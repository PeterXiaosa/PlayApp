package com.example.peter.playapp.data;

import java.util.ArrayList;
import java.util.List;

//TODO 菜品种类从服务器获取，同时有updateTime，如果菜品有更新则更新数据，否则利用缓存
public class ProductTypeData {
    private static ProductTypeData instance;
    private static List<String> mData= new ArrayList<>();

    public static ProductTypeData getInstance() {
        if (instance == null){
            instance = new ProductTypeData();
            initDataList();
        }
        return instance;
    }

    private static void initDataList(){
        mData.add("蔬菜");
        mData.add("肉类");
    }

    public List<String> getData(){
        return mData;
    }

    public int getTypeByName(String name){
        int type = -1;
        switch (name){
            case "蔬菜":
                type = 1;
                break;
            case "肉类":
                type = 2;
                break;
            default:
                break;
        }
        return type;
    }
}
