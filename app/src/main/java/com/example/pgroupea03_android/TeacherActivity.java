package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity {

    private Button btnInterrogations;
    private Button btnLessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        initViewInstances();
        initListeners();
    }

    private void initViewInstances() {
        btnInterrogations = findViewById(R.id.btn_teacherActivity_interrogations);
        btnLessons = findViewById(R.id.btn_teacherActivity_lessons);
    }

    private void initListeners() {
        btnInterrogations.setOnClickListener(view -> {
            Intent intent = new Intent(TeacherActivity.this, InterrogationActivity.class);
            startActivity(intent);
        });

        btnLessons.setOnClickListener(view -> {
            Intent intent = new Intent(TeacherActivity.this, LessonActivity.class);
            startActivity(intent);
        });
    }
}