package com.example.peter.playapp.Interface;

import com.example.peter.playapp.activity.LoginActivity;
import com.example.peter.playapp.bean.Product;

import dagger.Component;

@Component
public interface LoginActivityComponent {
    void inject(LoginActivity loginActivity);
}
