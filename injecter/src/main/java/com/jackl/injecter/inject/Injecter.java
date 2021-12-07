package com.jackl.injecter.inject;

import android.app.Activity;
import android.view.View;
import androidx.fragment.app.Fragment;
import com.jackl.injecter.annotation.BaseClickInject;
import com.jackl.injecter.annotation.BindClick;
import com.jackl.injecter.annotation.BindInflate;
import com.jackl.injecter.annotation.BindView;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


/**
 * @description: 通过反射+动态代理实现注入view实例 点击事件 及activity布局加载
 * @author: jackl
 * @date: 2021/12/2
 **/
public class Injecter {

    private Map<Integer, MapNode> tagetIds = new HashMap();
    private Object obj;

    public void inject(Activity act){
        inject(act,act.getClass());
    }

    public void inject(Fragment fra){
        inject(fra,fra.getClass());
    }

    public void inject(View view){
        inject(view,view.getClass());
    }

    private void inject(Object obj,Class<?> tagetClass) {
        this.obj = obj;
        try {
            injectType(tagetClass);
            injectField(tagetClass);
            injectMethod(tagetClass);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对activity fragment view
     * */
    private void injectType(Class<?> tagetClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        BindInflate bindInflate = tagetClass.getAnnotation(BindInflate.class);
        if (bindInflate!=null){
            int id = bindInflate.id();
            if(id!=-1){
                    //对Activity支持
                 if (obj instanceof Activity) {
                    //通过反射调用setContentView方法
                    Method setContentView=tagetClass.getMethod("setContentView",int.class);
                    setContentView.invoke(obj,id);
                }
            }
        }
    }

    /**
     * 对BindView注解过的成员变量实例化对象
     */
    private void injectField(Class<?> tagetClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //找到类中所有的变量，不包含父类变量
        Field[] fields = tagetClass.getDeclaredFields();
        //遍历所有成员变量
        for (Field declaredField : fields) {
            //找到被@BindView标记的成员变量
            if (!declaredField.isAnnotationPresent(BindView.class)) {
                continue;
            }
            //获取自定义注解中的id值
            int id = declaredField.getAnnotation(BindView.class).id();
            //判断id是否合法
            if (id == -1) {
                continue;
            }
            View bindView = null;
            //针对非public权限修饰符下的变量 取消访问检查
            declaredField.setAccessible(true);
            //对view支持
            if (obj instanceof View) {
                bindView = ((View) obj).findViewById(id);
                //对Activity支持
            } else if (obj instanceof Activity) {
                bindView = ((Activity) obj).findViewById(id);
                //对Fragment支持
            } else if (obj instanceof Fragment) {
                //找到fragment中mView
                Method getViewMethod = tagetClass.getMethod("getView");
                //取消访问检查
                getViewMethod.setAccessible(true);
                View mView= (View) getViewMethod.invoke(obj,null);
                bindView = mView.findViewById(id);
            }
            if (bindView != null) {
                declaredField.set(obj, bindView);
                MapNode mapNode = new MapNode(id, bindView);
                tagetIds.put(id, mapNode);
            }
        }
    }

    /**
     * 对BindClick注解过的方法，找到所以对应view的实例，通过动态代理模式回调事件
     */
    private void injectMethod(Class<?> tagetClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        //找到类中所有的方法。不包含父类方法
        Method[] methods = tagetClass.getDeclaredMethods();
        //遍历所有的方法
        for (Method declaredMethod : methods) {
            if (!declaredMethod.isAnnotationPresent(BindClick.class)) {
                continue;
            }
            declaredMethod.setAccessible(true);
            BindClick bindClick = declaredMethod.getAnnotation(BindClick.class);
            //获取方法被@BindClick标记的ids
            int[] ids = bindClick.ids();
            if(ids.length<1){
                continue;
            }
            Class<? extends Annotation> annotationType = bindClick.annotationType();
            String listenerName = annotationType.getAnnotation(BaseClickInject.class).listenerName();
            Class<?> listnerType = annotationType.getAnnotation(BaseClickInject.class).listnerType();
            //遍历所有ids
            for (int id : ids) {
                //命中目标,可以设置点击事件
                if (tagetIds.containsKey(id)) {
                    tagetIds.get(id).setCursorMeath(declaredMethod);
                    bindClick(tagetIds.get(id), listenerName, listnerType);
                }
            }
        }
    }


    private void bindClick(MapNode mapNode, String listenerName, Class<?> listnerType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        View.OnClickListener listennerProxy = (View.OnClickListener) Proxy.newProxyInstance(
                listnerType.getClassLoader(),
                new Class<?>[]{listnerType},
                new InjecterInvocationHandler(mapNode.getCursorMeath())
        );
        Method onClick = mapNode.getView().getClass().getMethod(listenerName, listnerType);
        onClick.invoke(mapNode.getView(), listennerProxy);
    }

    private class InjecterInvocationHandler implements InvocationHandler {
        private Method targetMethod;

        public InjecterInvocationHandler(Method targetMethod) {
            this.targetMethod = targetMethod;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, args);
            }
            return targetMethod.invoke(obj, args);
        }
    }
}
