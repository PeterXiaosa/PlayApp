package com.example.peter.playapp.mvp.presenter;

import android.support.annotation.NonNull;

import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.model.LoginModel;
import com.example.peter.playapp.mvp.modelImpl.LoginModelImpl;
import com.example.peter.playapp.mvp.view.LoginView;
import com.example.peter.playapp.retrofit.ApiCallback;
import com.example.peter.playapp.util.ErrorHelper;

// Presenter 中保留View的引用, 通过Model去进行数据存储，同时通过Callback进行Model与Presenter的交互
public class LoginPresenter extends BasePresenter<LoginView>{

    public LoginPresenter(@NonNull LoginView view){
        attachView(view);
        initLoginData();
    }

    public void login(UserInfo userInfo){
        mvpView.showLoading();
        addSubscription(api.login(userInfo),
                new ApiCallback<LoginModelImpl>() {
                    @Override
                    public void onSuccess(LoginModelImpl model) {
                        // 获取数据成功去更新View
                        if (model.getStatus() == 0){
                            model.saveLoginUserInfo();
                            mvpView.loginSuccess();
                        }else {
                            mvpView.loginFail(model.getMsg(), model.getStatus());

                            errorCodeSubscription(model.getStatus(), new ServerBean.errorCallback() {
                                @Override
                                public void onSuccess(ServerBean model) {
                                    if (model.getStatus() == 0){

                                    }else {

                                    }
                                }

                                @Override
                                public void onFail(String msg) {

                                }
                            });
                        }
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
