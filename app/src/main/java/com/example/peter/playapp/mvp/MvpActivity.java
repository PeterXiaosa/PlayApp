package com.example.peter.playapp.mvp;

import android.os.Bundle;

import com.example.peter.playapp.base.BaseActivity;
import com.example.peter.playapp.base.BasePresenter;


public abstract class MvpActivity<T extends BasePresenter> extends BaseActivity {
    protected T mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpPresenter = createPresenter();
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        checkNotNull(mvpPresenter).detachView();
        if (mvpPresenter != null){
            mvpPresenter.detachView();
        }
    }
}
