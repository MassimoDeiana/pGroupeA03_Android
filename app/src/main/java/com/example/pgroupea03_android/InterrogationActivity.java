package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class InterrogationActivity extends AppCompatActivity {

    private ImageButton btnList;
    private ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interrogation);

        initViewInstances();
        initListeners();
    }

    private void initViewInstances() {
        btnList = findViewById(R.id.btn_interrogationActivity_list);
        btnAdd = findViewById(R.id.btn_interrogationActivity_add);
    }

    private void initListeners() {
        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(InterrogationActivity.this, InterrogationListActivity.class);
            startActivity(intent);
        });

        btnAdd.setOnClickListener(view -> {
            //Intent intent = new Intent(InterrogationActivity.this, InterrogationListActivity.class);
            //startActivity(intent);
        });
    }
}