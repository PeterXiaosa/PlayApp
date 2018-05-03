package com.example.peter.playapp.Interface;

import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyService {
    @Headers({"Content-Type: application/json","Accept: application/json"})//需要添加头
    @POST("app/login")
//    Observable<UserInfo> login(
//            @Query("phone") String phone,
//            @Query("password") String password
//    );
    Call<ServerBean> postFlyRoute(@Body RequestBody route);//传入的参数为RequestBody
}
