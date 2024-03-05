package com.mobiletooldatabaseclient.model;

public class SampleComposite {

    private final long sampleId;
    private final Integer scode;
    private final String samplePhase;
    private Integer location;

    // Plusz információk a Sample-hoz
    private final String projectName;
    private final long assemblyId;

    // Konstruktorok, getterek és setterek

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

    public Integer getScode() {
        return scode;
    }

    public String getSamplePhase() {
        return samplePhase;
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

    public Long getAssemblyId() {
        return assemblyId;
    }

}
