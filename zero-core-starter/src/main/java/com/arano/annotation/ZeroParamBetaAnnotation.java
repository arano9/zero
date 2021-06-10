package com.arano.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author arano
 * @since 2021/6/9 23:04
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ZeroParamBetaAnnotation {
    //参数级别的说明 为参数需要的做的事情进行说明
    String parameterLevelMetadata();
}
