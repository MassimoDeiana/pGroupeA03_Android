package com.example.pgroupea03_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.interrogationreport.DtoOutputInterrogationReport;
import com.example.pgroupea03_android.dtos.note.DtoCreateNote;
import com.example.pgroupea03_android.dtos.note.DtoOutputNote;
import com.example.pgroupea03_android.dtos.student.DtoOutputStudent;
import com.example.pgroupea03_android.infrastructure.IInterrogationReportRepository;
import com.example.pgroupea03_android.infrastructure.INoteRepository;
import com.example.pgroupea03_android.infrastructure.IStudentRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.services.SessionManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoteAddActivity extends AppCompatActivity {

    private Spinner spinnerStudent;
    private List<DtoOutputStudent> students;
    private EditText etResult;
    private EditText etMessage;
    private List<DtoOutputInterrogationReport> interrogationReports;
    private ImageButton btnValidate;
    private retrofit2.Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);

        initViewInstances();
        initListeners();
    }

    /**
     * Méthode permettant d'initialiser les différents attributs de l'activité
     * et d'associer les différents composants aux composants graphiques du layout
     */
    private void initViewInstances() {
        spinnerStudent = findViewById(R.id.spinner_noteAddActivity_student);
        etResult = findViewById(R.id.et_noteAddActivity_result);
        etMessage = findViewById(R.id.et_noteAddActivity_message);
        btnValidate = findViewById(R.id.btn_noteAddActivity_add);

        students = new ArrayList<>();

        retrofit = Retrofit.getInstance(this);

        retrofit.create(IStudentRepository.class).getAll().enqueue(new Callback<List<DtoOutputStudent>>() {
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

    /**
     * Méthode permettant de définir des comportements lorsqu'une action est effectuée sur les différents composants du layout
     */
    private void initListeners() {
        etResult.setOnClickListener(view -> {
            etResult.setText(String.valueOf(0));
            etMessage.setText("message");
        });
        btnValidate.setOnClickListener(view -> {
            //Récupère l'idTeacher stocké dans les préfèrences partagées
            SessionManager sessionManager = new SessionManager(this);
            int idTeacher = sessionManager.fetchAuthId();

            //Récupère l'idStudent de la liste déroulante
            DtoOutputStudent student = (DtoOutputStudent) spinnerStudent.getSelectedItem();
            int idStudent = student.getIdStudent();

            //Récupère l'idInterro passée dans l'intent
            Intent intent = getIntent();
            DtoOutputInterrogation interrogation = intent.getParcelableExtra("interrogation");

            int idInterro = interrogation.getIdInterro();

            retrofit.create(IInterrogationReportRepository.class).getStudentsByInterro(idInterro).enqueue(new Callback<List<DtoOutputInterrogationReport>>() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onResponse(Call<List<DtoOutputInterrogationReport>> call, Response<List<DtoOutputInterrogationReport>> response) {
                    interrogationReports = response.body();

                    //Récupère la date actuelle
                    String dateNote = LocalDateTime.now().toString();

                    //Prend le total de la première interrogationReport de la liste (ils ont tous le même total puisque c'est la même interro)
                    int total = interrogation.getTotal();

                    double result;
                    try {
                        result = Double.parseDouble(etResult.getText().toString());
                        if(result < 0 || result > total) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "Please write a valid result.\nThe result has to be between 0 and " + total + ".",
                                    Toast.LENGTH_LONG
                            ).show();
                            return;
                        }
                    } catch (NumberFormatException e) {
                        Toast.makeText(
                                getApplicationContext(),
                                "Please fulfill the result field.",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }

                    //Gestion des doublons
                    boolean flag = false;
                    for (DtoOutputInterrogationReport interrogation :
                            interrogationReports) {
                        if(interrogation.getIdStudent() == idStudent && interrogation.getIdInterro() == idInterro) {
                            flag = true;
                            break;
                        }
                    }
                    if(flag) {
                        Toast.makeText(
                                getApplicationContext(),
                                "This note already exists.",
                                Toast.LENGTH_LONG
                        ).show();
                        return;
                    }

                    String message = etMessage.getText().toString().trim();

                    //Message pas obligatoire
                    DtoCreateNote note;
                    if(message.isEmpty()) {
                        note = new DtoCreateNote(idTeacher, idStudent, idInterro, dateNote, result, "");
                    } else {
                        note = new DtoCreateNote(idTeacher, idStudent, idInterro, dateNote, result, message);
                    }

                    retrofit.create(INoteRepository.class).create(note).enqueue(new Callback<DtoOutputNote>() {
                        @Override
                        public void onResponse(Call<DtoOutputNote> call, Response<DtoOutputNote> response) {
                            Toast.makeText(
                                    getApplicationContext(),
                                    "The note has been successfully added.",
                                    Toast.LENGTH_LONG
                            ).show();
                        }

                        @Override
                        public void onFailure(Call<DtoOutputNote> call, Throwable t) {

                        }
                    });

                }

                @Override
                public void onFailure(Call<List<DtoOutputInterrogationReport>> call, Throwable t) {

                }
            });
        });
    }
}