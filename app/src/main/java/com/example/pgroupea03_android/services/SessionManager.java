package com.example.pgroupea03_android.services;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pgroupea03_android.R;

public class SessionManager {
    private SharedPreferences preferences;
    private static final String USER_TOKEN = "user_token";
    private static final String USER_ID = "user_id";
    private static final String USER_TYPE = "user_type";

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
     * Méthode permettant d'enregistrer l'id
     * @param id : l'id à enregistrer
     */
    public void saveAuthId(int id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(USER_ID, id);
        editor.apply();
    }

    /**
     * Méthode permettant d'enregistrer le type d'utilisateur
     * @param type : le type d'utilisateur à enregistrer
     */
    public void saveAuthType(UserType type) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(USER_TYPE, type.ordinal());
        editor.apply();
    }

    /**
     * Méthode permettant de récupérer le token enregistré
     * @return le token s'il existe
     */
    public String fetchAuthToken() {
        return preferences.getString(USER_TOKEN, "");
    }

    /**
     * Méthode permettant de récupérer l'id enregistré
     * @return l'id s'il existe
     */
    public int fetchAuthId() {
        return preferences.getInt(USER_ID, 0);
    }

    /**
     * Méthode permettant de récupérer le type d'utilisateur enregistré
     * @return le type d'utilisateur s'il existe
     */
    public int fetchAuthType() {
        return preferences.getInt(USER_TYPE, 0);
    }
}
