package com.example.peter.playapp.activity;

import android.support.annotation.NonNull;
import android.view.View;

import com.example.peter.playapp.R;
import com.example.peter.playapp.base.BaseActivity;
import com.example.peter.playapp.base.MvpActivity;
import com.example.peter.playapp.mvp.contract.LoginContract;
import com.example.peter.playapp.mvp.model.LoginModel;
import com.example.peter.playapp.mvp.presenter.LoginPresenter;
import com.example.peter.playapp.mvp.presenter.MainPresenter;
import com.example.peter.playapp.mvp.view.LoginView;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView{


    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess(LoginModel loginModel) {

    }

    @Override
    public void loginFail(String errorMsg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }
}

