package com.example.peter.playapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.peter.playapp.Interface.BaseViewInterface;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener,BaseViewInterface {

    private boolean _isVisible;

    public final int TITLE_LIMIT_NUM = 30;
    public final int CONTENT_LIMIT_NUM = 4000;
    public final int SAVE_LIMIT_IN_TODO = 50;
    public final int CONTENT_LIMIT_IN_TASKINFO = 500;
    public final int CONTENT_LIMIT_IN_VISITADDCONTENT = 300;

    private ProgressDialog waitDialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        ButterKnife.reset(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        AppManager.getAppManager().addActivity(this);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }

        // 通过注解绑定控件
//        ButterKnife.inject(this);
        ButterKnife.bind(this);

        //透明状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }

        initData();
        initView();

        _isVisible = true;
    }

    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    protected String jsonTokener(String in) {
//        if (in != null && in.startsWith("\ufeff")) {
//            in = in.substring(1);
//        }
//        return in;
//    }

//    protected void showDialog() {
//        if (waitDialog == null) {
//            waitDialog = DialogHelp.getWaitDialog(this, getString(R.string.saving));
//        }
//        waitDialog.setCancelable(false);
//        waitDialog.setCanceledOnTouchOutside(false);
//        waitDialog.show();
//    }

//    protected void showDialog(String msg) {
//        if (waitDialog == null) {
//            waitDialog = DialogHelp.getWaitDialog(this, msg);
//        }
//        waitDialog.show();
//    }

//    protected void hideDialog() {
//        if (waitDialog != null) {
//            waitDialog.dismiss();
//        }
//    }
}