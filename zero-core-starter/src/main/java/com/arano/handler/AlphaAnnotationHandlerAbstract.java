package com.arano.handler;

import com.arano.annotation.ZeroParamAlphaAnnotation;

/**
 * @author arano
 * @since 2021/6/10 00:32
 */
public class AlphaAnnotationHandlerAbstract extends AbstractParamAnnotationHandler<ZeroParamAlphaAnnotation> {


    @Override
    public Object process(ZeroParamAlphaAnnotation annotation, Object... args) {
        String metadata = annotation.parameterLevelMetadata();

        //如果参数标注为alpha 且arg为某对象实列，可以则对arg进行针对化处理
        if ("Alpha".equals(metadata) && args[0] instanceof String) {
            String res = (String) args[0];
            return "Alpha:" + res;
        }
        return args[0];
    }


}
