package com.example.peter.playapp.mvp.presenter;

import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.model.MainModel;
import com.example.peter.playapp.mvp.view.MainView;
import com.example.peter.playapp.retrofit.ApiCallback;

public class MainPresenter extends BasePresenter<MainView>{

    public MainPresenter(MainView view){
        attachView(view);
    }

    public void getProduct(int type){

    }
}
