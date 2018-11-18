package com.example.peter.playapp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.MessageQueue;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.peter.playapp.R;
import com.example.peter.playapp.adapter.ProductDetailAdapter;
import com.example.peter.playapp.adapter.ProductTypeAdapter;
import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.bean.ProductInfo;
import com.example.peter.playapp.data.ProductTypeData;
import com.example.peter.playapp.mvp.MvpActivity;
import com.example.peter.playapp.mvp.model.MainModel;
import com.example.peter.playapp.mvp.presenter.MainPresenter;
import com.example.peter.playapp.mvp.view.MainView;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends MvpActivity<MainPresenter> implements MainView {

    @BindView(R.id.activity_main_rv_type)
    RecyclerView rv_type;
    @BindView(R.id.activity_main_rv_product)
    RecyclerView rv_product;

    ProductTypeAdapter mProductTypeAdapter;
    ProductDetailAdapter mProductDetailAdapter;

    final String TAG = "rxJava";

    @Override
    public void initView() {
        Glide.with(this).load("").into(new ImageView(this));
    }


    @Override
    public void initData() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            int REQUEST_EXTERNAL_STORAGE = 1;
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }

        initTypeRecyclerView();
    }

    private void initTypeRecyclerView() {
        mProductTypeAdapter = new ProductTypeAdapter(this, ProductTypeData.getInstance().getData());
        mProductDetailAdapter = new ProductDetailAdapter(this, null);

        mProductTypeAdapter.setListener(new ProductTypeAdapter.onClickType() {
            @Override
            public void clickTypeItem(String typeName) {
                presenter.getProduct(ProductTypeData.getInstance().getTypeByName(typeName));
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_type.setLayoutManager(layoutManager);
        rv_type.setAdapter(mProductTypeAdapter);

        LinearLayoutManager detailLayoutManager = new LinearLayoutManager(this);
        rv_product.setLayoutManager(detailLayoutManager);
        rv_product.setAdapter(mProductDetailAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reflect(String methodName) {

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void getProductSuccess(List<ProductInfo> productInfoList) {
        mProductDetailAdapter.setData(productInfoList);
        mProductDetailAdapter.notifyDataSetChanged();
    }

    @Override
    public void getProductFail(String msg) {

    }

    @Override
    public void getProductType() {

    }
}

