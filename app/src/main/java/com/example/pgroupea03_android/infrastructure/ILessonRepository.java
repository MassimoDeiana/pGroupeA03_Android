package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.lesson.DtoCreateLesson;
import com.example.pgroupea03_android.dtos.lesson.DtoOutputLesson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ILessonRepository {

    @GET("Lesson")
    Call<List<DtoOutputLesson>> getAll();

    @GET("Lesson/{id}")
    Call<DtoOutputLesson> getById(@Path("id")int id);

    @POST("Lesson")
    Call<DtoOutputLesson> create(@Body DtoCreateLesson lesson);

    @DELETE("Lesson/{id}")
    Call<Void> delete(@Path("id")int id);
}
