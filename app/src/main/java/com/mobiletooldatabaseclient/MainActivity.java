package com.mobiletooldatabaseclient;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
       emailTxt=findViewById(R.id.editText);
       passTxt=findViewById(R.id.editText2);


       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               email = emailTxt.getText().toString();
               password=passTxt.getText().toString();
               System.out.println("adfadsf");
               String authToken =createAuthToken(email,password);
               checkLoginDetails(authToken);
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
                    }
                }else{

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("TAG",t.toString());
                t.printStackTrace();
            }
        });
    }

    private String createAuthToken(String email, String password) {
        byte [] data =new byte[0];
        try{
            data =(email + ":" +password).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data,Base64.NO_WRAP);
    }
}