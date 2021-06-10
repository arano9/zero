package com.arano.response;

/**
 * @author arano
 * @since 2021/6/10 08:31
 */
public class ResultContext {
    private int status;
    private String msg;
    private String resContent;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResContent() {
        return resContent;
    }

    public void setResContent(String resContent) {
        this.resContent = resContent;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
