package com.mobiletooldatabaseclient.activites;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import com.mobiletooldatabaseclient.CaptureAct;
import com.mobiletooldatabaseclient.R;

/**
 * ScanActivity extends BaseActivity to use common functionalities defined in the base class.
 * This activity is responsible for initiating and handling QR code scanning operations.
 */
public class ScanActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Sets the content view to the activity_scan layout
        setContentView(R.layout.activity_scan);
        // Initializes the scan button and sets up its click listener to start the scan operation
        Button btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(v-> scanCode());
    }

    /**
     * Configures and launches the barcode scanner with specific options.
     * Uses the ScanOptions to customize the scanning experience, such as
     * enabling beep sounds and locking orientation during scanning.
     */
    private void scanCode() {
        ScanOptions options =new ScanOptions();
        // Sets the prompt to guide the user during scanning
        options.setPrompt("Scan Sample QR Code");
        // Enables beep sound after a successful scan
        options.setBeepEnabled(true);
        // Locks the orientation to the current state during scanning
        options.setOrientationLocked(true);
        // Specifies the custom activity that will display the scanner UI
        options.setCaptureActivity(CaptureAct.class);
        // Launches the barcode scanner
        barLaucher.launch(options);
    }

    // Initializes the ActivityResultLauncher for handling the scan result
    ActivityResultLauncher<ScanOptions> barLaucher= registerForActivityResult(new ScanContract(), result -> {
        // Checks if the scanned result is not null
        if (result.getContents() != null) {
            // Validates if the scanned result is a numeric value
            if (result.getContents().matches("\\d+")) {
                // Parses the scanned numeric value and sets it in the instance
                instance.setCode(Integer.parseInt(result.getContents()));
                // Navigates to the InfoActivity to display scanned information
                openinfoScanActivity();
            } else {
                // Shows a toast message for incorrect QR code format
                showToast("QR code incorrect");
                // Optionally, navigates back to the InfoActivity or handles the error differently
                openinfoScanActivity();
            }
        }

    });

}