package com.example.peter.playapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.peter.playapp.R;
import com.example.peter.playapp.mvp.MvpActivity;
import com.example.peter.playapp.mvp.presenter.SocketPresenter;
import com.example.peter.playapp.mvp.view.SocketView;

import butterknife.BindView;
import butterknife.OnClick;

public class SocketActivity extends MvpActivity<SocketPresenter> implements SocketView {

    @BindView(R.id.btn_match_code)
    Button btn_match_code;

    @Override
    protected SocketPresenter createPresenter() {
        return new SocketPresenter(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reflect(String methodName) {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_socket;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @OnClick(R.id.btn_match_code)
    public void getMatchCode(){

    }
}
