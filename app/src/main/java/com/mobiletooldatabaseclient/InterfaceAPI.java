package com.mobiletooldatabaseclient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceAPI {

    @POST("/check-auth")
    Call<String> checkLogin(@Header("Authorization") String authtoke);

    @GET("/sampleComposite/getByScode")
    Call<SampleComposite> getSampleCompositeByScode(@Header("Authorization") String authToken, @Query("scode") Integer scode);
}
