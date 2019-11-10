package com.practice.rxjavatest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.practice.rxjavatest.Model.ApiUser;
import com.practice.rxjavatest.Model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MapActivity extends AppCompatActivity {
    private Button mapBtn;
    private TextView mapText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        mapBtn = findViewById(R.id.btn_map);
        mapText = findViewById(R.id.command_map);
        mapBtn.setOnClickListener(v -> doSomeWorks());
    }

    /*Map
     *   transform the items emitted by an Observable by applying a function to each item
     */
    private void doSomeWorks() {
        getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // method reference in java :: with method reference we can pass a method as an
                // argument of another method(ex:static method)
                .map(Utils::convertApiUserToUser)
                .subscribe(getObserver());
    }


    //<? super List<User>> means that accept List<User> and any superclass.
    //Question: difference <? super List<User>> and <? extend List<User>>
    private Observer<? super List<User>> getObserver() {
        return new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<User> users) {
                mapText.append(" onNext");
                mapText.append("\n");
                for (User user : users) {
                    mapText.append(" firstname : " + user.firstname);
                    mapText.append("\n");
                }
            }

            @Override
            public void onError(Throwable e) {
                mapText.append(" onError : " + e.getMessage());
                mapText.append("\n");
            }

            @Override
            public void onComplete() {
                mapText.append(" onComplete ");
                mapText.append("\n");
            }
        };
    }

    private Observable<List<ApiUser>> getObservable() {
        return Observable.create(new ObservableOnSubscribe<List<ApiUser>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ApiUser>> emitter) throws Exception {
                if (!emitter.isDisposed()) {
                    emitter.onNext(Utils.getApiUserList());
                    emitter.onComplete();
                }
            }
        });
    }
}
