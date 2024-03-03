package com.mobiletooldatabaseclient;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
public interface InterfaceAPI {

    @POST("/check-auth")
    Call<String> checkLogin(@Header("Authorization") String authtoke);
}
