package com.example.peter.playapp.practice.keepAlive;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.peter.playapp.R;
import com.example.peter.playapp.practice.keepAlive.onepx.ScreenReceiver;

import butterknife.BindView;
import butterknife.OnClick;

public class KeepAliveActivity extends AppCompatActivity {

//    @BindView(R.id.activity_keep_alive_btn_open_service)
    Button btn_service;
    Button btn_one_px;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_alive);
        btn_service = findViewById(R.id.activity_keep_alive_btn_open_service);
        btn_one_px = findViewById(R.id.btn_one_px);

        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KeepAliveActivity.this, GrayService.class);
                startService(intent);
            }
        });

        btn_one_px.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 动态注册开关屏广播
                BroadcastReceiver receiver = new ScreenReceiver();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                intentFilter.addAction("android.intent.action.SCREEN_ON");
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                registerReceiver(receiver, intentFilter);
            }
        });
    }

//    @OnClick(R.id.activity_keep_alive_btn_open_service)
//    public void openService(){
//        Intent intent = new Intent(this, GrayService.class);
//        startService(intent);
//    }
}
