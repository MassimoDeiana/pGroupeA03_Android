package com.example.pgroupea03_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;
import com.example.pgroupea03_android.view.interrogation.InterrogationListFragment;
import com.example.pgroupea03_android.view.interrogation.InterrogationReportListFragment;
import com.example.pgroupea03_android.view.lesson.LessonListFragment;

public class LessonActivity extends AppCompatActivity {

    private ImageButton btnList;
    private ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        initViewInstances();
        initListeners();
    }

    private void initViewInstances() {
        btnList = findViewById(R.id.btn_lessonActivity_list);
        btnAdd = findViewById(R.id.btn_lessonActivity_add);
    }

    private void initListeners() {
        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(LessonActivity.this, LessonListActivity.class);
            startActivity(intent);
        });

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(LessonActivity.this, LessonAddActivity.class);
            startActivity(intent);
        });
    }
}