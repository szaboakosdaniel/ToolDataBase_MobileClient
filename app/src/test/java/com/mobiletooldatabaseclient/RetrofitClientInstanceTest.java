/*
package com.mobiletooldatabaseclient;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import retrofit2.Retrofit;

public class RetrofitClientInstanceTest {

    @Test
    public void testSingletonBehavior() {
        // Mock Retrofit.Builder and its methods using Mockito
        try (MockedStatic<Retrofit.Builder> mockedBuilder = Mockito.mockStatic(Retrofit.Builder.class)) {
            // Setup mock behavior
            Retrofit.Builder builderMock = Mockito.mock(Retrofit.Builder.class);
            Mockito.when(builderMock.baseUrl(Mockito.anyString())).thenReturn(builderMock);
            Mockito.when(builderMock.addConverterFactory(Mockito.any())).thenReturn(builderMock);
            Retrofit retrofitMock = Mockito.mock(Retrofit.class);
            Mockito.when(builderMock.build()).thenReturn(retrofitMock);

            mockedBuilder.when(Retrofit.Builder::new).thenReturn(builderMock);

            // First call to getRetrofitInstance should create a new instance
            Retrofit firstInstance = RetrofitClientInstance.getRetrofitInstance();
            // Subsequent call should return the same instance
            Retrofit secondInstance = RetrofitClientInstance.getRetrofitInstance();

            assertNotNull(firstInstance);
            assertSame("Returned Retrofit instances should be the same", firstInstance, secondInstance);
        }
    }
}
*/
