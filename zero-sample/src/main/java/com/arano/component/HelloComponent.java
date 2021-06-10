package com.arano.component;

import com.arano.annotation.ZeroComponent;
import com.arano.annotation.ZeroMethodAnnotation;
import com.arano.annotation.ZeroParamAlphaAnnotation;
import service.HelloService;

/**
 * @author arano
 * @since 2021/6/10 10:35
 */
@ZeroComponent
public interface HelloComponent extends HelloService {
    @ZeroMethodAnnotation(methodLevelMetadata = "hello")
    @Override
    String hello(@ZeroParamAlphaAnnotation(parameterLevelMetadata = "Alpha") String name);

    @Override
    @ZeroMethodAnnotation(methodLevelMetadata = "echo")
    T echo(T param);
}
