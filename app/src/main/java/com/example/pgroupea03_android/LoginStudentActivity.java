package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pgroupea03_android.dtos.student.DtoOutputTokenStudent;
import com.example.pgroupea03_android.infrastructure.IStudentRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.model.Login;
import com.example.pgroupea03_android.services.SessionManager;
import com.example.pgroupea03_android.services.UserType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginStudentActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etMail, etPwd;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);

        initRetrofit();
        initViewInstances();
        initListeners();
    }

    private void initRetrofit() {
        sessionManager = new SessionManager(this);
    }

    /**
     * Méthode permettant d'initialiser les différents attributs de l'activité
     * et d'associer les différents composants aux composants graphiques du layout
     */
    private void initViewInstances() {
        btnLogin = findViewById(R.id.btn_loginStudentActivity_login);
        etMail = findViewById(R.id.et_loginStudentActivity_mail);
        etPwd = findViewById(R.id.et_loginStudentActivity_passwd);
    }

    /**
     * Méthode permettant de définir des comportements lorsqu'une action est effectuée sur les différents composants du layout
     */
    private void initListeners() {
        btnLogin.setOnClickListener((view)->{login();});

        etMail.setOnClickListener(view -> {
            etMail.setText("StanyVincart@gmail.com");
            etPwd.setText("password1");
        });
    }

    private void login() {
        Login login = new Login(etMail.getText().toString().trim(), etPwd.getText().toString());

        Retrofit.getInstance(getApplicationContext()).create(IStudentRepository.class).login(login).enqueue(new Callback<DtoOutputTokenStudent>() {
            @Override
            public void onResponse(Call<DtoOutputTokenStudent> call, Response<DtoOutputTokenStudent> response) {
                if(response.isSuccessful()){
                    String token = response.body().getToken();

                    int idStudent = response.body().getIdStudent();

                    sessionManager.saveAuthToken(token);
                    sessionManager.saveAuthId(idStudent);
                    sessionManager.saveAuthType(UserType.STUDENT);

                    Intent intent = new Intent(LoginStudentActivity.this, StudentActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginStudentActivity.this, "login not correct :(", Toast.LENGTH_SHORT).show();
                    System.out.println("login not correct :(");
                }
            }

            @Override
            public void onFailure(Call<DtoOutputTokenStudent> call, Throwable t) {
                Toast.makeText(LoginStudentActivity.this,"Error :(",Toast.LENGTH_SHORT).show();
            }
        });
    }
}