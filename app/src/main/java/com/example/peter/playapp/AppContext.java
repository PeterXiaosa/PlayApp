package com.example.peter.playapp;

import android.app.Activity;

import com.example.peter.playapp.base.BaseApplication;

import java.util.LinkedList;
import java.util.List;

public class AppContext extends BaseApplication {

    static AppContext instance;
//    private List<Activity> activityList = new LinkedList<Activity>();

    private AppContext()
    {
    }

    public static synchronized AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化换肤框架
//        initSkinCompatManager();
        //写崩溃日志
//        EsCrashHandler.getInstance().init(this);
    }

    private void initSkinCompatManager() {
        //利用换肤框架初始化颜色数据
//        SkinCompatManager.withoutActivity(this)                         // 基础控件换肤初始化
//                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
//                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
//                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
//                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
//                .setSkinWindowBackgroundEnable(false)                   // 关闭windowBackground换肤，默认打开[可选]
//                .loadSkin();
    }
}
