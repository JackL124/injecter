package com.jackl.injecter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: jackl
 * @date: 2021/12/6
 **/
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
 public @interface BaseClickInject {
    String listenerName();
    Class<?> listnerType();
}
