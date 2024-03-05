package com.mobiletooldatabaseclient.activites;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the login activity
        setContentView(R.layout.activity_login);
        // Initialize the login button and input fields
        Button button = findViewById(R.id.button);
        username=findViewById(R.id.user);
        password=findViewById(R.id.password);

        // Setup the click listener for the login button
        button.setOnClickListener(view -> {
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
                // Log and handle any errors during the network request
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }
        });
    }


}