package com.example.pgroupea03_android.infrastructure;

import android.content.Context;

import com.example.pgroupea03_android.services.AuthInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static final String BASE_URL = "http://192.168.0.23:5000/api/";
    private static retrofit2.Retrofit instance;
    private static Gson gson;

    public static retrofit2.Retrofit getInstance(Context context) {
        if(instance == null)
            gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setLenient()
                    .create();
            instance = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient(context))
                    .build();
        return instance;
    }

    /**
     * Initialise un objet OkHttpClient permettant d'indiquer un token dans chaque requête
     */
    private static OkHttpClient okHttpClient(Context context) {
        return new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor(context))
                .build();
    }

}
