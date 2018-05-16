package com.example.peter.playapp.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.peter.playapp.R;

public class SimpleHotFixBugTest {
    public void getBug(Context context) {
        int i = 10;
        int a = 0;
        Toast.makeText(context, "修复成功:" + i / a, Toast.LENGTH_SHORT).show();
    }

    public void changePhoto(Context context, ImageView imageView){
//        imageView.setBackground(context.getResources().getDrawable(R.drawable.push));
    }
}

