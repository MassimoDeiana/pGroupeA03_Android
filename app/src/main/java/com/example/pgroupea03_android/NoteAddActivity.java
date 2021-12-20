package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;
import com.example.pgroupea03_android.dtos.note.DtoOutputNote;
import com.example.pgroupea03_android.dtos.student.DtoOutputStudent;
import com.example.pgroupea03_android.infrastructure.INoteRepository;
import com.example.pgroupea03_android.infrastructure.IStudentRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.services.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteAddActivity extends AppCompatActivity {

    private Spinner spinnerStudent;
    private List<DtoOutputStudent> students;
    private EditText etDate;
    private EditText etResult;
    private EditText etMessage;
    private List<DtoOutputNote> noteList;
    private ImageButton btnValidate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        initViewInstances();
        initListeners();
    }

    private void initViewInstances() {
        spinnerStudent = findViewById(R.id.spinner_interrogationAddActivity_student);
        etDate = findViewById(R.id.et_interrogationAddActivity_date);
        etResult = findViewById(R.id.et_noteAddActivity_result);
        etMessage = findViewById(R.id.et_noteAddActivity_message);
        btnValidate = findViewById(R.id.btn_noteAddActivity_add);

        students = new ArrayList<>();
        noteList = new ArrayList<>();

        Retrofit.getInstance(this).create(IStudentRepository.class).getAll().enqueue(new Callback<List<DtoOutputStudent>>() {
            @Override
            public void onResponse(Call<List<DtoOutputStudent>> call, Response<List<DtoOutputStudent>> response) {
                students = response.body();

                ArrayAdapter<DtoOutputStudent> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, students);
                spinnerStudent.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<DtoOutputStudent>> call, Throwable t) {

            }
        });
    }

    private void initListeners() {
        btnValidate.setOnClickListener(view -> {
            DtoOutputStudent student = (DtoOutputStudent)spinnerStudent.getSelectedItem();
            int idStudent = student.getIdStudent();
//TODO AJOUTER SPINNER INTERROGATION
            SessionManager sessionManager = new SessionManager(this);
            int idTeacher = sessionManager.fetchAuthId();
        });
    }
}