package com.example.peter.playapp.activity;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peter.playapp.AppContext;
import com.example.peter.playapp.R;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.MvpActivity;
import com.example.peter.playapp.mvp.model.RegisterModel;
import com.example.peter.playapp.mvp.presenter.RegisterPresenter;
import com.example.peter.playapp.mvp.view.RegisterView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RegisterActivity extends MvpActivity<RegisterPresenter> implements RegisterView{

    @BindView(R.id.activity_register_tv_register)
    TextView tv_register;
    @BindView(R.id.activity_register_et_account)
    EditText et_account;
    @BindView(R.id.activity_register_et_password)
    EditText et_password;
    @BindView(R.id.activity_register_back)
    ImageView iv_back;

    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.activity_register_tv_register)
    public void register(){
        final UserInfo userInfo = new UserInfo();
        userInfo.setAccount(et_account.getText().toString().trim());
        userInfo.setPassword(et_password.getText().toString().trim());
        userInfo.setDeviceId(AppContext.getInstance().getDeviceId());
        userInfo.setGenkey("123");
        presenter.register(userInfo);

        Observable.create(new ObservableOnSubscribe<UserInfo>() {
            @Override
            public void subscribe(ObservableEmitter<UserInfo> e) throws Exception {
                e.onNext(userInfo);
            }
        }).map(new Function<UserInfo, Boolean>() {
            @Override
            public Boolean apply(UserInfo userInfo) throws Exception {
                if (userInfo.getAccount().trim().isEmpty()) {
                    Toast.makeText(getBaseContext(), "未填写账号", Toast.LENGTH_SHORT).show();
                    return false;
                }else if (userInfo.getPassword().trim().isEmpty()){
                    Toast.makeText(getBaseContext(), "未填写密码", Toast.LENGTH_SHORT).show();
                    return false;
                } else{
                    return true;
                }
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){
                    presenter.register(userInfo);
                }
            }
        });
    }

    @OnClick(R.id.activity_register_back)
    public void back(){
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void registerSuccess(RegisterModel registerModel) {
        Toast.makeText(this, "注册成功 : " + registerModel.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerFail(String errorMsg) {
        Toast.makeText(this, "注册失败 : " + errorMsg, Toast.LENGTH_SHORT).show();
    }
}
