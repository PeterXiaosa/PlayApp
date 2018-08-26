package com.example.peter.playapp.mvp.model;

import com.example.peter.playapp.bean.ProductInfo;
import com.example.peter.playapp.bean.ServerBean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainModel extends ServerBean{

    public List<ProductInfo> getProductList(){
        List<ProductInfo> list = new ArrayList<>();
//        JsonArray jsonArray = null;
        ArrayList arrayList = null;
        if (this.getContent() != null) {
            arrayList = (ArrayList) this.getContent();
        }
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                LinkedTreeMap map = (LinkedTreeMap) arrayList.get(i);
                ProductInfo productInfo = new ProductInfo();
                productInfo.setImage_url((String) map.get("image_url"));
                productInfo.setName((String) map.get("name"));
                productInfo.setPrice((Double) map.get("price"));
                list.add(productInfo);
            }
        }
        return list;
    }
}
