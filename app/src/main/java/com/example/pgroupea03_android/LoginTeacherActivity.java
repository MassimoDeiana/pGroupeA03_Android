package com.example.pgroupea03_android;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pgroupea03_android.dtos.teacher.DtoOutputTokenTeacher;
import com.example.pgroupea03_android.infrastructure.ITeacherRepository;
import com.example.pgroupea03_android.model.Login;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.services.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginTeacherActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etMail, etPwd;

    private retrofit2.Retrofit retrofit;
    private ITeacherRepository teacherClient;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher);

        initRetrofit();
        initViewInstances();
        initListeners();
    }

    private void initRetrofit() {
        retrofit = Retrofit.getInstance(this);
        teacherClient = retrofit.create(ITeacherRepository.class);
        sessionManager = new SessionManager(this);
    }

    private void initViewInstances() {
        btnLogin = findViewById(R.id.btn_loginTeacherActivity_login);
        etMail = findViewById(R.id.et_loginTeacherActivity_mail);
        etPwd = findViewById(R.id.et_loginTeacherActivity_passwd);
    }

    private void initListeners() {
        btnLogin.setOnClickListener((view)->{login();});

        etMail.setOnClickListener(view -> {
            etMail.setText("ThierryRoland@gmail.com");
            etPwd.setText("password1");
        });
    }

    private void login() {
        Login login = new Login(etMail.getText().toString(), etPwd.getText().toString());
        Call<DtoOutputTokenTeacher> call = teacherClient.login(login);

        call.enqueue(new Callback<DtoOutputTokenTeacher>() {
            @Override
            public void onResponse(Call<DtoOutputTokenTeacher> call, Response<DtoOutputTokenTeacher> response) {
                if(response.isSuccessful()){
                    String token = response.body().getToken();

                    int idTeacher = response.body().getIdTeacher();

                    sessionManager.saveAuthToken(idTeacher + token);
System.out.println(token);
                    Intent intent = new Intent(LoginTeacherActivity.this, TeacherActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginTeacherActivity.this, "login not correct :(", Toast.LENGTH_SHORT).show();
                    System.out.println("login not correct :(");
                }
            }

            @Override
            public void onFailure(Call<DtoOutputTokenTeacher> call, Throwable t) {
                Toast.makeText(LoginTeacherActivity.this,"Error :(",Toast.LENGTH_SHORT).show();
            }
        });
    }
}