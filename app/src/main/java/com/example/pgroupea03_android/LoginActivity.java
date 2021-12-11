package com.example.pgroupea03_android;

import  androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pgroupea03_android.model.DtoStudent;
import com.example.pgroupea03_android.model.Login;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.model.Teacher;

import org.json.JSONStringer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText etname,etpasswd;
    private String name ,passwd;

    retrofit2.Retrofit retrofit = Retrofit.getInstance();

    TeacherClient teacherClient = retrofit.create(TeacherClient.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btn_loginActivity_login);
        etname = findViewById(R.id.et_loginActivity_name);
        etpasswd = findViewById(R.id.et_loginActivity_passwd);

        btnLogin.setOnClickListener((view)->{getTeacher();});


    }

    private String token;

    private void login() {

        Login login = new Login(etname.getText().toString(),etpasswd.getText().toString());
        Call<Teacher> call = teacherClient.login(login);

        call.enqueue(new Callback<Teacher>() {
            @Override
            public void onResponse(Call<Teacher> call, Response<Teacher> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                }else{
                    Toast.makeText(LoginActivity.this, "login not correct :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Teacher> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Error :(",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getTeacher(){
        Call<List<DtoStudent>> call = teacherClient.getStudent();
        call.enqueue(new Callback<List<DtoStudent>>() {
            @Override
            public void onResponse(Call<List<DtoStudent>> call, Response<List<DtoStudent>> response) {
                Log.d("code","code :"+response.code());
                if(response.isSuccessful()){
                    try {
                        Log.d("ok","ok ");
                        Toast.makeText(LoginActivity.this, "GG", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "token is not correct :(", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DtoStudent>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Error get :(",Toast.LENGTH_SHORT).show();
            }
        });
    }


}