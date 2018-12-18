package com.example.peter.playapp.mvp.view;

import com.example.peter.playapp.base.BaseView;
import com.example.peter.playapp.bean.ProductInfo;
import com.example.peter.playapp.bean.ProductTypeBean;
import com.example.peter.playapp.mvp.model.MainModel;

import java.util.List;

public interface MainView extends BaseView {
    void getProductTypeSuccess(List<ProductTypeBean> list);

    void getProductSuccess(List<ProductInfo> productInfoList);

    void getProductFail(String msg);

    void getProductType();
}
