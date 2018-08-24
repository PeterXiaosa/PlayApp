package com.example.peter.playapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
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
import com.example.peter.playapp.data.LoginUser;
import com.example.peter.playapp.mvp.MvpActivity;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.presenter.LoginPresenter;
import com.example.peter.playapp.mvp.view.LoginView;
import com.example.peter.playapp.util.ToastUtil;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;


public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView{

    @BindView(R.id.activity_login_et_account)
    EditText et_account;
    @BindView(R.id.activity_login_et_password)
    EditText et_password;
    @BindView(R.id.activity_login_tv_login)
    TextView tv_login;
    @BindView(R.id.activity_login_tv_register)
    TextView tv_register;

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

    @SuppressLint("CheckResult")
    @OnClick(R.id.activity_login_tv_login)
    public void login(){
        final String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        final UserInfo userInfo = new UserInfo();
        userInfo.setAccount(et_account.getText().toString());
        userInfo.setPassword(et_password.getText().toString());
        userInfo.setGenkey(LoginUser.getInstance().getGenKey());
        userInfo.setDeviceId(LoginUser.getInstance().getDeviceId());

        Observable.create(new ObservableOnSubscribe<UserInfo>() {
            @Override
            public void subscribe(ObservableEmitter<UserInfo> e) throws Exception {
                e.onNext(userInfo);
            }
        }).map(new Function<UserInfo, Boolean>() {
            @Override
            public Boolean apply(UserInfo s) throws Exception {
                if (s.getAccount().trim().isEmpty()) {
                    ToastUtil.show(getBaseContext(), "未填写账号");
                    return false;
                }else if (s.getPassword().trim().isEmpty()){
                    ToastUtil.show(getBaseContext(), "未填写密码");
                    return false;
                } else{
                    return true;
                }
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){
                    presenter.login(userInfo, methodName);
                }
            }
        });
    }

    @OnClick(R.id.activity_login_tv_register)
    public void register(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功" , Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }

    @Override
    public void loginFail(String errorMsg, int errorCode) {
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
        ToastUtil.show(getBaseContext(), "登录失败, " + errorMsg);
    }

    @Override
    public void loginFail(String errorMsg) {
        ToastUtil.show(getBaseContext(), "登录失败, " + errorMsg);
    }

    @Override
    public void showLoading() {
        ToastUtil.show(getBaseContext(), "showLoading");
    }

    @Override
    public void cancelLoading() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void reflect(String methodName) {
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, null);
            this.use(method);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}