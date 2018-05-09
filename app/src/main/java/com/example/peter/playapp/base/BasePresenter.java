package com.example.peter.playapp.base;

import android.support.annotation.NonNull;

import com.example.peter.playapp.HttpMethods;
import com.example.peter.playapp.retrofit.Api;
import com.example.peter.playapp.retrofit.ApiClient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

public class BasePresenter <T>{

    @NonNull
    protected T mvpView;

    protected Api api;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(@NonNull T mvpView){
//        this.mvpView = mvpView;
        this.mvpView = checkNotNull(mvpView, "mvpView cannot be null !");
        this.api = ApiClient.retrofit().create(Api.class);
    }

    public void detachView(){
        this.mvpView = null;
        onUnSubscribe();
    }

    private void onUnSubscribe() {
        if (mCompositeDisposable != null && ! mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public void addSubscription(Observable observable, DisposableObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        mCompositeDisposable.add(observer);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
    }
}
