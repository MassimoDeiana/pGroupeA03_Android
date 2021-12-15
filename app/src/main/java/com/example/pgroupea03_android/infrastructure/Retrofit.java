package com.example.pgroupea03_android.infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static final String BASE_URL = "http://192.168.2.28:5000/api/";
    private static retrofit2.Retrofit instance;
    private static Gson gson;

    public static retrofit2.Retrofit getInstance() {
        if(instance == null)
            gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .setLenient()
                    .create();
            instance = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        return instance;
    }

}
