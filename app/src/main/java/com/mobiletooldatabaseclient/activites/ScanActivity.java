package com.mobiletooldatabaseclient.activites;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.mobiletooldatabaseclient.CaptureAct;
import com.mobiletooldatabaseclient.R;

public class ScanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        Button btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v-> scanCode());
    }

    private void scanCode() {
        ScanOptions options =new ScanOptions();
        options.setPrompt("Scan Sample QR Code");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher= registerForActivityResult(new ScanContract(), result -> {

        if (result.getContents() != null) {

            if (result.getContents().matches("\\d+")) {
                instance.setCode(Integer.parseInt(result.getContents()));
                openinfoScanActivity();
            } else {
                showToast("QR code incorrect");
                openinfoScanActivity();
            }
        }

    });

}