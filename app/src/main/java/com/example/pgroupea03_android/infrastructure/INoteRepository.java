package com.example.pgroupea03_android.infrastructure;

import com.example.pgroupea03_android.dtos.note.DtoCreateNote;
import com.example.pgroupea03_android.dtos.note.DtoOutputNote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface INoteRepository {

    @POST("Note")
    Call<DtoOutputNote> create(@Body DtoCreateNote note);


    @DELETE("Note/{idInterro}/{idStudent}")
    Call<Void> delete(@Path("idInterro")int idInterro, @Path("idStudent")int idStudent);
}
