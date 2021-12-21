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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterrogationAddActivity extends AppCompatActivity {

    private static final int TOTAL_MIN = 0;
    private static final int TOTAL_MAX = 100;
    private Spinner spinnerLesson;
    private List<DtoOutputLesson> lessons;
    private int idTeacher;
    private EditText etSubject;
    private EditText etTotal;
    private ImageButton btnValidate;

    private List<DtoOutputInterrogation> interrogationList;

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

        interrogationList = new ArrayList<>();

        retrofit.create(ILessonRepository.class).getAll().enqueue(new Callback<List<DtoOutputLesson>>() {
            @Override
            public void onResponse(Call<List<DtoOutputLesson>> call, Response<List<DtoOutputLesson>> response) {
                if(response.code() == 201) {
                    lessons = response.body();

                    ArrayAdapter<DtoOutputLesson> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_item, lessons);
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
            SessionManager sessionManager = new SessionManager(this);
            idTeacher = sessionManager.fetchAuthId();

            DtoOutputLesson lesson = (DtoOutputLesson) spinnerLesson.getSelectedItem();
            int idLesson = lesson.getIdLesson();

            repository.getByTeacher(idTeacher).enqueue(new Callback<List<DtoOutputInterrogation>>() {
                @Override
                public void onResponse(Call<List<DtoOutputInterrogation>> call, Response<List<DtoOutputInterrogation>> response) {
                    interrogationList = response.body();

                    String subject = etSubject.getText().toString().trim();
                    if (subject.isEmpty()) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Please fulfill the subject field.",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }

                    int total;
                    try {
                        int tmp = Integer.parseInt(etTotal.getText().toString());
                        if (tmp > TOTAL_MIN && tmp <= TOTAL_MAX) {
                            total = tmp;
                        } else {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Please fulfill the total field.\nThe total has to be between " + TOTAL_MIN + " and " + TOTAL_MAX + ".",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }

                    boolean flag = false;
                    for (DtoOutputInterrogation interrogation :
                            interrogationList) {
                        if (interrogation.getSubject().equals(subject) && interrogation.getIdLesson() == idLesson) {
                            flag = true;
                            break;
                        }
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
                }

                @Override
                public void onFailure(Call<List<DtoOutputInterrogation>> call, Throwable t) {

                }
            });
        });
    }
}