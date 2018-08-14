package com.example.peter.playapp.mvp.presenter;

import android.support.annotation.NonNull;

import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.model.RegisterModel;
import com.example.peter.playapp.mvp.view.RegisterView;
import com.example.peter.playapp.retrofit.ApiCallback;

public class RegisterPresenter extends BasePresenter<RegisterView> {
    public RegisterPresenter() {
    }
    public RegisterPresenter(@NonNull RegisterView view){
        attachView(view);
        initRegisterData();
    }

    private void initRegisterData() {

    }

    public void register(UserInfo userInfo){
        mvpView.showLoading();

        addSubscription(api.register(userInfo),
                new ApiCallback<RegisterModel>() {
                    @Override
                    public void onSuccess(RegisterModel model) {
                        if (model.getStatus() == 0) {
                            mvpView.registerSuccess(model);
                        }else {
                            mvpView.registerFail(model.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.registerFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.cancelLoading();
                    }
                });
    }
}
