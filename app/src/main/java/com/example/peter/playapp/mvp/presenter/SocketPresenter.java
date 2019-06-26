package com.example.peter.playapp.mvp.presenter;

import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.mvp.view.SocketView;

public class SocketPresenter extends BasePresenter<SocketView> {

    public SocketPresenter(SocketView socketView) {
        attachView(socketView);
    }

}
