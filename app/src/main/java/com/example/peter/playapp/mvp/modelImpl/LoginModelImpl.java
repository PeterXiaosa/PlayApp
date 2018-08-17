package com.example.peter.playapp.mvp.modelImpl;

import com.example.peter.playapp.AppContext;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.data.LoginUser;
import com.example.peter.playapp.mvp.model.LoginModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// Model负责 本地数据的读取，存储，网络，数据库，等等的操作
public class LoginModelImpl extends ServerBean implements LoginModel{
    private String accesstoken = "accesstoken";
    private String account = "account";

    @Override
    public void saveLoginUserInfo() {
        JsonObject jsonObject = null;
        if (this.getContent() != null) {
            jsonObject = new JsonParser().parse(this.getContent().toString()).getAsJsonObject();
        }
        if (jsonObject != null) {
            // Save AccessToken
            LoginUser.getInstance().setAccessToken(String.valueOf(jsonObject.get(accesstoken)));
            LoginUser.getInstance().setAccount(String.valueOf(jsonObject.get(account)));
        }
    }
}
