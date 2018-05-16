package com.example.peter.playapp.bean;

import javax.inject.Inject;

public class Factory {
    Product product;

    @Inject
    public Factory(Product product){
        this.product = product;
    }

    public String show(){
        return product.make();
    }
}
