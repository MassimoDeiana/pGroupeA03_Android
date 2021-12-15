package com.example.pgroupea03_android;

import com.example.pgroupea03_android.model.DtoStudent;
import com.example.pgroupea03_android.model.Login;
import com.example.pgroupea03_android.model.Teacher;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TeacherClient {

    @POST("Teacher/authenticate")
    Call<Teacher> login(@Body Login login);

    @GET("Teacher")
    Call<ResponseBody> getTeacher(@Header("Authorization") String authToken);


}
