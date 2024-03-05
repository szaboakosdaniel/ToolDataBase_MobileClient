package com.mobiletooldatabaseclient;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationActivity extends AppCompatActivity {

    Button btn_scan;

    private ScanResult instance;

    private AuthToken token;

    private SampleComposite sample;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        instance=ScanResult.getInstance();
        sample=instance.getSample();
        token=AuthToken.getTokenInstance();
        setContentView(R.layout.activity_location);
        btn_scan =findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v-> {
            scanCode();
        });
    }

    private void scanCode() {
        ScanOptions options =new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher= registerForActivityResult(new ScanContract(), result -> {

        if (result.getContents() != null) {
            sample.setLocation(Integer.parseInt(result.getContents()));
            updateSampleComposite(sample);
        }

    });


    private void updateSampleComposite(SampleComposite sampleComposite) {
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api = retrofit.create(InterfaceAPI.class);
        Call<Void> call = api.updateSampleComposite(token.createAuthToken(), sampleComposite);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Handle successful update
                    Toast.makeText(LocationActivity.this, "Sample updated successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LocationActivity.this, InfoActivity.class);
                    startActivity(intent);
                } else {
                    // Handle failure
                    Toast.makeText(LocationActivity.this, "Failed to update sample", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("LocationActivity", "Error updating sample", t);
                Toast.makeText(LocationActivity.this, "Error updating sample", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}