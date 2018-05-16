package com.example.peter.playapp.mvp.model;

import com.example.peter.playapp.AppContext;
import com.example.peter.playapp.bean.ServerBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// Model负责 本地数据的读取，存储，网络，数据库，等等的操作
public class LoginModel extends ServerBean{
    private ServerBean serverInfo;

    public ServerBean getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerBean serverInfo) {
        this.serverInfo = serverInfo;
    }

    public void saveAccessToken(){
        if (this.getContent() != null) {
            JsonObject jsonObject = new JsonParser().parse(this.getContent().toString()).getAsJsonObject();
            AppContext.getInstance().setAccessToken(String.valueOf(jsonObject.get("accesstoken")));
        }
    }

}
