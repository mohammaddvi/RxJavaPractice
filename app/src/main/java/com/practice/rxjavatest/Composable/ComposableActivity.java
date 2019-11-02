package com.practice.rxjavatest.Composable;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.practice.rxjavatest.R;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ComposableActivity extends AppCompatActivity {
    //in RxJava 2.0 compositeSubscription changed to compositeDisposable
    private final CompositeDisposable disposable = new CompositeDisposable();
    private Button btn;
    private TextView command;

    static Observable<String> sampleObservable() {
        //defer create observable each time you get a new observer
        //callable is a improvement of Runnable, callable is added in Java 1.5
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                SystemClock.sleep(2000);
                Log.d("RxJava : Composable", "I want to defer observable");
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composable_activity);
        btn = findViewById(R.id.btn_composable);
        command = findViewById(R.id.command);
        btn.setOnClickListener(v -> doSomeWorks());
    }

    private void doSomeWorks() {
        disposable.add(sampleObservable()
                //that means observable does his work in io thread
                .subscribeOn(Schedulers.io())
                //that means subscriber needs the data for using in main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onNext(String value) {
                        command.append("on Next : value :" + value);
                        command.append("\n");
                    }

                    @Override
                    public void onError(Throwable e) {
                        command.append(" onErrorrr : " + e.getMessage());
                        command.append("\n");
                    }

                    @Override
                    public void onComplete() {
                        command.append("on Complete");
                        command.append("\n");
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stop sending events
        disposable.clear();
    }
}
