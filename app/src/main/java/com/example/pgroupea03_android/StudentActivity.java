package com.example.pgroupea03_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pgroupea03_android.dtos.interrogation.DtoOutputInterrogation;
import com.example.pgroupea03_android.dtos.schoolclass.DtoOutputSchoolclass;
import com.example.pgroupea03_android.infrastructure.IInterrogationRepository;
import com.example.pgroupea03_android.infrastructure.ISchoolClassRepository;
import com.example.pgroupea03_android.infrastructure.IStudentRepository;
import com.example.pgroupea03_android.infrastructure.Retrofit;
import com.example.pgroupea03_android.services.SessionManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity  extends AppCompatActivity implements View.OnClickListener{

    private Button btnScan;
    private String urlUpdate;
    String className;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        initViewInstances();
        initListeners();
    }

    private void initViewInstances() {
        btnScan = findViewById(R.id.btn_studentActivity_scanQr);
    }

    private void initListeners() {
        btnScan.setOnClickListener(this);
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
                SessionManager sessionManager = new SessionManager(this);
                int idStudent = sessionManager.fetchAuthId();
                int idClass= Integer.parseInt(result.getContents().substring(7));

                System.out.println("ici");



                builder.setTitle("Scanning result");
                Retrofit.getInstance(this).create(IStudentRepository.class).update(idClass,idStudent).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                    }
                });
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

                getClasseName(idClass);

                Log.d("test","id student : " + idStudent + " id classe : " + idClass + " classname : " + className);
                builder.setMessage("Move to " + className + " class");
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                Toast.makeText(this,"No Result",Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }


    private void getClasseName(int idClass){
        try {
            Retrofit.getInstance(this).create(ISchoolClassRepository.class).getById(idClass).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
