package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pgroupea03_android.dtos.student.DtoOutputTokenStudent;
import com.example.pgroupea03_android.infrastructure.IStudentRepository;
import com.example.pgroupea03_android.infrastructure.ITeacherRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.model.Login;
import com.example.pgroupea03_android.services.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginStudentActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etMail, etPwd;

    private retrofit2.Retrofit retrofit;
    private IStudentRepository studentClient;
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
        retrofit = Retrofit.getInstance(this);
        studentClient = retrofit.create(IStudentRepository.class);
        sessionManager = new SessionManager(this);
    }

    private void initSession() {
        sessionManager = new SessionManager(this);
    }

    private void initViewInstances() {
        btnLogin = findViewById(R.id.btn_loginStudentActivity_login);
        etMail = findViewById(R.id.et_loginStudentActivity_mail);
        etPwd = findViewById(R.id.et_loginStudentActivity_passwd);
    }

    private void initListeners() {
        btnLogin.setOnClickListener((view)->{login();});

        etMail.setOnClickListener(view -> {
            etMail.setText("StanyVincart@gmail.com");
            etPwd.setText("password1");
        });
    }

    private void login() {
        Login login = new Login(etMail.getText().toString(), etPwd.getText().toString());

        Retrofit.getInstance(this).create(IStudentRepository.class).login(login).enqueue(new Callback<DtoOutputTokenStudent>() {
            @Override
            public void onResponse(Call<DtoOutputTokenStudent> call, Response<DtoOutputTokenStudent> response) {
                if(response.isSuccessful()){
                    Log.d("ici","ici");
                    String token = response.body().getToken();

                    int idStudent = response.body().getIdStudent();

                    sessionManager.saveAuthToken(token);
                    sessionManager.saveAuthId(idStudent);

                    Intent intent = new Intent(LoginStudentActivity.this, StudentActivity.class);
                    startActivity(intent);
                }else{
                    Log.d("ici","la");
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