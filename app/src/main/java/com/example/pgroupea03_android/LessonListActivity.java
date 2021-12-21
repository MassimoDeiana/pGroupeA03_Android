package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;
import com.example.pgroupea03_android.view.lesson.LessonListFragment;
import com.example.pgroupea03_android.view.student.ResultListFragment;

public class LessonListActivity extends AppCompatActivity implements LessonListFragment.onLessonClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_list);
    }

    @Override
    public void onLessonClick(DtoOutputLesson dto) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("lessonList")
                .replace(R.id.fragment_activityTeacher_container,
                        ResultListFragment.newInstance(1, dto.getIdLesson()), "resultReportList")
                .commit();
    }
}