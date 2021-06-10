package com.arano.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * mark interface as a component which is created by {@link com.arano.core.ZeroFactoryBean}
 *
 * @author arano
 * @since 2021/6/9 22:09
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
@Indexed
public @interface ZeroComponent {
}
