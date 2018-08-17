package com.example.peter.playapp.data;

import com.google.gson.JsonElement;

public class LoginUser {

    private static LoginUser instance;

    private String accessToken;
    private String deviceId;
    private String account;
    private String genKey;

    public static LoginUser getInstance() {
        if (instance == null){
            synchronized (LoginUser.class){
                if (instance == null){
                    instance = new LoginUser();
                }
            }
        }
        return instance;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGenKey() {
        return genKey;
    }

    public void setGenKey(String genKey) {
        this.genKey = genKey;
    }
}
