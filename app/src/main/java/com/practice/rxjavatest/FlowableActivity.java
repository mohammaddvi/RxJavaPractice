package com.practice.rxjavatest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.Flowable;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

public class FlowableActivity extends AppCompatActivity {
    //choose meaningful and funny variable that makes you happy
    private Button flowableButt;
    private TextView flowableText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowable_activity);
        flowableButt = findViewById(R.id.flowable_btn);
        flowableText = findViewById(R.id.flowable_command);
        flowableButt.setOnClickListener(v -> {
            doSomeWorks();
        });
    }

    private void doSomeWorks() {
        /* FLOWABLE
         *   Flowable is like observable but flowable has back pressure so it can handle situations
         *   that creation events are faster than consuming them.
         *   Backpressure is when your observable (publisher) is creating more events than
         *   your subscriber can handle.
         *   application : touch screen, network accesor ,...
         */

        /* JUST
         *   just is an operator in RxJava that coverts an item into an observable that emits the item.
         *   you can pass maximally 10 parameters to just.
         *   if you pass null in just what happen?empty observable or an observable that emits null
         *   as an item?
         *   answer:second one.
         */

        Flowable<Integer> observable = Flowable.just(1, 2, 3, 4);

        /*REDUCE
         *   The Reduce operator applies a function to the first item emitted by the source Observable
         *   and then feeds the result of the function back into the function along with the second
         *   item emitted by the source Observable
         *   reduce has two implementations with one parameter 1)seed and two parameter
         *   1)seed and 2)BiFunction.
         *   BiFunction: is an interface for doing some things on two things and return result or
         *   throw an exception
         */
        observable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        }).subscribe(getObserver());
    }
    /*SingleObserver
     *   Subscribing a SingleObserver to multiple SingleSources is not recommended.
     *   The Single class implements the SingleSource base interface and the default consumer type
     *   it interacts with is the SingleObserver via the subscribe(SingleObserver) method.
     */

    private SingleObserver<Integer> getObserver() {
        return new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                flowableText.append("on Subscribe");
                flowableText.append("\n");

            }

            @Override
            public void onSuccess(Integer integer) {
                flowableText.append("on Success : value : " + integer);
                flowableText.append("\n");
            }

            @Override
            public void onError(Throwable e) {
                flowableText.append("on Error : error is : "+e.getMessage());
                flowableText.append("\n");

            }
        };
    }
}
