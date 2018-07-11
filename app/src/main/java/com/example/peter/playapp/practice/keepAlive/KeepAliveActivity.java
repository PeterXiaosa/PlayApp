package com.example.peter.playapp.practice.keepAlive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.peter.playapp.R;

import butterknife.BindView;
import butterknife.OnClick;

public class KeepAliveActivity extends AppCompatActivity {

//    @BindView(R.id.activity_keep_alive_btn_open_service)
    Button btn_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_alive);
        btn_service = findViewById(R.id.activity_keep_alive_btn_open_service);

        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KeepAliveActivity.this, GrayService.class);
                startService(intent);
            }
        });
    }

//    @OnClick(R.id.activity_keep_alive_btn_open_service)
//    public void openService(){
//        Intent intent = new Intent(this, GrayService.class);
//        startService(intent);
//    }
}
