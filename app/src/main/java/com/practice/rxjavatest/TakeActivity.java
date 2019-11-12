package com.practice.rxjavatest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class TakeActivity extends AppCompatActivity {
    private Button btnTake;
    private TextView txtTake;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_activity);
        btnTake = findViewById(R.id.btn_take);
        txtTake = findViewById(R.id.command_take);
        btnTake.setOnClickListener(v -> doSomeWorks());
    }

    private void doSomeWorks() {
        getObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(2)
                .filter(s -> s.length() > 3)
                .subscribe(getObserver());
    }

    private Observer<? super String> getObserver() {
        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                txtTake.append(" onSubscribe ");
                txtTake.append("\n");

            }

            @Override
            public void onNext(String s) {

                txtTake.append(" onNext " + s);
                txtTake.append("\n");

            }

            @Override
            public void onError(Throwable e) {

                txtTake.append(" onError ");
                txtTake.append("\n");

            }

            @Override
            public void onComplete() {

                txtTake.append(" onComplete ");
                txtTake.append("\n");

            }
        };
    }

    private Observable<String> getObservable() {
        return Observable.just("mohammad", "zahra", "amin", "fateme", "shiva", "hossein");
    }
}
