package com.example.peter.playapp.activity;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.peter.playapp.HttpMethods;
import com.example.peter.playapp.R;
import com.example.peter.playapp.base.BaseActivity;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    private ServerBean serverBean;

    @Override
    public void initData() {
//        getData();
    }

    private void onlyRetrofit(){
        //        UserInfo userInfo = new UserInfo();
//        userInfo.setPhone("15216701562");
//        userInfo.setPassword("fhc199508030");
//        Gson gson = new Gson();
//        String route = gson.toJson(userInfo);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
////                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .baseUrl(BASE_URL)
//                .build();
//
//        MyService service = retrofit.create(MyService.class);
//        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), route);
//        Call<ServerBean> call = service.postFlyRoute(body);
//        call.enqueue(new Callback<ServerBean>() {
//            @Override
//            public void onResponse(Call<ServerBean> call, Response<ServerBean> response) {
////                String phone =response.body().getPhone();
//                Log.i(TAG, "successful" + response.body().getContent());
//                Toast.makeText(MainActivity.this, response.body().getContent(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<ServerBean> call, Throwable t) {
//                Log.i(TAG, t.getMessage());
//            }
//        });
    }

    private void getData() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("15216701562");
        userInfo.setPassword("fhc19950803");

//        HttpMethods.getInstance().login(userInfo, new Observer<ServerBean>() {
//            Disposable disposable;
//            @Override
//            public void onSubscribe(Disposable d) {
//                this.disposable = d;
//            }
//
//            @Override
//            public void onNext(ServerBean value) {
//                serverBean = value;
////                String content = serverBean.getContent();
////                Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
