package com.mobiletooldatabaseclient;
 import okhttp3.OkHttpClient;
 import retrofit2.Retrofit;
 import retrofit2.converter.gson.GsonConverterFactory;

 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;


/**
 * Singleton class for creating and managing a Retrofit instance.
 * This class provides a centralized Retrofit client for making HTTP requests,
 * ensuring that only one instance of Retrofit is used throughout the application.
 * It configures Retrofit with a base URL for the API and a Gson converter for JSON serialization and deserialization.
 */
public class RetrofitClientInstance {
    // Base URL for the API endpoint. This should point to the server where the API is hosted.
    private static final String API_BASE_URL="https://10.0.2.2:8443/";
    // Static variable to hold the single instance of Retrofit
    private static Retrofit retrofit;


    /**
     * Provides a global access point to the Retrofit instance.
     * If the instance is not already created, it initializes Retrofit with the specified base URL
     * and a Gson converter factory for handling JSON data. This method ensures that the Retrofit
     * instance is created only once, adhering to the singleton pattern.
     *
     * @return The singleton instance of Retrofit configured for the application.
     */
    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            // Creates a Gson object for Retrofit with configuration to handle malformed JSON gracefully
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
            // Initialize Retrofit with the API base URL and the Gson converter factory.
            // This setup is necessary for Retrofit to parse JSON responses into Java objects
            // and to serialize Java objects into JSON requests.
            retrofit= new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
