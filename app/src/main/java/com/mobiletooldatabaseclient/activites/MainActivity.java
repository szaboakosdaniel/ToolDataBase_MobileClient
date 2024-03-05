package com.mobiletooldatabaseclient.activites;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobiletooldatabaseclient.AuthToken;
import com.mobiletooldatabaseclient.R;
import com.mobiletooldatabaseclient.RetrofitClientInstance;
import com.mobiletooldatabaseclient.interfaces.InterfaceAPI;

//import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivity {
    private Button button;
    private EditText username, password;
    private AuthToken token;

   // private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        token=AuthToken.getTokenInstance();
        button=findViewById(R.id.button);
        username=findViewById(R.id.user);
        password=findViewById(R.id.password);


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               token.setEmail(username.getText().toString());
               token.setPassword(password.getText().toString());
               checkLoginDetails(token.createAuthToken());
           }
       });

    }

    private void checkLoginDetails(String authToken) {
        Retrofit retrofit= RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api=retrofit.create(InterfaceAPI.class);
        Call<String> call = api.checkLogin(authToken);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(response.body().matches("succes")){
                        showToast("Success");
                        openScanActivity();
                        finish();
                    }
                }else{
                    showToast("Incorrect Username or Password");
                    username.setText("");
                    password.setText("");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }
        });
    }



    private void openScanActivity() {
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }


}