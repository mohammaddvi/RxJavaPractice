package com.practice.rxjavatest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BufferActivity extends AppCompatActivity {
    private Button bufferBtn;
    private TextView bufferText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buffer_activity);
        bufferBtn = findViewById(R.id.btn_buffer);
        bufferText = findViewById(R.id.command_buffer);
        bufferBtn.setOnClickListener(v -> doSomeWorks());
    }

    private void doSomeWorks() {
        /*Buffer
         *  periodically gather items emitted by an Observable into bundles
         *  and emit these bundles rather than emitting the items one at a time
         *  buffer(3,1) -> count = 3 and skip = 1
         *  Event stream = 123456789
         *  on next : (123) , (234) , (345) , (456) , (567) , (678) , (789)
         */
        Observable<List<String>> buffered = getObservable().buffer(3, 1);
        buffered.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observer<? super List<String>> getObserver() {
        return new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                bufferText.append(" onSubscribe ");
                bufferText.append("\n");

            }

            @Override
            public void onNext(List<String> strings) {
                for (String s : strings) {
                    bufferText.append(" onNext " + s);
                    bufferText.append("\n");
                }
            }

            @Override
            public void onError(Throwable e) {
                bufferText.append(" onError ");
                bufferText.append("\n");
            }

            @Override
            public void onComplete() {
                bufferText.append(" onComplete ");
                bufferText.append("\n");
            }
        };
    }

    private Observable<String> getObservable() {
        return Observable.just("mohammad", "mojtaba", "narges", "fateme", "farzane", "pouriya");
    }
}
