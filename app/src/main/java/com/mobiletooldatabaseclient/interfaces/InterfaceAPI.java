package com.mobiletooldatabaseclient.interfaces;

import com.mobiletooldatabaseclient.model.SampleComposite;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface InterfaceAPI {

    @POST("/check-auth")
    Call<String> checkLogin(@Header("Authorization") String authtoke);

    @GET("/sampleComposite/getByScode")
    Call<SampleComposite> getSampleCompositeByScode(@Header("Authorization") String authToken, @Query("scode") Integer scode);

    @PUT("/sampleComposite/update")
    Call<Void> updateSampleComposite(@Header("Authorization") String authToken, @Body SampleComposite sampleComposite);
}
