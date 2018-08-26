package com.example.peter.playapp.mvp.presenter;

import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.bean.ProductInfo;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.model.MainModel;
import com.example.peter.playapp.mvp.view.MainView;
import com.example.peter.playapp.retrofit.ApiCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainPresenter extends BasePresenter<MainView>{

    public MainPresenter(MainView view){
        attachView(view);
    }

    public void getProduct(int type){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        addSubscription(api.getProductInfo(body),
                new ApiCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        List<ProductInfo> data = model.getProductList();
                        mvpView.getProductSuccess(data);
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onFinish() {
                        mvpView.cancelLoading();
                    }
                });
    }

//    public void getProduceType(){
//        addSubscription(api.);
//    }
}
