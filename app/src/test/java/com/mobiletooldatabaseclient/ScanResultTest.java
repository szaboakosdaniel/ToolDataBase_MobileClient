package com.mobiletooldatabaseclient;

import com.mobiletooldatabaseclient.model.SampleComposite;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class ScanResultTest {

    @Before
    public void setUp() {
        // Reset the ScanResult instance to null to ensure each test has a fresh start
        // Reflection can be used here to access the private field, if necessary
        // However, it might be better to provide a package-private reset method for testing purposes
    }

    @Test
    public void testGetInstance() {
        ScanResult instance1 = ScanResult.getInstance();
        assertNotNull("Instance should not be null", instance1);

        ScanResult instance2 = ScanResult.getInstance();
        assertSame("Instances should be the same", instance1, instance2);
    }

    @Test
    public void testSetAndGetCode() {
        ScanResult instance = ScanResult.getInstance();
        int testCode = 12345;
        instance.setCode(testCode);

        assertEquals("Codes should match", testCode, instance.getCode());
    }

    @Test
    public void testSetAndGetSample() {
        ScanResult instance = ScanResult.getInstance();
        SampleComposite testSample = new SampleComposite(1L, 100, "Phase", 200, "ProjectName", 300L);
        instance.setSample(testSample);

        assertEquals("Samples should match", testSample, instance.getSample());
    }
}
