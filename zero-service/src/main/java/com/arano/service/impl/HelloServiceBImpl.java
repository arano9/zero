package com.arano.service.impl;

import service.HelloServiceB;

import java.util.Map;

/**
 * @author arano
 * @since 2021/6/10 11:50
 */
public class HelloServiceBImpl implements HelloServiceB {
    @Override
    public Map<String, String> test(Map<String, String> p, String a, String b) {
        p.put("a", a);
        p.put("b", b);
        return p;
    }
}
