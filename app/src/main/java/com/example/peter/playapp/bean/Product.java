package com.example.peter.playapp.bean;

import javax.inject.Inject;

import dagger.Module;

@Module
public class Product {
    @Inject
    public Product(){
    }

    public String make(){
        return "生产";
    }
}
