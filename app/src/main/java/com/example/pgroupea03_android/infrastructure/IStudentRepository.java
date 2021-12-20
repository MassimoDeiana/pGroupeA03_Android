package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.student.DtoOutputStudent;
import com.example.pgroupea03_android.dtos.student.DtoOutputTokenStudent;
import com.example.pgroupea03_android.model.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IStudentRepository {

    @POST("Student/authenticate")
    Call<DtoOutputTokenStudent> login(@Body Login login);

    @GET("Student/{id}")
    Call<DtoOutputStudent> getById(@Path("id")int id);

    @PUT("Student/{idClass}/{id}")
    Call<Void> update(@Path("idClass")int idClass, @Path("id")int id);
}
