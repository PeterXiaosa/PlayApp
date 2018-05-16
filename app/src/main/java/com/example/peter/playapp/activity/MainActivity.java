package com.example.peter.playapp.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Parcel;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.peter.playapp.HttpMethods;
import com.example.peter.playapp.R;
import com.example.peter.playapp.base.BaseActivity;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.util.FixDexUtils;
import com.example.peter.playapp.util.SimpleHotFixBugTest;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_fix)
    Button btn_fix;
    @BindView(R.id.btn_hot_fix)
    Button btn_hot_fix;
    @BindView(R.id.iv_test)
    ImageView iv_test;

    @Override
    public void initData() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            int REQUEST_EXTERNAL_STORAGE = 1;
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }
    }


    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {

    }

    @OnClick(R.id.btn_fix)
    public void fix(){
        FixDexUtils.loadFixedDex(this, Environment.getExternalStorageDirectory());
    }

    @OnClick(R.id.btn_hot_fix)
    public void hotFix(){
//        Toast.makeText(this, "修复之前", Toast.LENGTH_SHORT).show();
        SimpleHotFixBugTest test = new SimpleHotFixBugTest();
        test.getBug(this);
//        test.changePhoto(this, iv_test);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
