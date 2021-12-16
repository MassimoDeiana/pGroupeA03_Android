package com.example.pgroupea03_android.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pgroupea03_android.R;

public class SessionManager {
    private SharedPreferences preferences;
    private static final String USER_TOKEN = "user_token";

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    /**
     * Méthode permettant d'enregistrer le token
     * @param token : le token à enregistrer
     */
    public void saveAuthToken(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_TOKEN, token);
        editor.apply();
    }

    /**
     * Méthode permettant de récupérer le token enregistré
     * @return le token s'il existe
     */
    public String fetchAuthToken() {
        return preferences.getString(USER_TOKEN, "");
    }
}
