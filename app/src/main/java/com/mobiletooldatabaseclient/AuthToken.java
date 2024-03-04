package com.mobiletooldatabaseclient;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

public class AuthToken {
    private static AuthToken token;
    private String email;
    private String password;

    private AuthToken() {
    }

    public static AuthToken getTokenInstance(){
        if(token == null){
            token=new AuthToken();
        }
        return token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String createAuthToken() {
        byte [] data =new byte[0];
        try{
            data =(email + ":" +password).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "Basic " + Base64.encodeToString(data,Base64.NO_WRAP);
    }
}
