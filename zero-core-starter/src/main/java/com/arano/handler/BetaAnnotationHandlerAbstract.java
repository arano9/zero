package com.arano.handler;

import com.arano.annotation.ZeroParamBetaAnnotation;

/**
 * @author arano
 * @since 2021/6/10 00:32
 */
public class BetaAnnotationHandlerAbstract extends AbstractParamAnnotationHandler<ZeroParamBetaAnnotation> {
    @Override
    public Object process(ZeroParamBetaAnnotation annotation, Object... args) {
        String metadata = annotation.parameterLevelMetadata();

        //如果参数标注为beta 且arg为某对象实列，可以则对arg进行针对化处理
        if ("Beta".equals(metadata) && args[0] instanceof String) {
            String res = (String) args[0];
            return "Beta:" + res;
        }
        return args[0];
    }
}
