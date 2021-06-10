package com.arano.core;


import com.arano.handler.DelegateHandler;
import com.arano.handler.ProcessHandler;
import com.arano.request.InvokeContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * ZeroBeanProxy
 *
 * @author arano
 * @since 2021/6/10 00:50
 */
public class ZeroBeanProxy implements InvocationHandler {

    private final Map<String, ProcessHandler> methodProcessHandlerMap;

    public ZeroBeanProxy(Map<String, ProcessHandler> methodProcessHandlerMap) {
        this.methodProcessHandlerMap = methodProcessHandlerMap;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String methodName = method.getName();
        if ("toString".equals(methodName)) {
            return "proxy$" + method.getDeclaringClass() + "$" + method.getName();
        }
        DelegateHandler processHandler = (DelegateHandler) methodProcessHandlerMap.get(method.toString());
        if (processHandler == null) {
            throw new UnsupportedOperationException("not support method:" + methodName);
        }
        return processHandler.process(InvokeContext.aContext(), args);
    }


}
