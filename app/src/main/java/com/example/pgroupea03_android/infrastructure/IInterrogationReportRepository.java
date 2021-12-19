package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.interrogationreport.DtoOutputInterrogationReport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IInterrogationReportRepository {

    @GET("InterrogationReport/{id}")
    Call<List<DtoOutputInterrogationReport>> getStudentsByInterro(@Path("id")int id);
}
