package com.mobiletooldatabaseclient.activites;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.mobiletooldatabaseclient.R;
import com.mobiletooldatabaseclient.RetrofitClientInstance;
import com.mobiletooldatabaseclient.interfaces.InterfaceAPI;
import com.mobiletooldatabaseclient.model.SampleComposite;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class InfoActivity extends BaseActivity {

    private TextView sampleid,scode,samplePhase,location,projectName,assemblyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_info);
        Button setLocation = findViewById(R.id.setLocation);
        setLocation.setOnClickListener((View v) -> openLocationActivity());

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v-> openScanActivity());


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
        Retrofit retrofit= RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api=retrofit.create(InterfaceAPI.class);
        Call<SampleComposite> call = api.getSampleCompositeByScode(authToken,instance.getCode());
        call.enqueue(new Callback<SampleComposite>() {

            @Override
            public void onResponse(@NonNull Call<SampleComposite> call, @NonNull Response<SampleComposite> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SampleComposite sampleComposite = response.body();
                    instance.setSample(sampleComposite);
                    // Update your TextViews here
                    sampleid.setText(String.valueOf(sampleComposite.getSampleId()));
                    scode.setText(String.valueOf(sampleComposite.getScode()));
                    samplePhase.setText(sampleComposite.getSamplePhase());
                    location.setText(String.valueOf(sampleComposite.getLocation()));
                    projectName.setText(sampleComposite.getProjectName());
                    assemblyId.setText(String.valueOf(sampleComposite.getAssemblyId()));
                } else {
                    showToast("Sample not found or error in response");
                    openScanActivity();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SampleComposite> call, @NonNull Throwable t) {
                Log.e("InfoActivity", "Error fetching sample info", t);
                showToast("Error fetching sample info");
            }
        });
    }






}