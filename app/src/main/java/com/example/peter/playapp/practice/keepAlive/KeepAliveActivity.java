package com.example.peter.playapp.practice.keepAlive;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewManager;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.peter.playapp.R;
import com.example.peter.playapp.practice.keepAlive.jobservice.DaemonService;
import com.example.peter.playapp.practice.keepAlive.jobservice.ScheduleService;
import com.example.peter.playapp.practice.keepAlive.onepx.ScreenReceiver;

import butterknife.BindView;
import butterknife.OnClick;

public class KeepAliveActivity extends AppCompatActivity {

//    @BindView(R.id.activity_keep_alive_btn_open_service)
    Button btn_service;
    Button btn_one_px;
    Button btn_job_service;
    Button mFloatingButton;

    WindowManager.LayoutParams mLayoutParams;
    WindowManager mWindowManager;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep_alive);
        btn_service = findViewById(R.id.activity_keep_alive_btn_open_service);
        btn_one_px = findViewById(R.id.btn_one_px);
        btn_job_service = findViewById(R.id.btn_job_service);

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

        btn_job_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KeepAliveActivity.this, DaemonService.class);
                startService(intent);
            }
        });

        getMainLooper().quit();
        Looper.prepareMainLooper();

        mFloatingButton = new Button(this);
        mFloatingButton.setText("会移动");
        mLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        mWindowManager = getWindowManager();
        mWindowManager.addView(mFloatingButton, mLayoutParams);

        Window window = getWindow();
        mFloatingButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int rawX = (int) event.getRawX();
                int rawY = (int) event.getRawY();
                switch (event.getAction()){
                    case MotionEvent.ACTION_MOVE:{
                        mLayoutParams.x = rawX;
                        mLayoutParams.y = rawY;
                        mWindowManager.updateViewLayout(mFloatingButton, mLayoutParams);
                     break;
                    }
                }
                return false;
            }
        });
    }

//    @OnClick(R.id.activity_keep_alive_btn_open_service)
//    public void openService(){
//        Intent intent = new Intent(this, GrayService.class);
//        startService(intent);
//    }
}
