package com.arano.handler;

import com.arano.annotation.ZeroMethodAnnotation;
import com.arano.annotation.ZeroParamAlphaAnnotation;
import com.arano.annotation.ZeroParamBetaAnnotation;
import com.arano.request.InvokeContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author arano
 * @since 2021/6/9 23:21
 */
public class DelegateHandler implements ProcessHandler<InvokeContext> {

    private final Method method;
    private final AlphaAnnotationHandlerAbstract alphaAnnotationHandler;
    private final BetaAnnotationHandlerAbstract betaAnnotationHandler;
    private final InvokeHandler invokeHandler;


    public DelegateHandler(Method method) {
        this.method = method;
        alphaAnnotationHandler = new AlphaAnnotationHandlerAbstract();
        betaAnnotationHandler = new BetaAnnotationHandlerAbstract();
        invokeHandler = new InvokeHandler(method.getGenericReturnType());
    }

    public Object process(InvokeContext invokeContext, Object... args) {
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < args.length; i++) {
            for (Annotation annotation : annotations[i]) {
                if (annotation instanceof ZeroParamAlphaAnnotation) {
                    args[i] = alphaAnnotationHandler.process((ZeroParamAlphaAnnotation) annotation, args[i]);
                }
                if (annotation instanceof ZeroParamBetaAnnotation) {
                    args[i] = betaAnnotationHandler.process((ZeroParamBetaAnnotation) annotation, args[i]);
                }
            }
        }
        ZeroMethodAnnotation zeroMethodAnnotation = method.getAnnotation(ZeroMethodAnnotation.class);
        invokeContext.setMethodMetaParam(zeroMethodAnnotation.methodLevelMetadata());
        return invokeHandler.process(invokeContext, args);
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        Method[] methods = DelegateHandler.class.getMethods();
        System.out.println(Arrays.toString(methods));
        for (Method method : methods) {
            System.out.println(method.getName() + "===");
            for (Annotation[] parameterAnnotation : method.getParameterAnnotations()) {
                for (Annotation annotation : parameterAnnotation) {
                    System.out.println(annotation.annotationType());
                }
            }

        }
    }
}
