package com.arano.handler;

/**
 * @author arano
 * @since 2021/6/9 23:19
 */
public interface ProcessHandler<O> {
    Object process(O obj, Object... args);
}
