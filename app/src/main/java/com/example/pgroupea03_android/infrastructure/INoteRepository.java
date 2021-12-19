package com.example.pgroupea03_android.infrastructure;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface INoteRepository {

    @DELETE("Note/{idInterro}/{idStudent}")
    Call<Void> delete(@Path("idInterro")int idInterro, @Path("idStudent")int idStudent);
}
