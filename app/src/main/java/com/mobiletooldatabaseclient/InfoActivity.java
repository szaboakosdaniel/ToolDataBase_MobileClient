package com.mobiletooldatabaseclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InfoActivity extends AppCompatActivity {

    private TextView sampleid,scode,samplePhase,location,projectName,assemblyId;

    private AuthToken token;

    private ScanResult instance;

    Button setLocation;

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = ScanResult.getInstance();
        setContentView(R.layout.activity_info);
        setLocation =findViewById(R.id.setLocation);
        setLocation.setOnClickListener(v-> {
            //openScanActivity();
        });

        back =findViewById(R.id.back);
        back.setOnClickListener(v-> {
            openScanActivity();
        });

        // Az Intent-ből származó extra adatok olvasása
        //  int scanResult = getIntent().getIntExtra("scanResult",0);

        token=AuthToken.getTokenInstance();
        // Initialize your TextViews
        sampleid = findViewById(R.id.sampleid);
        scode = findViewById(R.id.scode);
        samplePhase = findViewById(R.id.samplePhase);
        location = findViewById(R.id.location);
        projectName = findViewById(R.id.projectName);
        assemblyId = findViewById(R.id.assemblyId);

        getSampleInfo(token.createAuthToken());
    }
    private void getSampleInfo(String authToken) {
        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api=retrofit.create(InterfaceAPI.class);
        Call<SampleComposite> call = api.getSampleCompositeByScode(authToken,instance.getCode());
        call.enqueue(new Callback<SampleComposite>() {

            @Override
            public void onResponse(Call<SampleComposite> call, Response<SampleComposite> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SampleComposite sampleComposite = response.body();
                    // Update your TextViews here
                    sampleid.setText(String.valueOf(sampleComposite.getSampleId()));
                    scode.setText(String.valueOf(sampleComposite.getScode()));
                    samplePhase.setText(sampleComposite.getSamplePhase());
                    location.setText(String.valueOf(sampleComposite.getLocation()));
                    projectName.setText(sampleComposite.getProjectName());
                    assemblyId.setText(String.valueOf(sampleComposite.getAssemblyId()));
                } else {
                    Toast.makeText(InfoActivity.this, "Sample not found or error in response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SampleComposite> call, Throwable t) {
                Log.e("InfoActivity", "Error fetching sample info", t);
                Toast.makeText(InfoActivity.this, "Error fetching sample info", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openScanActivity() {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }



}