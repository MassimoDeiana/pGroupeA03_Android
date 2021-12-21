package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.interrogation.DtoCreateInterrogation;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IInterrogationRepository {

    @GET("Interrogation")
    Call<List<DtoOutputInterrogation>> getAll();

    @GET("Interrogation/{id}")
    Call<DtoOutputInterrogation> getById(@Path("id")int id);

    @GET("Interrogation/Teacher/{id}")
    Call<List<DtoOutputInterrogation>> getByTeacher(@Path("id")int id);

    @POST("Interrogation")
    Call<DtoOutputInterrogation> create(@Body DtoCreateInterrogation interrogation);

    @DELETE("Interrogation/{id}")
    Call<Void> delete(@Path("id")int id);
}
