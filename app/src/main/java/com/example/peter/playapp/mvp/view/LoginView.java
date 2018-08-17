package com.example.peter.playapp.mvp.view;

import com.example.peter.playapp.base.BaseView;

public interface LoginView extends BaseView{
    void loginSuccess();

    void loginFail(String errorMsg, int errorCode);

    void loginFail(String errorMsg);
}
