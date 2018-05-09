package com.example.peter.playapp.mvp.view;

import com.example.peter.playapp.base.BaseView;
import com.example.peter.playapp.mvp.model.MainModel;

public interface MainView extends BaseView {
    void getDataSuccess(MainModel mainModel);

    void getDataFail(String msg);
}
