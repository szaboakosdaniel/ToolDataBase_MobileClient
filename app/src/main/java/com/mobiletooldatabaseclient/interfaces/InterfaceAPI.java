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

    /** Sends a POST request to "/check-auth" to verify if the provided authorization token is valid.
     @param authtoke The authorization token to be sent in the request header.
     @return A Call object for a String response, which can be used to asynchronously get the login check result.**/
    @POST("/check-auth")
    Call<String> checkLogin(@Header("Authorization") String authtoke);

    /** Sends a GET request to "/sampleComposite/getByScode" to retrieve a SampleComposite object by its code.
     @param authToken The authorization token to be sent in the request header.
     @param scode The specific code of the SampleComposite to retrieve.
     @return A Call object for a SampleComposite response, which can be used to asynchronously get the specified object.**/
    @GET("/sampleComposite/getByScode")
    Call<SampleComposite> getSampleCompositeByScode(@Header("Authorization") String authToken, @Query("scode") Integer scode);

    /** Sends a PUT request to "/sampleComposite/update" to update a SampleComposite object in the database.
    @param authToken The authorization token to be sent in the request header.
    @param sampleComposite The SampleComposite object to update, sent in the request body.
    @return A Call object for a Void response, indicating that the request was sent but no response body is expected back.**/
    @PUT("/sampleComposite/update")
    Call<Void> updateSampleComposite(@Header("Authorization") String authToken, @Body SampleComposite sampleComposite);
}
