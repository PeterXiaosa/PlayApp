package com.example.peter.playapp.bean;

public class LoginInfo {

    private static LoginInfo instance;

    private LoginInfo(){}

    public static LoginInfo getInstance(){

        if (instance == null) {
            synchronized (LoginInfo.class) {
                if (instance == null) {
                    instance = new LoginInfo();
                }
            }
        }
        return instance;
    }
}
