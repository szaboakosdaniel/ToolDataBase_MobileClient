package com.mobiletooldatabaseclient;

import org.junit.Test;
import static org.junit.Assert.*;

public class AuthTokenTest {

    @Test
    public void testSingletonInstance() {
        AuthToken instance1 = AuthToken.getTokenInstance();
        AuthToken instance2 = AuthToken.getTokenInstance();

        assertNotNull(instance1);
        assertNotNull(instance2);
        assertSame(instance1, instance2);
    }

    @Test
    public void testCreateAuthToken() {
        AuthToken token = AuthToken.getTokenInstance();
        token.setUser("admin");
        token.setPassword("password123");

        // A Base64 kódolás eredménye "admin:password123" sztringre
        // Ehhez hasonlóan "YWRtaW46cGFzc3dvcmQxMjM="
        String expectedToken = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
        assertEquals(expectedToken, token.createAuthToken());
    }
}
