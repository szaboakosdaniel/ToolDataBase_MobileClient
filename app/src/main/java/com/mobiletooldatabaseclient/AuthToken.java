package com.mobiletooldatabaseclient;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class AuthToken {
    private static AuthToken token;
    private String user;
    private String password;

    private AuthToken() {
    }

    public static AuthToken getTokenInstance(){
        if(token == null){
            token=new AuthToken();
        }
        return token;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String createAuthToken() {
        byte [] data;
        data =(user + ":" +password).getBytes(StandardCharsets.UTF_8);
        return "Basic " + Base64.encodeToString(data,Base64.NO_WRAP);
    }
}
