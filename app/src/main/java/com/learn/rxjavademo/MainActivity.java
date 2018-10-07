package com.learn.rxjavademo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.create(
                new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        emitter.onNext(1);
//                        emitter.onError(new Throwable("test error"));
                        emitter.onComplete();
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(/*new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e("avvds","integer = " + integer);
                    }
                }*/new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("avvds","onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("avvds","integer = " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("avvds","onError = " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("avvds","onComplete");
                    }
                });
    }
}
