package com.mobiletooldatabaseclient;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
//import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText emailTxt, passTxt;
    private AuthToken token;

   // private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        token=AuthToken.getTokenInstance();
        button=findViewById(R.id.button);
       emailTxt=findViewById(R.id.editText);
       passTxt=findViewById(R.id.editText2);


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               token.setEmail(emailTxt.getText().toString());
               token.setPassword(passTxt.getText().toString());
               checkLoginDetails(token.createAuthToken());
           }
       });

    }

    private void checkLoginDetails(String authToken) {
        Retrofit retrofit=RetrofitClientInstance.getRetrofitInstance();
        final InterfaceAPI api=retrofit.create(InterfaceAPI.class);
        Call<String> call = api.checkLogin(authToken);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    if(response.body().matches("succes")){
                        Toast.makeText(getApplicationContext(),"Succes",Toast.LENGTH_LONG).show();
                        //openInfoActivity();
                        openScanActivity();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Incorrect Username or Password",Toast.LENGTH_LONG).show();
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