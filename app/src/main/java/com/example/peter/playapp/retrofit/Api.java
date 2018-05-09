package com.example.peter.playapp.retrofit;

import com.example.peter.playapp.bean.ContactInfo;
import com.example.peter.playapp.bean.ContactInfoRequest;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Api {
    //baseUrl
    String BASE_URL = "http://47.100.210.98:8080/app";


    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头

    @POST("/certificate/login")
    Call<ServerBean> postFlyRoute(@Body RequestBody route);//传入的参数为RequestBody

    //登录
    @POST("/certificate/login")
    Observable<ServerBean> login(@Body UserInfo userInfo);

    //查询联系人
    @POST("app/contact")
    Observable<List<ContactInfo>> getContacts(@Body ContactInfoRequest contactInfo);

//    @POST("/certificate/regettoken")
//    Observable<>
}
