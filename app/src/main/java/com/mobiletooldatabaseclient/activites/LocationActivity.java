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

public class LocationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setContentView(R.layout.activity_location);
        Button btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v-> scanCode());
    }

    private void scanCode() {
        ScanOptions options =new ScanOptions();
        options.setPrompt("Scan Location QR Code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher= registerForActivityResult(new ScanContract(), result -> {

        if (result.getContents() != null) {
            if (result.getContents().matches("\\d+")) {
                instance.getSample().setLocation(Integer.parseInt(result.getContents()));
                updateSampleComposite(instance.getSample());
            }
            else {
                showToast("QR code incorrect");
                openinfoScanActivity();
            }
        }

    });


    private void updateSampleComposite(SampleComposite sampleComposite) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<Void> call = api.updateSampleComposite(token.createAuthToken(), sampleComposite);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful update
                    showToast("Sample updated successfully");
                    openinfoScanActivity();
                } else {
                    // Handle failure
                    showToast("Failed to update sample");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e("LocationActivity", "Error updating sample", t);
                showToast("Error updating sample");
            }
        });
    }

}