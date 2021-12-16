package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.interrogation.DtoCreateInterrogation;
import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IInterrogationService {

    @GET("Interrogation")
    Call<List<DtoOutputInterrogation>> getAll(@Header("Authorization") String authToken);

    @GET("Interrogation/{id}")
    Call<DtoOutputInterrogation> getById(@Header("Authorization") String authToken, int id);

    @POST("Interrogation")
    Call<DtoOutputInterrogation> create(@Header("Authorization") String authToken, DtoCreateInterrogation interrogation);

    @DELETE("Interrogation/{id}")
    Call<Void> delete(@Header("Authorization") String authToken, int id);
}
