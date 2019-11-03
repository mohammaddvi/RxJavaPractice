package com.practice.rxjavatest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.practice.rxjavatest.Composable.ComposableActivity;


public class MainActivity extends AppCompatActivity {
    private Button composablebBtn, flowableBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        composablebBtn = findViewById(R.id.composable);
        flowableBtn = findViewById(R.id.flowable);
        composablebBtn.setOnClickListener(v -> startActivity(new Intent(this, ComposableActivity.class)));
        flowableBtn.setOnClickListener(v -> startActivity(new Intent(this, FlowableActivity.class)));
    }
}
