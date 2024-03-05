package com.mobiletooldatabaseclient.activites;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.mobiletooldatabaseclient.AuthToken;
import com.mobiletooldatabaseclient.CaptureAct;
import com.mobiletooldatabaseclient.R;
import com.mobiletooldatabaseclient.RetrofitClientInstance;
import com.mobiletooldatabaseclient.model.SampleComposite;
import com.mobiletooldatabaseclient.ScanResult;
import com.mobiletooldatabaseclient.interfaces.InterfaceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocationActivity extends BaseActivity {

    private Button btn_scan;

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
        options.setPrompt("Scan Location QR Code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher= registerForActivityResult(new ScanContract(), result -> {

        if (result.getContents() != null) {
            if (result.getContents().matches("\\d+")) {
                sample.setLocation(Integer.parseInt(result.getContents()));
                updateSampleComposite(sample);
            }
            else {
                // Kezelje az esetet, ha a sztring nem csak számokat tartalmaz
                // Például megjeleníthet egy hibaüzenetet
                Intent intent = new Intent(LocationActivity.this, InfoActivity.class);
                showToast("QR code incorrect");
                startActivity(intent);
                finish();
            }
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
                    showToast("Sample updated successfully");
                    Intent intent = new Intent(LocationActivity.this, InfoActivity.class);
                    startActivity(intent);
                } else {
                    // Handle failure
                    showToast("Failed to update sample");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("LocationActivity", "Error updating sample", t);
                showToast("Error updating sample");
            }
        });
    }

}