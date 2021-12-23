package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pgroupea03_android.dtos.lesson.DtoCreateLesson;
import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;
import com.example.pgroupea03_android.infrastructure.ILessonRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonAddActivity extends AppCompatActivity {

    private EditText etSubject;
    private ImageButton btnValidate;

    private List<DtoOutputLesson> lessonList;

    private ILessonRepository lessonRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_add);

        initViewInstances();
        initListeners();
    }

    /**
     * Méthode permettant d'initialiser les différents attributs de l'activité
     * et d'associer les différents composants aux composants graphiques du layout
     */
    private void initViewInstances() {
        etSubject = findViewById(R.id.et_lessonAddActivity_subject);
        btnValidate = findViewById(R.id.btn_lessonAddActivity_add);

        lessonList = new ArrayList<>();

        lessonRepository = Retrofit.getInstance(this).create(ILessonRepository.class);
    }

    /**
     * Méthode permettant de définir des comportements lorsqu'une action est effectuée sur les différents composants du layout
     */
    private void initListeners() {
        btnValidate.setOnClickListener(view -> {
            lessonRepository.getAll().enqueue(new Callback<List<DtoOutputLesson>>() {
                @Override
                public void onResponse(Call<List<DtoOutputLesson>> call, Response<List<DtoOutputLesson>> response) {
                    lessonList = response.body();

                    //Validation du sujet
                    String subject = etSubject.getText().toString().trim();
                    if (subject.isEmpty()) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Please fulfill the subject field.",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }

                    //Gestion des doublons
                    boolean flag = false;
                    for (DtoOutputLesson lesson :
                            lessonList) {
                        if (lesson.getSubject().equals(subject)) {
                            flag = true;
                            break;
                        }
                    }
                    if(flag) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This lesson already exists.",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }

                    DtoCreateLesson dto = new DtoCreateLesson(subject);

                    lessonRepository.create(dto).enqueue(new Callback<DtoOutputLesson>() {
                        @Override
                        public void onResponse(Call<DtoOutputLesson> call, Response<DtoOutputLesson> response) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "The lesson " + dto.getSubject() + " has been successfully created.",
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                        @Override
                        public void onFailure(Call<DtoOutputLesson> call, Throwable t) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<List<DtoOutputLesson>> call, Throwable t) {

                }
            });
        });
    }
}