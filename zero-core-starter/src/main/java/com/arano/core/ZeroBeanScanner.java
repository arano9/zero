package com.arano.core;

import com.arano.annotation.ZeroComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 * ZeroBeanScanner
 *
 * @author arano
 * @since 2021/6/09 23:5
 */
public class ZeroBeanScanner extends ClassPathBeanDefinitionScanner {

    private ClassLoader classLoader;

    public static final Logger LOGGER = LoggerFactory.getLogger(ZeroBeanScanner.class);


    public ZeroBeanScanner(BeanDefinitionRegistry registry, ClassLoader classLoader) {
        super(registry, false);
        this.classLoader = classLoader;
        //只扫描有ZeroComponent注解的
        this.addIncludeFilter(new AnnotationTypeFilter(ZeroComponent.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        //确保只是接口
        if (beanDefinition.getMetadata().isInterface()) {
            try {
                Class<?> target = ClassUtils.forName(beanDefinition.getMetadata().getClassName(), classLoader);
                return !target.isAnnotation();
            } catch (Exception ex) {
                LOGGER.error("load class exception:{}", ex.getMessage(), ex);
            }
        }
        return false;
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("No @ZeroComponent was found in {} packages. Please check your configuration.", Arrays.toString(basePackages));
            }
        } else {
            processBeanDefinitions(beanDefinitions);
        }
        return beanDefinitions;
    }


    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder definitionHolder : beanDefinitions) {
            definition = (GenericBeanDefinition) definitionHolder.getBeanDefinition();
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Creating ZeroComponent with name [{}] and [{}] Interface", definitionHolder.getBeanName(), definition.getBeanClassName());
            }
            definition.getConstructorArgumentValues().addGenericArgumentValue(Objects.requireNonNull(definition.getBeanClassName()));
            // 利用factoryBean创建对象
            definition.setBeanClass(ZeroFactoryBean.class);
        }
    }

}
