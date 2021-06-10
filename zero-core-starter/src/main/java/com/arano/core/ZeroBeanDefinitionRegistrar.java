package com.arano.core;

import com.arano.annotation.ZeroComponentScan;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * use {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor ConfigurationClassPostProcessor} to import zeroComponents' beanDefinition
 *
 * @author arano
 * @since 2021/6/9 22:21
 */
public class ZeroBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, BeanClassLoaderAware {

    private ClassLoader classLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes attributes = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(ZeroComponentScan.class.getName(), true));

        Set<String> packages = new HashSet<>();
        if (attributes != null) {
            addPackages(packages, attributes.getStringArray("basePackages"));
            addClasses(packages, attributes.getStringArray("basePackageClasses"));
            if (packages.isEmpty()) {
                packages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
            }
        }

        ZeroBeanScanner scanner = new ZeroBeanScanner(registry, classLoader);

        // 扫描并注册到BeanDefinition
        scanner.doScan(StringUtils.toStringArray(packages));
    }

    private void addPackages(Set<String> packages, String[] values) {
        if (values != null) {
            Collections.addAll(packages, values);
        }
    }

    private void addClasses(Set<String> packages, String[] values) {
        if (values != null) {
            for (String value : values) {
                packages.add(ClassUtils.getPackageName(value));
            }
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }


}
