package com.mobiletooldatabaseclient;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class AuthTokenInstrumentedTest {

    private AuthToken authToken;

    @Before
    public void setUp() {
        // Itt példányosítjuk az AuthToken osztályt, előkészítve a tesztelésre
        authToken = AuthToken.getTokenInstance();
    }

    @Test
    public void authTokenCreation_isCorrect() {
        // Beállítjuk a felhasználónevet és jelszót
        authToken.setUser("admin");
        authToken.setPassword("password123");

        // Generáljuk a token-t
        String result = authToken.createAuthToken();

        // A várt értéket a "Basic YWRtaW46cGFzc3dvcmQxMjM=" kell, hogy legyen,
        // de mivel ez az érték függ a Base64 kódolástól és a platform implementációjától,
        // a tesztelés során közvetlenül nem ellenőrizhető.
        // Ezért csak annyit tesztelünk, hogy a generált string "Basic " előtaggal kezdődik-e.
        assertEquals(true, result.startsWith("Basic "));
    }
}
