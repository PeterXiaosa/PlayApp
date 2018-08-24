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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ErrorHelper{

    private static final int ACCESSTOKEN_TIME_OUT = 10012;

    private static final ErrorHelper ourInstance = new ErrorHelper();

    public static ErrorHelper getInstance() {
        return ourInstance;
    }

    public Observable getObservableByErrorCode(int errorCode, Api api){
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {

            }
        });
        switch (errorCode){
            case ACCESSTOKEN_TIME_OUT:
                observable = getTimeOutObservable(api);
                break;
        }
        return observable;
    }

    private Observable getTimeOutObservable(Api api) {
        String account = LoginUser.getInstance().getAccount();
        String genKey = LoginUser.getInstance().getGenKey();
        String timeStamp = CertificateUtil.getTimeStamp();
        String nonce = CertificateUtil.getRandomString();
        String signature = CertificateUtil.generateSignature(genKey, timeStamp, nonce);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("account", account);
            jsonObject.put("timestamp", timeStamp);
            jsonObject.put("nonce", nonce);
            jsonObject.put("signature", signature);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
        return api.refreshToken(body);
    }
}
