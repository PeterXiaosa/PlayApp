package com.example.peter.playapp.retrofit;

import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.model.RegisterModel;
import com.example.peter.playapp.mvp.model.LoginModel;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    //baseUrl
    String BASE_URL = "http://192.168.18.73:8080/untitled/";
//    String BASE_URL = "http://47.100.210.98:8080/app/";

    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头

    //登录
    @POST("certificate/login")
    Observable<LoginModel> login(@Body UserInfo userInfo);

    //登录
    @POST("user/register")
    Observable<RegisterModel> register(@Body UserInfo userInfo);

    //TODO RequestBody传入服务器的参数不对，服务器无法接受到Json格式，不想创建JavaBean的前提下需要使用RequetBody。
    //TODO 使用@FieldMap需要传入HashMap，同时需要添加注解@FormUrlEncoded。
    //刷新AccessToken
    @POST("certificate/regettoken")
    Observable<ServerBean> refreshToken(@Body RequestBody body);

    // 获取产品信息
    @POST("certificate/regettoken")
    Observable<ServerBean> getProductInfo(@Body RequestBody body);
}