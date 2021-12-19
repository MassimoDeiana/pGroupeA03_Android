package com.example.pgroupea03_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;
import com.example.pgroupea03_android.view.interrogation.InterrogationListFragment;
import com.example.pgroupea03_android.view.interrogation.InterrogationReportListFragment;
import com.example.pgroupea03_android.view.lesson.LessonListFragment;

public class LessonActivity extends AppCompatActivity implements LessonListFragment.onLessonClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
    }

    @Override
    public void onLessonClick(DtoOutputLesson dtoOutputLesson) {

    }
}