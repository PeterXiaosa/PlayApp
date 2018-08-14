package com.example.peter.playapp.retrofit;

import com.example.peter.playapp.bean.ContactInfo;
import com.example.peter.playapp.bean.ContactInfoRequest;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.model.LoginModel;
import com.example.peter.playapp.mvp.model.RegisterModel;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface Api {
    //baseUrl
    String BASE_URL = "http://192.168.18.7:8080/untitled/";
//    String BASE_URL = "http://47.100.210.98:8080/app/";

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头

    //登录
    @POST("certificate/login")
    Observable<LoginModel> login(@Body UserInfo userInfo);

    //登录
    @POST("user/register")
    Observable<RegisterModel> register(@Body UserInfo userInfo);
}
