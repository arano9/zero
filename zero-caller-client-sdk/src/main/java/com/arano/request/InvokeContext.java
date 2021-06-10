package com.arano.request;

/**
 * 调用流程中的上下文
 *
 * @author arano
 * @since 2021/6/9 23:46
 */
public class InvokeContext {
    public static InvokeContext aContext() {
        return new InvokeContext();
    }

    private String methodMetaParam;
    private String reqJsonContent;


    public String getMethodMetaParam() {
        return methodMetaParam;
    }

    public void setMethodMetaParam(String methodMetaParam) {
        this.methodMetaParam = methodMetaParam;
    }

    public String getReqJsonContent() {
        return reqJsonContent;
    }

    public void setReqJsonContent(String reqJsonContent) {
        this.reqJsonContent = reqJsonContent;
    }
}
