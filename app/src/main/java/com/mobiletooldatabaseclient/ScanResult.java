package com.mobiletooldatabaseclient;

import com.mobiletooldatabaseclient.model.SampleComposite;

public class ScanResult {

    private static ScanResult instance;

    private SampleComposite sample;
    private int code;

    private ScanResult() {
    }

    public static ScanResult getInstance(){
        if(instance==null){
            instance=new ScanResult();
        }
        return instance;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SampleComposite getSample() {
        return sample;
    }

    public void setSample(SampleComposite sample) {
        this.sample = sample;
    }
}
