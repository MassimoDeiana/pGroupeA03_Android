package com.example.pgroupea03_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class InterrogationActivity extends AppCompatActivity {

    private ImageButton btnList;
    private ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interrogation);

        initViewInstances();
        initListeners();
    }

    /**
     * Méthode permettant d'initialiser les différents attributs de l'activité
     * et d'associer les différents composants aux composants graphiques du layout
     */
    private void initViewInstances() {
        btnList = findViewById(R.id.btn_interrogationActivity_list);
        btnAdd = findViewById(R.id.btn_interrogationActivity_add);
    }

    /**
     * Méthode permettant de définir des comportements lorsqu'une action est effectuée sur les différents composants du layout
     */
    private void initListeners() {
        btnList.setOnClickListener(view -> {
            Intent intent = new Intent(InterrogationActivity.this, InterrogationListActivity.class);
            startActivity(intent);
        });

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(InterrogationActivity.this, InterrogationAddActivity.class);
            startActivity(intent);
        });
    }
}