package com.example.peter.playapp.mvp.presenter;

import com.example.peter.playapp.base.BasePresenter;
import com.example.peter.playapp.bean.ProductInfo;
import com.example.peter.playapp.bean.ServerBean;
import com.example.peter.playapp.bean.UserInfo;
import com.example.peter.playapp.mvp.model.MainModel;
import com.example.peter.playapp.mvp.view.MainView;
import com.example.peter.playapp.retrofit.ApiCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Peter
 */
public class MainPresenter extends BasePresenter<MainView>{

    public MainPresenter(MainView view){
        attachView(view);
    }

    public void getProduct(int type){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("type", type);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, jsonObject.toString());

        addSubscription(api.getProductInfo(body),
                new ApiCallback<MainModel>() {
                    @Override
                    public void onSuccess(MainModel model) {
                        List<ProductInfo> data = model.getProductList();
                        mvpView.getProductSuccess(data);
                    }

                    @Override
                    public void onFailure(String msg) {

                    }

                    @Override
                    public void onFinish() {
                        mvpView.cancelLoading();
                    }
                });
    }

    public void getProduceType(){
//        addSubscription(api.getProductType(), new Function<ServerBean, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(ServerBean serverBean) {
//                MainModel model = (MainModel)serverBean;
//                if (model.getStatus() == 0){
//                    model.saveProductType();
//                    mvpView.getProductTypeSuccess(model.getProductType());
//                    JSONObject jsonObject = new JSONObject();
//                    try {
//                        jsonObject.put("type", 1);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//                    RequestBody body = RequestBody.create(JSON, jsonObject.toString());
//                    return api.getProductInfo(body);
//                }else {
//
//                    return null;
//                }
//            }
//        }, new ApiCallback<MainModel>() {
//            @Override
//            public void onSuccess(MainModel model) {
//                List<ProductInfo> data = model.getProductList();
//                mvpView.getProductSuccess(data);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        });
    }
}
