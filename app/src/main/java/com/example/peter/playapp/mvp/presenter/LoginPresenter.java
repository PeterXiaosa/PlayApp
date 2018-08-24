package com.example.peter.playapp.mvp.presenter;

import android.support.annotation.NonNull;

import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.contract.errorCallback;
import com.example.peter.playapp.mvp.model.LoginModel;
import com.example.peter.playapp.mvp.view.LoginView;
import com.example.peter.playapp.retrofit.ApiCallback;

// Presenter 中保留View的引用, 通过Model去进行数据存储，同时通过Callback进行Model与Presenter的交互
public class LoginPresenter extends BasePresenter<LoginView>{

    public LoginPresenter(@NonNull LoginView view){
        attachView(view);
        initLoginData();
    }

    private void initLoginData(){

    }

    public void login(final UserInfo userInfo, final String methodName) {
        mvpView.showLoading();
        addSubscription(api.login(userInfo),
                new ApiCallback<LoginModel>() {
                    @Override
                    public void onSuccess(LoginModel model) {
                        // 获取数据成功去更新View
                        if (model.getStatus() == 0){
                            model.saveLoginUserInfo();
                            mvpView.loginSuccess();
                        }else {
                            model.saveLoginUserAccountName(userInfo.getAccount());
                            mvpView.loginFail(model.getMsg(), model.getStatus());
                            errorCodeSubscription(model.getStatus(), new errorCallback() {
                                @Override
                                public void onSuccess(ServerBean model) {
                                    if (model.getStatus() == 0){
                                        // 重新调用原方法继续之前操作
                                        mvpView.reflect(methodName);
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
}
