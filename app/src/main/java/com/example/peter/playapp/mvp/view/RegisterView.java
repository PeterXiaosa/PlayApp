package com.example.peter.playapp.mvp.view;

import com.example.peter.playapp.base.BaseView;
import com.example.peter.playapp.mvp.model.RegisterModel;

public interface RegisterView extends BaseView{
    void registerSuccess(RegisterModel registerModel);

    void registerFail(String errorMsg);
}
