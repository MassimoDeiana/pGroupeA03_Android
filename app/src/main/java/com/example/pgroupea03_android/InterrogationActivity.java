package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.view.interrogation.InterrogationListFragment;
import com.example.pgroupea03_android.view.interrogation.InterrogationReportListFragment;

public class InterrogationActivity extends AppCompatActivity implements InterrogationListFragment.onInterrogationClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interrogation);
    }

    @Override
    public void onInterrogationClick(DtoOutputInterrogation dto) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("interrogationList")
                .replace(R.id.fragment_activityTeacher_container,
                InterrogationReportListFragment.newInstance(1, dto.getIdInterro()), "interrogationReportList")
                .commit();
    }
}