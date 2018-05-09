package com.example.peter.playapp.mvp.contract;


import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.base.BasePresenterGoogle;
import com.example.peter.playapp.base.BaseViewGoogle;

public interface LoginContract {
    interface View extends BaseViewGoogle<Presenter> {
        void hideTitle();

        void showLoadingDialog();

        void hideLoadingDialog();

        void showTitle(String title);
    }

    interface Presenter extends BasePresenterGoogle{
        void login();
    }
}
