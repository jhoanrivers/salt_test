package com.example.salt.ui.main_activity;


import android.util.Log;


import com.example.salt.model.NewsResponse;
import com.example.salt.network.NetworkClient;
import com.example.salt.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class MainPresenter implements MainPresenterInterface{

    MainViewInterface mvi;
    private String TAG = "MainPresenter";

    public MainPresenter(MainViewInterface mvi){
        this.mvi = mvi;
    }

    @Override
    public void getNews(){
        getObservable().subscribeWith(getObserver());
    }

    public Observable<NewsResponse> getObservable(){
        mvi.displayLoading(true);

        Log.e("terpanggil","Observable");
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMovies("us","e01f38652256427482b14865e3831b0b")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    public DisposableObserver<NewsResponse> getObserver(){
        return new DisposableObserver<NewsResponse>() {
            @Override
            public void onNext(NewsResponse movieResponse) {
                Log.e("Entah","OnNext"+movieResponse.getTotalResults());
                mvi.displayLoading(false);
                mvi.displayNews(movieResponse);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"Error" + e);
                e.printStackTrace();
                mvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.e(TAG,"Completed");

            }
        };
    }





}
