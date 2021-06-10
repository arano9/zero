package com.arano.service.impl;

import service.HelloService;

/**
 * @author arano
 * @since 2021/6/10 10:13
 */
public class HelloImpl implements HelloService {
    @Override
    public String hello(String name) {
        return String.format("hello:[%s]", name);
    }


    @Override
    public T echo(T param) {
        param.setA("echo:"+param.getA());
        param.setB("echo:"+param.getB());
        return param;
    }
}
