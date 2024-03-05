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

public class MainActivity extends BaseActivity {
    private EditText username, password;

   // private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        username=findViewById(R.id.user);
        password=findViewById(R.id.password);


       button.setOnClickListener(view -> {
           token.setUser(username.getText().toString());
           token.setPassword(password.getText().toString());
           checkLoginDetails(token.createAuthToken());
       });

    }

    private void checkLoginDetails(String authToken) {
        Retrofit retrofit= RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api=retrofit.create(InterfaceAPI.class);
        Call<String> call = api.checkLogin(authToken);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    if(response.body().matches("succes")){
                        showToast("Success");
                        openScanActivity();
                    }
                }else{
                    showToast("Incorrect Username or Password");
                    username.setText("");
                    password.setText("");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }
        });
    }


}