package com.example.peter.playapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import com.example.peter.playapp.activity.LoginActivity;
import com.example.peter.playapp.activity.MainActivity;
import com.example.peter.playapp.base.BaseApplication;
import com.example.peter.playapp.util.CertificateUtil;
import com.example.peter.playapp.util.FixDexUtils;

import java.util.LinkedList;
import java.util.List;

public class AppContext extends BaseApplication {

    static AppContext instance;
    private String genKey;
    private String deviceId = "";
    private String accessToken;

    public AppContext() {
    }

    public static synchronized AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        init();
        //初始化换肤框架
//        initSkinCompatManager();
        //写崩溃日志
//        EsCrashHandler.getInstance().init(this);
    }

    private void init() {
        generateGenKey();
        setDeviceId();
    }

    private void generateGenKey() {
        //初始化生成genKey
        SharedPreferences sharedPreferences = getSharedPreferences("genkeyLibrary", MODE_PRIVATE);
        genKey = sharedPreferences.getString("genkey", "");

        if (genKey.trim().equals("")) {
            genKey = CertificateUtil.generaterGenKey();
            genKey = "peter08f8cee142599dd80660d9192a4520f7cadbf247";

            SharedPreferences sp = getSharedPreferences("genkeyLibrary", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("genkey", genKey);
            editor.apply();
        }
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

    public void setDeviceId() {
        String serial = "";
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        String szDevIDShort = "35" + //we make this look like a valid IMEI

                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        String devid = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        if(devid != null) {
            szDevIDShort = szDevIDShort + serial + devid;
        }else {
            szDevIDShort = szDevIDShort + serial;
        }
        this.deviceId = szDevIDShort;
        this.deviceId = "357696505656247e5570b44865736036768169";
    }

    public String getDeviceId(){
        return deviceId;
    }

    public String getGenKey() {
        return genKey;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public String getAccessToken(){
        return accessToken;
    }
}
