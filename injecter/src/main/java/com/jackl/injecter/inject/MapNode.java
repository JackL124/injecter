package com.jackl.injecter.inject;

import android.view.View;
import java.lang.reflect.Method;

/**
 * @author: jackl
 * @date: 2021/12/3
 **/
 class MapNode {
   private int id;
   private View view;
   private Method cursorMeath;

   private MapNode() {
   }

   public MapNode(int id, View view) {
      this.id = id;
      this.view = view;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public View getView() {
      return view;
   }

   public void setView(View view) {
      this.view = view;
   }

   public Method getCursorMeath() {
      return cursorMeath;
   }

   public void setCursorMeath(Method cursorMeathName) {
      cursorMeath = cursorMeathName;
   }
}
