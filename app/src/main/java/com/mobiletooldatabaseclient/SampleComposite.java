package com.mobiletooldatabaseclient;

public class SampleComposite {

    private long sampleId;
    private Integer scode;
    private String samplePhase;
    private Integer location;

    // Plusz információk a Sample-hoz
    private String projectName;
    private long assemblyId;

    // Konstruktorok, getterek és setterek

    public SampleComposite() {
    }

    // Tegyük fel, hogy ezeket az adatokat egy JOIN lekérdezésből kapjuk
    public SampleComposite(Long sampleId, Integer scode, String samplePhase, Integer location, String projectName, Long assemblyId) {
        this.sampleId = sampleId;
        this.scode = scode;
        this.samplePhase = samplePhase;
        this.location = location;
        this.projectName = projectName;
        this.assemblyId = assemblyId;
    }

    // Getterek és setterek

    public Long getSampleId() {
        return sampleId;
    }

    public void setSampleId(Long sampleId) {
        this.sampleId = sampleId;
    }

    public Integer getScode() {
        return scode;
    }

    public void setScode(Integer scode) {
        this.scode = scode;
    }

    public String getSamplePhase() {
        return samplePhase;
    }

    public void setSamplePhase(String samplePhase) {
        this.samplePhase = samplePhase;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(Long assemblyId) {
        this.assemblyId = assemblyId;
    }

    // További getterek és setterek
}
