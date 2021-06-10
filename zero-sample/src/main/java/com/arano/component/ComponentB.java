package com.arano.component;

import com.arano.annotation.ZeroComponent;
import com.arano.annotation.ZeroMethodAnnotation;
import com.arano.annotation.ZeroParamAlphaAnnotation;
import com.arano.annotation.ZeroParamBetaAnnotation;
import service.HelloServiceB;

import java.util.Map;

/**
 * @author arano
 * @since 2021/6/10 11:43
 */
@ZeroComponent
public interface ComponentB extends HelloServiceB {

    @ZeroMethodAnnotation(methodLevelMetadata = "test")
    @Override
    Map<String, String> test(Map<String, String> p,
                             @ZeroParamAlphaAnnotation(parameterLevelMetadata = "Alpha") String a,
                             @ZeroParamBetaAnnotation(parameterLevelMetadata = "Beta") String b);
}
