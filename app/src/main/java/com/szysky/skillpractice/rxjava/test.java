package com.szysky.skillpractice.rxjava;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by suzeyu on 16/7/22.
 */

public class test {

    private final Context context;

    public test(Context context) {
        this.context = context;
        init();
    }

    private void printLog(String infoStr) {
        Log.d("test", infoStr);
    }


    private void init() {

        //创建观察者    回调领域
        Observer<String> _observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                printLog("completed: 执行");
            }

            @Override
            public void onError(Throwable e) {
                printLog("error: 执行");

            }

            @Override
            public void onNext(String s) {

                printLog("next: " + s+" _线程名字_ "+Thread.currentThread().getName()+" _线程id_ "+Thread.currentThread().getId());
            }
        };

        //创建被观察事物
        Observable<String> _observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                printLog("~~我是被观察者,我有一个时间正在处理,一会就通知观察者--" +" _线程名字_ "+Thread.currentThread().getName()+" _线程id_ "+Thread.currentThread().getId());
                subscriber.onNext("first");
                subscriber.onNext("second");
                subscriber.onNext("three");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        //被观察者 订阅观察者
//        _observable.subscribe(_observer);


        // just方法 被观察者提供了快捷创建事件队列的方法
//        Observable.just("just_one", "just_tow", "just_three").subscribe(_observer);


        // from方法
        String[] _names = {"张三", "李四",  "王五"};
//        Observable.from(_names).subscribe(_observer);


        //打印字符串数组
        Observable.from(_names)
                .map(new Func1<String, ArrayList<String>>() {               //开始变换
                    @Override
                    public ArrayList<String> call(String s) {
                        ArrayList<String> strings = new ArrayList<>();
                        strings.add(s);
                        return strings;
                    }
                })
                .subscribe(new Action1<ArrayList<String>>() {
                    @Override
                    public void call(ArrayList<String> strings) {
                        printLog("输出数组的大小:"+strings.size());

                    }
                });


    }


}
