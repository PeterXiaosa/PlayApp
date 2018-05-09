package com.example.peter.playapp.base;

import android.os.Bundle;

import static com.google.common.base.Preconditions.checkNotNull;

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
