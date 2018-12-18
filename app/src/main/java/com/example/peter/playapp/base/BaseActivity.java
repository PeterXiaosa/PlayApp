package com.example.peter.playapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.peter.playapp.Interface.BaseMethodInterface;
import com.example.peter.playapp.Interface.BaseViewInterface;

import java.lang.reflect.Method;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener, BaseViewInterface, BaseMethodInterface{

    private boolean _isVisible;

    private ProgressDialog waitDialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.reset(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppManager.getAppManager().addActivity(this);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        ButterKnife.bind(this);

        initData();
        initView();

        _isVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void use(Method method) throws Exception{
        if (method == null)
            return;
        method.invoke(this);
    }
}