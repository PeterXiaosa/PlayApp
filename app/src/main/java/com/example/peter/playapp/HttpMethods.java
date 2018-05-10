package com.example.peter.playapp;

import com.example.peter.playapp.mvp.model.LoginModel;
import com.example.peter.playapp.retrofit.Api;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// 这个类写的挺好的留着学习，虽然没用着......
public class HttpMethods {
    private static String BASE_URL = "http://47.100.210.98:8080/app";
    private static final int TIME_OUT = 4;
    private Retrofit retrofit;
    private Api api;

    private HttpMethods(){
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    private static class sinalInstance{
        public static final HttpMethods instance = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return sinalInstance.instance;
    }

    public void login(UserInfo userInfo, Observer<LoginModel> observer){
        api.login(userInfo)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
