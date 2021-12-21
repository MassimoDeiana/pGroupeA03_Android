package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.teacher.DtoOutputTeacher;
import com.example.pgroupea03_android.dtos.teacher.DtoOutputTokenTeacher;
import com.example.pgroupea03_android.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ITeacherRepository {

    @POST("Teacher/authenticate")
    Call<DtoOutputTokenTeacher> login(@Body Login login);

    @GET("Teacher")
    Call<List<DtoOutputTeacher>> getAll();

    @GET("Teacher/{id}")
    Call<DtoOutputTeacher> getById(int id);
}
