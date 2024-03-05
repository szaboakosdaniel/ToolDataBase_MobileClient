package com.mobiletooldatabaseclient;

import android.util.Base64;

import java.nio.charset.StandardCharsets;

/**
 * Singleton class responsible for managing authentication tokens.
 * This class encapsulates the logic for creating basic authentication tokens
 * based on a username and password. It follows the singleton pattern to ensure
 * that only one instance of the token manager is used throughout the application.
 */
public class AuthToken {
    // Single instance of AuthToken for singleton pattern
    private static AuthToken token;
    // Username for authentication
    private String user;
    // Password for authentication
    private String password;

    /**
     * Private constructor to prevent external instantiation.
     */
    private AuthToken() {
    }

    /**
     * Retrieves the single instance of the AuthToken class.
     * If no instance exists, a new one is created. This method ensures that
     * the AuthToken class is a singleton.
     *
     * @return The singleton instance of the AuthToken.
     */
    public static AuthToken getTokenInstance(){
        if(token == null){
            token=new AuthToken();
        }
        return token;
    }

    /**
     * Sets the username for authentication.
     *
     * @param user The username to be used for generating the authentication token.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Sets the password for authentication.
     *
     * @param password The password to be used for generating the authentication token.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Generates a basic authentication token using the username and password.
     * The username and password are combined with a colon and then base64 encoded
     * to create the authentication token.
     *
     * @return A String representing the basic authentication token.
     */
    public String createAuthToken() {
        byte [] data;
        data =(user + ":" +password).getBytes(StandardCharsets.UTF_8);
        return "Basic " + Base64.encodeToString(data,Base64.NO_WRAP);
    }
}
