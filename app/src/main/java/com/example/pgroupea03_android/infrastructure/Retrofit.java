package com.example.pgroupea03_android.infrastructure;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {

    private static final String BASE_URL = "http://192.168.2.28:5000/api/";
    private static retrofit2.Retrofit instance;

    public static retrofit2.Retrofit getInstance() {
        if(instance == null)
            instance = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return instance;
    }

}
