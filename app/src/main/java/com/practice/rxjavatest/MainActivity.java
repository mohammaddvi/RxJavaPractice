package com.practice.rxjavatest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button composablebBtn, flowableBtn, completableBtn, mapBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapBtn = findViewById(R.id.map);
        composablebBtn = findViewById(R.id.composable);
        flowableBtn = findViewById(R.id.flowable);
        completableBtn = findViewById(R.id.completable);
        composablebBtn.setOnClickListener(v -> startActivity(new Intent(this, ComposableActivity.class)));
        flowableBtn.setOnClickListener(v -> startActivity(new Intent(this, FlowableActivity.class)));
        completableBtn.setOnClickListener(v -> startActivity(new Intent(this, CompletableActivity.class)));
        mapBtn.setOnClickListener(v -> startActivity(new Intent(this, MapActivity.class)));
    }
}
