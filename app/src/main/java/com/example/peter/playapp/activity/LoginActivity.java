package com.example.peter.playapp.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.playapp.AppContext;
import com.example.peter.playapp.R;
import com.example.peter.playapp.mvp.MvpActivity;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.model.LoginModel;
import com.example.peter.playapp.mvp.presenter.LoginPresenter;
import com.example.peter.playapp.mvp.view.LoginView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView{

    @BindView(R.id.activity_login_et_account)
    EditText et_account;
    @BindView(R.id.activity_login_et_password)
    EditText et_password;
    @BindView(R.id.activity_login_tv_login)
    TextView tv_login;

    @Override
    public void initData() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            int REQUEST_EXTERNAL_STORAGE = 1;
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_PHONE_STATE
            };
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AppContext.getInstance().setDeviceId();
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
        userInfo.setAccount(et_account.getText().toString());
        userInfo.setPassword(et_password.getText().toString());
        userInfo.setGenkey(AppContext.getInstance().getGenKey());
        userInfo.setDeviceId(AppContext.getInstance().getDeviceId());
        mvpPresenter.login(userInfo);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess(LoginModel loginModel) {
        Gson gson = new Gson();
        if (loginModel.getContent() != null) {
            JsonObject jsonObject = new JsonParser().parse(loginModel.getContent().toString()).getAsJsonObject();
            Toast.makeText(this, "登录成功, accesstoken = " + jsonObject.get("accesstoken"), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "登录失败, " + loginModel.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loginFail(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
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

