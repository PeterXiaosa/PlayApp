package com.example.peter.playapp.mvp.presenter;

import android.support.annotation.NonNull;

import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.contract.LoginContract;
import com.example.peter.playapp.mvp.model.LoginModel;
import com.example.peter.playapp.mvp.view.LoginView;
import com.example.peter.playapp.retrofit.ApiCallback;

public class LoginPresenter extends BasePresenter<LoginView>{

    public LoginPresenter(@NonNull LoginView view){
        attachView(view);
        initLoginData();
    }

    public void login(UserInfo userInfo){
        mvpView.showLoading();

        addSubscription(api.login(userInfo),
                new ApiCallback<LoginModel>() {
                    @Override
                    public void onSuccess(LoginModel model) {
                        // 获取数据成功去更新View
                        mvpView.loginSuccess(model);

                        model.saveAccessToken();
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.loginFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.cancelLoading();
                    }
                });
    }

    public void initLoginData(){

    }
}
