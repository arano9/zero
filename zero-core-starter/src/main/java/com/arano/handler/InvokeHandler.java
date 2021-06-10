package com.arano.handler;

import com.arano.caller.Client;
import com.arano.exception.ZeroRuntimeException;
import com.arano.request.InvokeContext;
import com.arano.response.ResultContext;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @author arano
 * @since 2021/6/10 00:34
 */
public class InvokeHandler implements ProcessHandler<InvokeContext> {

    private final Type methodReturnType;
    private Gson gson = new Gson();

    public InvokeHandler(Type methodReturnType) {
        this.methodReturnType = methodReturnType;
    }

    @Override
    public Object process(InvokeContext obj, Object... args) {
        obj.setReqJsonContent(gson.toJson(args));
        Client client = new Client();
        ResultContext resultContext = client.invoke(obj);
        if (resultContext.getStatus() != 0) {
            throw new ZeroRuntimeException(String.format("invoke failed :[%s]", resultContext.getMsg()));
        }
        String json = resultContext.getResContent();
        //todo @arano 所有其他非实体类型都应处理
        if (methodReturnType.equals(String.class))
            return json;
        return gson.fromJson(json, methodReturnType);
    }
}


