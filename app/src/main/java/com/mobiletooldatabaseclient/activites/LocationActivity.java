package com.mobiletooldatabaseclient.activites;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.mobiletooldatabaseclient.CaptureAct;
import com.mobiletooldatabaseclient.R;
import com.mobiletooldatabaseclient.RetrofitClientInstance;
import com.mobiletooldatabaseclient.interfaces.InterfaceAPI;
import com.mobiletooldatabaseclient.model.SampleComposite;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * LocationActivity extends BaseActivity to utilize common functionality
 * and to manage location scanning and updating the scanned location in the server.
 */
public class LocationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets the layout for this activity.
        setContentView(R.layout.activity_location);
        // Initialize the scan button and set its click listener to initiate the scan operation.
        Button btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v-> scanCode());
    }

    /**
     * Configures and launches the QR code scanner.
     * It sets up scanning options and specifies the activity to handle the scan result.
     */
    private void scanCode() {
        ScanOptions options =new ScanOptions();
        // Configures the prompt message for the scanner.
        options.setPrompt("Scan Location QR Code");
        // Enables the beep after scanning.
        options.setBeepEnabled(true);
        // Locks the orientation to the current configuration.
        options.setOrientationLocked(true);
        // Specifies the custom activity to display the scanner.
        options.setCaptureActivity(CaptureAct.class);
        // Launches the scanner.
        barLaucher.launch(options);
    }

    // Launcher for the barcode scanner result.
    ActivityResultLauncher<ScanOptions> barLaucher= registerForActivityResult(new ScanContract(), result -> {
        // Handles the scanned data.
        if (result.getContents() != null) {
            // Checks if the scanned data is numeric, which is expected for a location code.
            if (result.getContents().matches("\\d+")) {
                // Updates the location in the current sample instance based on the scanned result.
                instance.getSample().setLocation(Integer.parseInt(result.getContents()));
                // Attempts to update the sample's location on the server.
                updateSampleComposite(instance.getSample());
            }
            else {
                // Handles incorrect QR codes that do not match the expected format.
                showToast("QR code incorrect");
                // Navigates back to the InfoActivity.
                openinfoScanActivity();
            }
        }

    });


    /**
     * Sends a request to update the sample information on the server with the new location.
     * @param sampleComposite The updated sample object including the new location.
     */
    private void updateSampleComposite(SampleComposite sampleComposite) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<Void> call = api.updateSampleComposite(token.createAuthToken(), sampleComposite);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    // Notifies the user of the successful update.
                    showToast("Sample updated successfully");
                    // Returns to the InfoActivity.
                    openinfoScanActivity();
                } else {
                    // Notifies the user of the failure to update the sample.
                    showToast("Failed to update sample");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                // Logs and notifies the user of an error in the update process.
                Log.e("LocationActivity", "Error updating sample", t);
                showToast("Error updating sample");
            }
        });
    }

}