package com.example.peter.playapp.mvp.contract;

import com.example.peter.playapp.bean.ServerBean;

public interface errorCallback {
    void onSuccess(ServerBean serverBean);

    void onFail(String msg);
}
