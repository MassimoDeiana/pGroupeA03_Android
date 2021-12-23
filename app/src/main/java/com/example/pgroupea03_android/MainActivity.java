package com.example.pgroupea03_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    private Button btnTeacherConnection;
    private Button btnStudentConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewInstances();
        initListeners();
    }

    /**
     * Méthode permettant d'initialiser les différents attributs de l'activité
     * et d'associer les différents composants aux composants graphiques du layout
     */
    private void initViewInstances() {
        btnTeacherConnection = findViewById(R.id.btn_mainActivity_loginTeacher);
        btnStudentConnection = findViewById(R.id.btn_mainActivity_loginStudent);
    }

    /**
     * Méthode permettant de définir des comportements lorsqu'une action est effectuée sur les différents composants du layout
     */
    private void initListeners() {
        btnTeacherConnection.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,LoginTeacherActivity.class);
            startActivity(intent);
        });

        btnStudentConnection.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,LoginStudentActivity.class);
            startActivity(intent);
        });
    }


}