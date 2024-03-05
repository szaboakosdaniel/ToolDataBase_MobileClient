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

/**
 * InfoActivity is an activity class that extends BaseActivity to inherit
 * common functionality. This activity is responsible for displaying detailed
 * information about a sample fetched from a remote server using Retrofit.
 */
public class InfoActivity extends BaseActivity {

    // TextViews for displaying sample details.
    private TextView sampleid,scode,samplePhase,location,projectName,assemblyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Setting the layout for this activity
        setContentView(R.layout.activity_info);
        // Initializing and setting up the setLocation button with a click listener
        // that navigates to the LocationActivity.
        Button setLocation = findViewById(R.id.setLocation);
        setLocation.setOnClickListener((View v) -> openLocationActivity());
        // Initializing and setting up the back button with a click listener
        // that navigates back to the ScanActivity.
        Button back = findViewById(R.id.back);
        back.setOnClickListener(v-> openScanActivity());
        // Initializing TextViews to display the sample's details.
        sampleid = findViewById(R.id.sampleid);
        scode = findViewById(R.id.scode);
        samplePhase = findViewById(R.id.samplePhase);
        location = findViewById(R.id.location);
        projectName = findViewById(R.id.projectName);
        assemblyId = findViewById(R.id.assemblyId);
        // Fetching sample information from the server using the authentication token.
        getSampleInfo(token.createAuthToken());
    }

    /**
     * Fetches sample information based on the scanned code and updates the UI.
     * @param authToken The authentication token used for API calls.
     */
    private void getSampleInfo(String authToken) {
        // Getting the Retrofit instance and creating an API interface.
        Retrofit retrofit= RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api=retrofit.create(InterfaceAPI.class);
        // Making an asynchronous call to fetch sample details.
        Call<SampleComposite> call = api.getSampleCompositeByScode(authToken,instance.getCode());
        call.enqueue(new Callback<SampleComposite>() {
            @Override
            public void onResponse(@NonNull Call<SampleComposite> call, @NonNull Response<SampleComposite> response) {
                // Handling successful response from the server.
                if (response.isSuccessful() && response.body() != null) {
                    SampleComposite sampleComposite = response.body();
                    // Storing the fetched sample in the singleton instance.
                    instance.setSample(sampleComposite);
                    // Updating UI with the fetched sample details.
                    sampleid.setText(String.valueOf(sampleComposite.getSampleId()));
                    scode.setText(String.valueOf(sampleComposite.getScode()));
                    samplePhase.setText(sampleComposite.getSamplePhase());
                    location.setText(String.valueOf(sampleComposite.getLocation()));
                    projectName.setText(sampleComposite.getProjectName());
                    assemblyId.setText(String.valueOf(sampleComposite.getAssemblyId()));
                } else {
                    // Handling cases where the sample is not found or there is an error in response.
                    showToast("Sample not found or error in response");
                    openScanActivity();
                }
            }

            @Override
            public void onFailure(@NonNull Call<SampleComposite> call, @NonNull Throwable t) {
                // Logging and handling errors in fetching sample information.
                Log.e("InfoActivity", "Error fetching sample info", t);
                showToast("Error fetching sample info");
            }
        });
    }






}