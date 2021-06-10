package com.arano.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于注解在方法上作为元数据说明
 *
 * @author arano
 * @since 2021/6/9 23:01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ZeroMethodAnnotation {
    //方法级别的说明
    String methodLevelMetadata();
}
