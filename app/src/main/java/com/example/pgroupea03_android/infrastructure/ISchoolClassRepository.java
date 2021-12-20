package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.schoolclass.DtoOutputSchoolclass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ISchoolClassRepository {

    @GET("SchoolClass/{id}")
    Call<DtoOutputSchoolclass> getById(@Path("id")int id);
}
