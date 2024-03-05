package com.mobiletooldatabaseclient.activites;

import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobiletooldatabaseclient.AuthToken;
import com.mobiletooldatabaseclient.ScanResult;
/**
 * BaseActivity is an abstract class that extends AppCompatActivity.
 * This class serves as a base class for other activities in the application,
 * providing common functionalities such as displaying toast messages and navigating between activities.
 * It initializes and provides access to AuthToken and ScanResult instances,
 * which are common throughout the application.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected AuthToken token; // Token instance for authentication purposes
    protected ScanResult instance; // Instance of ScanResult to hold and manage the scanning results

    /**
     * Constructor for BaseActivity.
     * Initializes the AuthToken and ScanResult instances by fetching their singleton instances.
     */
    public BaseActivity() {
        token=AuthToken.getTokenInstance();
        instance = ScanResult.getInstance();
    }

    /**
     * Displays a toast message to the user.
     * @param message The message to be displayed.
     */
    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Navigates to LocationActivity and finishes the current activity.
     * This method is used to move from the current activity to the LocationActivity,
     * and ensures that the current activity is removed from the activity stack.
     */
    protected void openLocationActivity() {
        // Create an Intent to navigate to InfoActivity.The Intent describes the activity to start and provides any necessary data.
        Intent intent = new Intent(this, LocationActivity.class);
        // Start the activity specified by the Intent.This makes the InfoActivity the active screen.
        startActivity(intent);
        // Call finish() to close the current activity.This prevents the activity from being included in the back stack,ensuring that pressing the back button won't navigate back to this activity.
        finish();
    }

    /**
     * Navigates to ScanActivity and finishes the current activity.
     * Similar to openLocationActivity, this method transitions the user to the ScanActivity,
     * removing the current activity from the stack to prevent back navigation to it.
     */
    protected void openScanActivity() {
        // Create an Intent to navigate to InfoActivity.The Intent describes the activity to start and provides any necessary data.
        Intent intent = new Intent(this, ScanActivity.class);
        // Start the activity specified by the Intent.This makes the InfoActivity the active screen.
        startActivity(intent);
        // Call finish() to close the current activity.This prevents the activity from being included in the back stack,ensuring that pressing the back button won't navigate back to this activity.
        finish();
    }

    /**
     * Navigates to InfoActivity and finishes the current activity.
     * It provides a mechanism to transition to the InfoActivity while ensuring
     * that the current activity is not accessible via the back button.
     */
    protected void openinfoScanActivity() {
        // Create an Intent to navigate to InfoActivity.The Intent describes the activity to start and provides any necessary data.
        Intent intent = new Intent(this, InfoActivity.class);
        // Start the activity specified by the Intent.This makes the InfoActivity the active screen.
        startActivity(intent);
        // Call finish() to close the current activity.This prevents the activity from being included in the back stack,ensuring that pressing the back button won't navigate back to this activity.
        finish();
    }
}
