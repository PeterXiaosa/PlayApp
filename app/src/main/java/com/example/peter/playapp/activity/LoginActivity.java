package com.example.peter.playapp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
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
import com.example.peter.playapp.util.DownLoadUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView{

    @BindView(R.id.activity_login_et_account)
    EditText et_account;
    @BindView(R.id.activity_login_et_password)
    EditText et_password;
    @BindView(R.id.activity_login_tv_login)
    TextView tv_login;

    // 可以考虑一下将加载数据放到presenter中去。
    @Override
    public void initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        0);
            }else {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AppContext.getInstance().setDeviceId();
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        0);
            }else {

            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_SETTINGS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        0);
            }else {

            }
        }
    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.activity_login_tv_login)
    public void setBtn_login(){
        startActivity(new Intent(LoginActivity.this, MainActivity.class));

//        if ("".equals(et_account.getText().toString())){
//            Toast.makeText(this, "未填写账号", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if ("".equals(et_password.getText().toString())){
//            Toast.makeText(this, "未填写密码", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        UserInfo userInfo = new UserInfo();
//        userInfo.setAccount(et_account.getText().toString());
//        userInfo.setPassword(et_password.getText().toString());
//        userInfo.setGenkey(AppContext.getInstance().getGenKey());
//        userInfo.setDeviceId(AppContext.getInstance().getDeviceId());
//
//        presenter.login(userInfo);
//
//        DownLoadUtil.downClassFile();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess(LoginModel loginModel) {
        // 可以封装成json再传过来，处理逻辑在presenter中处理。
        Gson gson = new Gson();
        if (loginModel.getContent() != null) {
            JsonObject jsonObject = new JsonParser().parse(loginModel.getContent().toString()).getAsJsonObject();
//            Toast.makeText(this, "登录成功, accesstoken = " + jsonObject.get("accesstoken"), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "登录成功" , Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
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

