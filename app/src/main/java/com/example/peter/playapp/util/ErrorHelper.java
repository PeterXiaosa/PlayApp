package com.example.peter.playapp.util;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.peter.playapp.AppContext;
import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.base.BaseView;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.data.LoginUser;
import com.example.peter.playapp.retrofit.Api;
import com.example.peter.playapp.retrofit.ApiCallback;
import com.example.peter.playapp.retrofit.ApiClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

public class ErrorHelper{

    private static final int ACCESSTOKEN_TIME_OUT = 10012;

    private static final ErrorHelper ourInstance = new ErrorHelper();

    public static ErrorHelper getInstance() {
        return ourInstance;
    }

//    public void dealError(BaseView view, int errorCode, String activityName, String methodName){
//        switch (errorCode){
//            case ACCESSTOKEN_TIME_OUT:
//                LoginUser.getInstance().setAccessToken("");
//                reGetAccessToken(activityName, methodName);
//                break;
//        }
//    }

//    private void reGetAccessToken(String activityName, String methodName) {
//        String account = LoginUser.getInstance().getAccount();
//        String timeStamp = CertificateUtil.getCurrentTime();
//        String nonce = CertificateUtil.getRandomString(CertificateUtil.lengthOfNonce);
//        String genKey = LoginUser.getInstance().getGenKey();
//        String signature = CertificateUtil.generateSignature(genKey, timeStamp, nonce);
//        addSubscription(api.refreshToken(account, timeStamp, nonce, signature), new ApiCallback<ServerBean>() {
//            @Override
//            public void onSuccess(ServerBean model) {
//                JsonObject jsonObject = null;
//                if (model.getStatus() == 0) {
//                    jsonObject = new JsonParser().parse(model.getContent().toString()).getAsJsonObject();
//                    LoginUser.getInstance().setAccessToken(String.valueOf(jsonObject.get("accesstoken")));
//                    mvpView.showLoading();
//                }else {
//                    // 签名失败 10008 或者其他错误
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
//    }

    public Observable getObservableByErrorCode(int errorCode, Api api){
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

            }
        });
        switch (errorCode){
            case ACCESSTOKEN_TIME_OUT:
                String account = LoginUser.getInstance().getAccount();
                String genKey = LoginUser.getInstance().getGenKey();
                String timeStamp = CertificateUtil.getTimeStamp();
                String nonce = CertificateUtil.getRandomString();
                String signature = CertificateUtil.generateSignature(genKey, timeStamp, nonce);
                observable = api.refreshToken(account, timeStamp, nonce, genKey);
                break;
        }
        return observable;
    }
}
