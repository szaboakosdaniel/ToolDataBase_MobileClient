package com.mobiletooldatabaseclient;

import com.mobiletooldatabaseclient.model.SampleComposite;

/**
 * Singleton class to hold and manage the results of a scan operation.
 * This class encapsulates the details of a scanned sample and the associated code.
 * It ensures that only one instance of the scan result is active at any given time
 * throughout the application, providing a centralized point of access to the scan data.
 */
public class ScanResult {
    // Static variable to hold the single instance of the ScanResult class
    private static ScanResult instance;

    // Variable to hold the detailed information of the scanned sample
    private SampleComposite sample;

    // Variable to hold the scanned code
    private int code;


    /**
     * Private constructor to prevent instantiation from outside the class.
     * This is a key part of the Singleton pattern, ensuring control over the creation of the instance.
     */
    private ScanResult() {
    }

    /**
     * Public method to get the singleton instance of the ScanResult class.
     * If the instance does not exist, it creates a new one; otherwise, it returns the existing instance.
     * This ensures that only one instance of the ScanResult class is created and used throughout the application.
     *
     * @return The single instance of the ScanResult class.
     */
    public static ScanResult getInstance(){
        if(instance==null){
            instance=new ScanResult();
        }
        return instance;
    }

    /**
     * Gets the scanned code.
     *
     * @return The code obtained from the scan operation.
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the scanned code.
     * This method allows updating the code associated with the scan result.
     *
     * @param code The code to set as the result of a scan operation.
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets the SampleComposite object associated with the scan result.
     * This object contains detailed information about the scanned sample.
     *
     * @return The SampleComposite object containing the details of the scanned sample.
     */
    public SampleComposite getSample() {
        return sample;
    }

    /**
     * Sets the SampleComposite object for the scan result.
     * This method allows associating detailed sample information with the scan result.
     *
     * @param sample The SampleComposite object to associate with the scan result.
     */
    public void setSample(SampleComposite sample) {
        this.sample = sample;
    }

    /**
     * Resets all variables in the ScanResult instance to their default values or null.
     * This method effectively clears the scan result data.
     */
    public void reset() {
        this.sample = null;
        this.code = 0; // or whatever default value makes sense for your application
    }
}
