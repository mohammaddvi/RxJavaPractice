package com.practice.rxjavatest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CompletableActivity extends AppCompatActivity {
    private Button completableButt;
    private TextView completableText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completable_activity);
        completableButt = findViewById(R.id.btn_completable);
        completableText = findViewById(R.id.command_completable);
        completableButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomeWorks();
            }
        });
    }

    private void doSomeWorks() {
        /*Completable

         *   Completable behaves similarly to Observable except that it can only emit either a completion or
         *   error signal (there is no onNext or onSuccess as with the other reactive types).
         *   Completable is like void method there is no specific return.

         */
        Completable completable = Completable.timer(1000, TimeUnit.MILLISECONDS);
        completable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getCompletableObserver());
    }

    private CompletableObserver getCompletableObserver() {
        return new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                completableText.append(" onSubscribe ");
                completableText.append("\n");

            }

            @Override
            public void onComplete() {

                completableText.append(" onComplete ");
                completableText.append("\n");

            }

            @Override
            public void onError(Throwable e) {

                completableText.append(" onError : error : " + e.getMessage());
                completableText.append("\n");


            }
        };
    }
}
