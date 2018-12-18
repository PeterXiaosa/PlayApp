package com.example.peter.playapp.data;

import com.example.peter.playapp.bean.ProductTypeBean;

import java.util.ArrayList;
import java.util.List;

//TODO 菜品种类从服务器获取，同时有updateTime，如果菜品有更新则更新数据，否则利用缓存
public class ProductTypeData {
    private static ProductTypeData instance;
    private  List<ProductTypeBean> mData= new ArrayList<>();

    public static ProductTypeData getInstance() {
        if (instance == null){
            instance = new ProductTypeData();
            initDataList();
        }
        return instance;
    }

    private static void initDataList(){

    }

    public void setData(List<ProductTypeBean> list){
        this.mData.clear();
        this.mData.addAll(list);
    }

    public List<ProductTypeBean> getData(){
        return mData;
    }

    public int getTypeByName(String name){
        int type = -1;
        for (int i = 0; i < mData.size(); i++){
            if (mData.get(i).getType_name().equals(name)){
                type = mData.get(i).getType();
                break;
            }
        }
        return type;
    }
}
