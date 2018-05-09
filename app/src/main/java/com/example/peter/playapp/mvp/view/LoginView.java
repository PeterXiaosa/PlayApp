package com.example.peter.playapp.mvp.view;

import com.example.peter.playapp.base.BaseView;
import com.example.peter.playapp.mvp.contract.LoginContract;
import com.example.peter.playapp.mvp.model.LoginModel;

public interface LoginView extends BaseView{
    void loginSuccess(LoginModel loginModel);

    void loginFail(String errorMsg);
}
