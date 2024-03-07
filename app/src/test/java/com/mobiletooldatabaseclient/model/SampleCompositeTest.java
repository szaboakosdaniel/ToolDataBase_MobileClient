package com.mobiletooldatabaseclient.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.mobiletooldatabaseclient.model.SampleComposite;

public class SampleCompositeTest {

    private SampleComposite sampleComposite;

    @Before
    public void setUp() {
        // Initialize your object with some test data
        sampleComposite = new SampleComposite(1L, 100, "Initial Phase", 200, "Test Project", 300L);
    }

    @Test
    public void testGetSampleId() {
        assertEquals(Long.valueOf(1), sampleComposite.getSampleId());
    }

    @Test
    public void testGetScode() {
        assertEquals(Integer.valueOf(100), sampleComposite.getScode());
    }

    @Test
    public void testGetSamplePhase() {
        assertEquals("Initial Phase", sampleComposite.getSamplePhase());
    }

    @Test
    public void testGetLocation() {
        assertEquals(Integer.valueOf(200), sampleComposite.getLocation());
    }

    @Test
    public void testSetLocation() {
        sampleComposite.setLocation(400);
        assertEquals(Integer.valueOf(400), sampleComposite.getLocation());
    }

    @Test
    public void testGetProjectName() {
        assertEquals("Test Project", sampleComposite.getProjectName());
    }

    @Test
    public void testGetAssemblyId() {
        assertEquals(Long.valueOf(300), sampleComposite.getAssemblyId());
    }
}
