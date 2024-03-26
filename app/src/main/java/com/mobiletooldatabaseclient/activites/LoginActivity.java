package com.mobiletooldatabaseclient.activites;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.mobiletooldatabaseclient.R;
import com.mobiletooldatabaseclient.RetrofitClientInstance;
import com.mobiletooldatabaseclient.interfaces.InterfaceAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * LoginActivity extends BaseActivity to utilize common base functionalities.
 * This activity handles user login by capturing username and password inputs,
 * and verifying these credentials via a network request.
 */
public class LoginActivity extends BaseActivity {
    // Input fields for the user to enter their username and password
    private EditText username, password;
    private Button button;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the login activity
        setContentView(R.layout.activity_login);
        // Initialize the login button and input fields
        button = findViewById(R.id.button);
        username=findViewById(R.id.user);
        password=findViewById(R.id.password);
        // Find the ProgressBar by its ID
        progressBar = findViewById(R.id.progressBar);

        // Add TextChangedListener to username and password fields
        username.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);

        // Disable the login button initially
        button.setEnabled(false);

        // Setup the click listener for the login button
        button.setOnClickListener(view -> {
            // Show progress bar when login process starts
            progressBar.setVisibility(View.VISIBLE);
            username.setEnabled(false);
            password.setEnabled(false);
            button.setEnabled(false);
            // Retrieve input text and set it as user and password in the token
            token.setUser(username.getText().toString());
            token.setPassword(password.getText().toString());
            // Attempt to authenticate with the provided credentials
            checkLoginDetails(token.createAuthToken());
        });
    }

    /**
     * Verifies the login credentials by making a network request.
     * Uses Retrofit to call the checkLogin API endpoint, passing the authentication token.
     *
     * @param authToken The authentication token created from the user's input.
     */
    private void checkLoginDetails(String authToken) {
        // Get the Retrofit instance and create an API interface
        Retrofit retrofit= RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api=retrofit.create(InterfaceAPI.class);

        // Make an asynchronous call to the login API
        Call<String> call = api.checkLogin(authToken);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                // Check if the response from the server is successful and matches the expected success response
                progressBar.setVisibility(View.INVISIBLE);
                username.setEnabled(true);
                password.setEnabled(true);
                button.setEnabled(true);
                if(response.isSuccessful()){
                    assert response.body() != null;
                    if(response.body().matches("succes")){
                        // Notify the user of successful authentication and navigate to the ScanActivity
                        showToast("Success");
                        openScanActivity();
                    }
                }else{
                    // Notify the user of failed authentication and clear the input fields
                    showToast("Incorrect Username or Password");
                    username.setText("");
                    password.setText("");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                showToast("No response from remote server");
                progressBar.setVisibility(View.INVISIBLE);
                username.setEnabled(true);
                password.setEnabled(true);
                button.setEnabled(true);
                username.setText("");
                password.setText("");
                // Log and handle any errors during the network request
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }
        });
    }

    // TextWatcher for username and password fields
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No implementation needed
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // No implementation needed
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Check if both username and password fields are not empty
            if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                // Enable the login button if both fields are not empty
                button.setEnabled(true);
            } else {
                // Disable the login button if either field is empty
                button.setEnabled(false);
            }
        }
    };

}