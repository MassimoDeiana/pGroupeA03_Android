package com.example.pgroupea03_android.services;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private SessionManager sessionManager;

    public AuthInterceptor(Context context) {
        this.sessionManager = new SessionManager(context);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        String token = sessionManager.fetchAuthToken();

        if(!token.isEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        return chain.proceed(requestBuilder.build());
    }
}
