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

        // 通过注解绑定控件
//        ButterKnife.inject(this);
        ButterKnife.bind(this);

        //透明状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        initData();
        initView();

        _isVisible = true;
    }

//    protected int getLayoutId() {
//        return 0;
//    }

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