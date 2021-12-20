package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.student.DtoOutputStudent;
import com.example.pgroupea03_android.dtos.student.DtoOutputTokenStudent;
import com.example.pgroupea03_android.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IStudentRepository {

    @GET("Student")
    Call<List<DtoOutputStudent>> getAll();

    @POST("Student/authenticate")
    Call<DtoOutputTokenStudent> login(@Body Login login);

    @GET("Student/{id}")
    Call<DtoOutputStudent> getById(int id);
}
