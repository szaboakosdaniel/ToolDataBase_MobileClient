package com.mobiletooldatabaseclient;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ScanActivity extends AppCompatActivity {

    Button btn_scan;

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
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher= registerForActivityResult(new ScanContract(), result -> {
/*        if(result.getContents() !=null){
            AlertDialog.Builder builder =new AlertDialog.Builder(ScanActivity.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    dialogInterface.dismiss();
                }
            }).show();
        }*/

        if (result.getContents() != null) {
            // Indítsa el az InfoActivity-t és adja át az eredményt
            instance.setCode(Integer.parseInt(result.getContents()));
            Intent intent = new Intent(ScanActivity.this, InfoActivity.class);
            startActivity(intent);
        }

    });

    private void openInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}