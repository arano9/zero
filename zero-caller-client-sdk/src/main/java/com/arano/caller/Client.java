package com.arano.caller;

import cn.hutool.core.bean.BeanUtil;
import com.arano.request.InvokeContext;
import com.arano.response.ResultContext;
import com.arano.service.impl.HelloImpl;
import com.arano.service.impl.HelloServiceBImpl;
import com.google.gson.Gson;
import service.HelloService;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author arano
 * @since 2021/6/10 08:15
 */
public class Client {
    private final Gson gson = new Gson();

    public ResultContext invoke(InvokeContext invokeContext) {
        Object[] params = gson.fromJson(invokeContext.getReqJsonContent(), Object[].class);
        ResultContext resultContext = new ResultContext();
        try {
            if ("hello".equals(invokeContext.getMethodMetaParam())) {
                HelloImpl hello = new HelloImpl();
                Method method = HelloImpl.class.getMethod("hello", String.class);
                resultContext.setResContent((String) method.invoke(hello, params));
                resultContext.setStatus(0);
                resultContext.setMsg("ok");

            }
            if ("echo".equals(invokeContext.getMethodMetaParam())) {
                HelloImpl hello = new HelloImpl();
                Class<?>[] paramClz = new Class[]{HelloService.T.class};
                Method method = HelloImpl.class.getMethod("echo", paramClz);
                for (int i = 0; i < params.length; i++) {
                    if (String.class.equals(paramClz[i]))
                        continue;
                    params[i] = BeanUtil.toBean(params[i], paramClz[i]);
                }
                resultContext.setResContent(gson.toJson(method.invoke(hello, params)));
                resultContext.setStatus(0);
                resultContext.setMsg("ok");
            }
            if ("test".equals(invokeContext.getMethodMetaParam())) {
                HelloServiceBImpl helloServiceBImpl = new HelloServiceBImpl();
                Class<?>[] paramClz = new Class[]{Map.class, String.class, String.class};
                Method method = HelloServiceBImpl.class.getMethod("test", paramClz);
                for (int i = 0; i < params.length; i++) {
                    if (String.class.equals(paramClz[i]))
                        continue;
                    params[i] = BeanUtil.toBean(params[i], paramClz[i]);
                }
                resultContext.setResContent(gson.toJson(method.invoke(helloServiceBImpl, params)));
                resultContext.setStatus(0);
                resultContext.setMsg("ok");
            }
        } catch (Exception e) {
            resultContext.setMsg(e.getMessage());
            resultContext.setStatus(-1);
        }

        return resultContext;
    }
}
