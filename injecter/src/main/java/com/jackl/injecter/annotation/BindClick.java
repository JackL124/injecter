package com.jackl.injecter.annotation;

import android.view.View;
import androidx.annotation.IdRes;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@BaseClickInject(listenerName = "setOnClickListener", listnerType = View.OnClickListener.class)
public @interface BindClick {
   @IdRes int [] ids() default {};
}
