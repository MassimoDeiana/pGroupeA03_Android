package com.example.pgroupea03_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pgroupea03_android.services.SessionManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnScan;
    private Button btnTeacherConnection;
    private Button btnStudentConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewInstances();
        initListeners();
    }

    private void initViewInstances() {
        btnScan = findViewById(R.id.btn_mainActivity_scanQr);
        btnTeacherConnection = findViewById(R.id.btn_mainActivity_loginTeacher);
        btnStudentConnection = findViewById(R.id.btn_mainActivity_loginStudent);
    }

    private void initListeners() {
        btnScan.setOnClickListener(this);

        btnTeacherConnection.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,LoginTeacherActivity.class);
            startActivity(intent);
        });

        btnStudentConnection.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,LoginStudentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onClick(View view) {
        scanCode();
    }

    private void scanCode() {

        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!= null){
            if(result.getContents()!=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                //Url du QR
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning result");
                builder.setPositiveButton("scan again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        scanCode();
                    }
                }).setNegativeButton("finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                Toast.makeText(this,"No Result",Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}