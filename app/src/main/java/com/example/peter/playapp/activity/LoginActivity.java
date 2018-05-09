package com.example.peter.playapp.activity;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.peter.playapp.R;
import com.example.peter.playapp.base.BaseActivity;
import com.example.peter.playapp.base.MvpActivity;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.contract.LoginContract;
import com.example.peter.playapp.mvp.model.LoginModel;
import com.example.peter.playapp.mvp.presenter.LoginPresenter;
import com.example.peter.playapp.mvp.presenter.MainPresenter;
import com.example.peter.playapp.mvp.view.LoginView;
import com.google.gson.Gson;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView{

    @BindView(R.id.activity_login_et_account)
    EditText et_account;
    @BindView(R.id.activity_login_et_password)
    EditText et_password;
    @BindView(R.id.activity_login_tv_login)
    Button btn_login;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.activity_login_tv_login)
    public void setBtn_login(){
        UserInfo userInfo = new UserInfo();
//        mvpPresenter.login();
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

