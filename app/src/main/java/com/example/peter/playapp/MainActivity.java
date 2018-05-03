package com.example.peter.playapp;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.peter.playapp.Interface.MyService;
import com.example.peter.playapp.base.BaseActivity;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    private static String BASE_URL = "http://47.100.210.98:8080/";
    private static String TAG = "MainActivity";

    private String phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData() {
        UserInfo userInfo = new UserInfo();
        userInfo.setPhone("15216701562");
        userInfo.setPassword("fhc199508030");
        Gson gson = new Gson();
        String route = gson.toJson(userInfo);

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        MyService service = retrofit.create(MyService.class);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), route);
        Call<ServerBean> call = service.postFlyRoute(body);
        call.enqueue(new Callback<ServerBean>() {
            @Override
            public void onResponse(Call<ServerBean> call, Response<ServerBean> response) {
//                String phone =response.body().getPhone();
                Log.i(TAG, "successful" + response.body().getContent());
                Toast.makeText(MainActivity.this, response.body().getContent(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ServerBean> call, Throwable t) {
                Log.i(TAG, t.getMessage());
            }
        });
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {

    }
}
