package com.example.peter.playapp.mvp.model;

import com.example.peter.playapp.bean.ProductInfo;
import com.example.peter.playapp.bean.ProductTypeBean;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.data.ProductTypeData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainModel extends ServerBean{

    // 服务器返回JsonArray的字符串，不知如何转换成JsonArray，现从LinkedTreeMap中转化。
    public List<ProductInfo> getProductList(){
        List<ProductInfo> list = new ArrayList<>();
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

    public void saveProductType(){
        JsonObject object = new JsonObject();
        object.addProperty("name", "1");
        JsonObject object2 = new JsonObject();
        object2.addProperty("name", "2");
        JsonArray array = new JsonArray();
        array.add(object);
        array.add(object2);

        Gson gson = new Gson();
        JsonArray array1 = new JsonParser().parse(array.toString()).getAsJsonArray();

        List<ProductTypeBean> list = new ArrayList<>();
        ArrayList arrayList = null;
        if (this.getContent() != null) {
            arrayList = (ArrayList) this.getContent();
        }
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                LinkedTreeMap map = (LinkedTreeMap) arrayList.get(i);
                ProductTypeBean productTypeBean = new ProductTypeBean();
                productTypeBean.setType(Integer.parseInt(String.valueOf(map.get("type")).substring(0, 1)));
                productTypeBean.setType_name((String) map.get("type_name"));
                productTypeBean.setImg_url((String) map.get("image_url"));
                list.add(productTypeBean);
            }
            ProductTypeData.getInstance().setData(list);
        }
    }

    public List<ProductTypeBean> getProductType(){
        return ProductTypeData.getInstance().getData();
    }

    public List<String> getProductTypeStr(){
        List<ProductTypeBean> list = ProductTypeData.getInstance().getData();
        List<String> stringList = new ArrayList<>();
        for (ProductTypeBean bean : list){
            stringList.add(bean.getType_name());
        }
        return stringList;
    }
}
