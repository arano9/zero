package com.arano.annotation;

import com.arano.core.ZeroBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * scan packages to find  Beans annotated by {@link ZeroComponent}
 *
 * @author arano
 * @since 2021/6/9 22:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ZeroBeanDefinitionRegistrar.class)
public @interface ZeroComponentScan {


    String[] basePackages() default {};


    Class<?>[] basePackageClasses() default {};
}
