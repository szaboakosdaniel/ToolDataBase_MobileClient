package com.mobiletooldatabaseclient;
 import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;

 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;

public class RetrofitClientInstance {
    private static  String API_BASE_URL="http://10.0.2.2:8080/";
    private static Retrofit retrofit;

    private static Gson gson;

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            gson=new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit= new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
