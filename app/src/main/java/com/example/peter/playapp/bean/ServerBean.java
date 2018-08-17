package com.example.peter.playapp.bean;

public class ServerBean<T> {
    public interface errorCallback{
        void onSuccess(ServerBean serverBean);

        void onFail(String msg);
    }

    private int status;
    private String msg;
    private T content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
