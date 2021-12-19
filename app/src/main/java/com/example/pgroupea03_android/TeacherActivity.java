package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class TeacherActivity extends AppCompatActivity {

    private Button btnInterrogations;
    private Button btnCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        initViewInstances();
        initListeners();
    }

    private void initViewInstances() {
        btnInterrogations = findViewById(R.id.btn_teacherActivity_interrogations);
        btnCourses = findViewById(R.id.btn_teacherActivity_courses);
    }

    private void initListeners() {
        btnInterrogations.setOnClickListener(view -> {
            Intent intent = new Intent(TeacherActivity.this, InterrogationActivity.class);
            startActivity(intent);
        });

        btnCourses.setOnClickListener(view -> {
            Intent intent = new Intent(TeacherActivity.this, LessonActivity.class);
            startActivity(intent);
        });
    }
}