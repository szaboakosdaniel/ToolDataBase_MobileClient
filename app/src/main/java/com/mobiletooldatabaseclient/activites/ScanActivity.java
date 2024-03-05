package com.mobiletooldatabaseclient.activites;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.mobiletooldatabaseclient.CaptureAct;
import com.mobiletooldatabaseclient.R;
import com.mobiletooldatabaseclient.ScanResult;

public class ScanActivity extends BaseActivity {

    private Button btn_scan;
    private ScanResult instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance=ScanResult.getInstance();
        setContentView(R.layout.activity_scan);
        btn_scan =findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v-> {
            scanCode();
        });
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
                Intent intent = new Intent(ScanActivity.this, InfoActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(ScanActivity.this, InfoActivity.class);
                showToast("QR code incorrect");
                startActivity(intent);
                finish();
            }
        }

    });

    private void openInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}