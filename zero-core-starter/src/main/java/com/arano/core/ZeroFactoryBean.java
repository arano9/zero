package com.arano.core;

import com.arano.handler.DelegateHandler;
import com.arano.handler.ProcessHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ZeroFactoryBean
 *
 * @author arano
 * @since 2021/6/9 22:09
 */
public class ZeroFactoryBean<T> implements FactoryBean<T> {

    private Class<T> operationServiceInterface;

    public ZeroFactoryBean(Class<T> operationServiceInterface) {
        this.operationServiceInterface = operationServiceInterface;
    }


    @Override
    public T getObject() {
        checkBeanIsOperationService();
        return (T) Proxy.newProxyInstance(operationServiceInterface.getClassLoader(),
                new Class[]{operationServiceInterface},
                new ZeroBeanProxy(initMethodProcessHandler()));
    }


    private Map<String, ProcessHandler> initMethodProcessHandler() {
        Map<String, ProcessHandler> methodProcessHandler = new ConcurrentHashMap<>(4);
        for (Method method : operationServiceInterface.getMethods()) {
            methodProcessHandler.put(method.toString(), new DelegateHandler(method));
        }
        return methodProcessHandler;
    }


    //只用于接口
    private void checkBeanIsOperationService() {
        Assert.isTrue(operationServiceInterface.isInterface() && !operationServiceInterface.isAnnotation(), "@ZeroComponent only annotate on operationServiceInterface ");
    }


    @Override
    public Class<T> getObjectType() {
        return this.operationServiceInterface;
    }


}
