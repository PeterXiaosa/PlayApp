package com.example.peter.playapp.mvp;

import android.os.Bundle;

import com.example.peter.playapp.base.BaseActivity;
import com.example.peter.playapp.base.BasePresenter;


public abstract class MvpActivity<T extends BasePresenter> extends BaseActivity {
    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        checkNotNull(presenter).detachView();
        if (presenter != null){
            presenter.detachView();
        }
    }
}
