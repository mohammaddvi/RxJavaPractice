package com.practice.rxjavatest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {
    private Button composablebBtn, flowableBtn, completableBtn, mapBtn, zipBtn, bufferBtn, takeBtn, formValidationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapBtn = findViewById(R.id.map);
        composablebBtn = findViewById(R.id.composable);
        flowableBtn = findViewById(R.id.flowable);
        completableBtn = findViewById(R.id.completable);
        zipBtn = findViewById(R.id.zip);
        bufferBtn = findViewById(R.id.buffer);
        takeBtn = findViewById(R.id.take);
        formValidationBtn = findViewById(R.id.formvalidation);
        composablebBtn.setOnClickListener(v -> startActivity(new Intent(this, ComposableActivity.class)));
        flowableBtn.setOnClickListener(v -> startActivity(new Intent(this, FlowableActivity.class)));
        completableBtn.setOnClickListener(v -> startActivity(new Intent(this, CompletableActivity.class)));
        mapBtn.setOnClickListener(v -> startActivity(new Intent(this, MapActivity.class)));
        zipBtn.setOnClickListener(v -> startActivity(new Intent(this, ZipActivity.class)));
        bufferBtn.setOnClickListener(v -> startActivity(new Intent(this, BufferActivity.class)));
        takeBtn.setOnClickListener(v -> startActivity(new Intent(this, TakeActivity.class)));
        formValidationBtn.setOnClickListener(v -> {
            FormValidationFragment fragment = new FormValidationFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();
        });
    }
}
