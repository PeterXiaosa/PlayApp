package com.example.peter.playapp.mvp.model;

import com.example.peter.playapp.bean.UserInfo;

public class MainModel {
    private UserInfo mUserInfo;

    public UserInfo getUserInfo(){
        return mUserInfo;
    }

    public void setUserInfi(UserInfo userInfo){
        this.mUserInfo = userInfo;
    }
}
