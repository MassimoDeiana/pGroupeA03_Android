package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pgroupea03_android.dtos.interrogation.DtoCreateInterrogation;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;
import com.example.pgroupea03_android.infrastructure.IInterrogationRepository;
import com.example.pgroupea03_android.infrastructure.ILessonRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.services.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterrogationAddActivity extends AppCompatActivity {

    private Spinner spinnerLesson;
    private List<DtoOutputLesson> lessons;
    private List<String> subjects;
    private Map<String, Integer> lessonMap;
    private EditText etSubject;
    private EditText etTotal;
    private List<DtoOutputInterrogation> interrogationList;
    private ImageButton btnValidate;

    private retrofit2.Retrofit retrofit;
    private IInterrogationRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interrogation_add);

        retrofit = Retrofit.getInstance(this);
        repository = retrofit.create(IInterrogationRepository.class);
        initViewInstances();
        initListeners();
    }

    private void initViewInstances() {
        spinnerLesson = findViewById(R.id.spinner_interrogationAddActivity_lesson);
        etSubject = findViewById(R.id.et_interrogationAddActivity_subject);
        etTotal = findViewById(R.id.et_interrogationAddActivity_total);
        btnValidate = findViewById(R.id.btn_interrogationAddActivity_add);

        subjects = new ArrayList<>();
        lessonMap = new HashMap<>();
        interrogationList = new ArrayList<>();

        retrofit.create(ILessonRepository.class).getAll().enqueue(new Callback<List<DtoOutputLesson>>() {
            @Override
            public void onResponse(Call<List<DtoOutputLesson>> call, Response<List<DtoOutputLesson>> response) {
                if(response.code() == 201) {
                    lessons = response.body();

                    for (DtoOutputLesson lesson :
                            lessons) {
                        subjects.add(lesson.getSubject());
                        lessonMap.put(lesson.getSubject(), lesson.getIdLesson());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, subjects);
                    spinnerLesson.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<DtoOutputLesson>> call, Throwable t) {

            }
        });
    }

    private void initListeners() {
        btnValidate.setOnClickListener(view -> {
            String subject = etSubject.getText().toString();
            if( subject.isEmpty()) {
                Toast.makeText(
                        getApplicationContext(),
                        "Please fulfill the subject field.",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            int total;
            try {
                total = Integer.parseInt(etTotal.getText().toString());
            } catch (NumberFormatException e) {
                Toast.makeText(
                        getApplicationContext(),
                        "Please fulfill the total field.",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            int idLesson = lessonMap.get(spinnerLesson.getSelectedItem().toString());
            SessionManager sessionManager = new SessionManager(this);
            int idTeacher = sessionManager.fetchAuthId();
            boolean flag = false;
            repository.getByTeacher(idTeacher).enqueue(new Callback<List<DtoOutputInterrogation>>() {
                @Override
                public void onResponse(Call<List<DtoOutputInterrogation>> call, Response<List<DtoOutputInterrogation>> response) {
                    interrogationList = response.body();
                }

                @Override
                public void onFailure(Call<List<DtoOutputInterrogation>> call, Throwable t) {

                }
            });
            for (DtoOutputInterrogation interrogation :
                    interrogationList) {
                flag = true;
            }
            if(flag) {
                Toast.makeText(
                        getApplicationContext(),
                        "This interrogation already exists.",
                        Toast.LENGTH_LONG
                ).show();
                return;
            }

            DtoCreateInterrogation dto = new DtoCreateInterrogation(idTeacher, idLesson, subject, total);
            repository.create(dto).enqueue(new Callback<DtoOutputInterrogation>() {
                @Override
                public void onResponse(Call<DtoOutputInterrogation> call, Response<DtoOutputInterrogation> response) {
                    Toast.makeText(
                            getApplicationContext(),
                            "The interrogation " + dto.getSubject() + " has been successfully created.",
                            Toast.LENGTH_LONG
                    ).show();
                }

                @Override
                public void onFailure(Call<DtoOutputInterrogation> call, Throwable t) {

                }
            });
        });
    }
}