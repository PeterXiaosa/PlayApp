package com.example.peter.playapp.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {
    private static boolean isShow = true;
    private static Toast mToast = null;
    private static int defaultGravity = Gravity.CENTER;

    private ToastUtil(){
        throw new UnsupportedOperationException("cannot instantiate");
    }

    public static void controlShow(boolean isShowToast){
        isShow = isShowToast;
    }

    public void cancelToast(){
        if (isShow && mToast != null){
            mToast.cancel();
        }
    }

    public static void show(Context context, CharSequence message){
        if (isShow){
            if (mToast == null){
                mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            }else {
                mToast.setText(message);
            }
            mToast.setGravity(defaultGravity, 0, 0);
            mToast.show();
        }
    }

    public static void show(Context context, int resId){
        if (isShow){
            if (mToast == null){
                mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
            }else {
                mToast.setText(resId);
            }
            mToast.setGravity(defaultGravity, 0, 0);
            mToast.show();
        }
    }

    public static void showLong(Context context, CharSequence message){
        if (isShow){
            if (mToast == null){
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            }else {
                mToast.setText(message);
            }
            mToast.setGravity(defaultGravity, 0, 0);
            mToast.show();
        }
    }

    @SuppressLint("ShowToast")
    public static void showLong(Context context, int resId){
        if (isShow){
            if (mToast == null){
                mToast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
            }else {
                mToast.setText(resId);
            }
            mToast.setGravity(defaultGravity, 0, 0);
            mToast.show();
        }
    }
}


