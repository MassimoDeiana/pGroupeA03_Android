package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.result.DtoOutputResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IResultRepository {

    @GET("Result/{id}")
    Call<List<DtoOutputResult>> getResults(@Path("id")int id);
}
