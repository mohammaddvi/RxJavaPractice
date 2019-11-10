package com.practice.rxjavatest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.practice.rxjavatest.Model.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ZipActivity extends AppCompatActivity {
    private Button btnZip;
    private TextView textZip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zip_activity);
        btnZip = findViewById(R.id.btn_zip);
        textZip = findViewById(R.id.command_zip);
        btnZip.setOnClickListener(v -> doSomeWorks());
    }
    /*
     *  here we have two list one list is java fan and another one is kotlin fan
     *  and we want to find out the users love both
     */


    private void doSomeWorks() {
        Observable.zip(getJavaFanObservable(), getKotlinFanObservable(),
                /*Method Reference
                 * third parameter in zip is BiFunctional that is an implementation of interface
                 * that have just one method (apply) so we can use Lambda here.
                 * On the other hand, because filterUseKotlinAndJava have been implemented in Utils
                 * and income argument of apply are equal with filterUserLoveKotlinAndJava income
                 * and output argument of apply are equal with filterUserLoveKotlinAndJava outcome
                 * so we can user Method Reference
                 */
                Utils::filterUserLoveKotlinAndJava)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<List<User>> getJavaFanObservable() {
        //this list can be made by network request or by manual
        //because we define subscribeOn(Schedulers.io()) this observable run in background and parallel
        return Observable.create((ObservableOnSubscribe<List<User>>) emitter -> {
            if (!emitter.isDisposed()) {
                emitter.onNext(Utils.javaFanUserList());
                emitter.onComplete();
            }

        }).subscribeOn(Schedulers.io());
    }

    private Observable<List<User>> getKotlinFanObservable() {
        //this list can be made by network request or by manual
        //because we define subscribeOn(Schedulers.io()) this observable run in background and parallel
        return Observable.create((ObservableOnSubscribe<List<User>>) emitter -> {
            if (!emitter.isDisposed()) {
                emitter.onNext(Utils.kotlinFanOUserList());
                emitter.onComplete();
            }


        }).subscribeOn(Schedulers.io());
    }

    private Observer<List<User>> getObserver() {
        return new Observer<List<User>>() {
            @Override
            public void onSubscribe(Disposable d) {
                textZip.append(" onSubscribe ");
                textZip.append("\n");

            }

            @Override
            public void onNext(List<User> users) {
                textZip.append(" onNext ");
                textZip.append("\n");
                for (User user : users) {
                    textZip.append("firstname " + user.firstname);
                    textZip.append("\n");
                    textZip.append("lastname " + user.lastname);
                    textZip.append("\n");
                }
            }

            @Override
            public void onError(Throwable e) {
                textZip.append(" onError ");
                textZip.append("\n");

            }

            @Override
            public void onComplete() {
                textZip.append(" onComplete ");
                textZip.append("\n");

            }
        };

    }
}
