package com.mobiletooldatabaseclient.model;
/**
 * Represents a composite object for a sample item, encapsulating various details
 * about a sample such as its ID, code, phase, location, project name, and assembly ID.
 * This class is typically used to hold data retrieved from a JOIN query in a database,
 * aggregating information from multiple tables into a single object.
 */
public class SampleComposite {
    // Unique identifier for the sample. Immutable.
    private final long sampleId;
    // Sample code, which may be unique. Immutable.
    private final Integer scode;
    // Descriptive phase of the sample. Immutable.
    private final String samplePhase;
    // Location identifier where the sample is stored. Mutable.
    private Integer location;
    // Name of the project associated with the sample. Immutable.
    private final String projectName;
    // Identifier for the assembly associated with the sample. Immutable.
    private final long assemblyId;

    /**
     * Constructs a new SampleComposite instance.
     *
     * @param sampleId The unique identifier for the sample.
     * @param scode The code of the sample.
     * @param samplePhase The phase of the sample.
     * @param location The storage location identifier for the sample.
     * @param projectName The name of the project associated with the sample.
     * @param assemblyId The identifier of the assembly associated with the sample.
     */
    public SampleComposite(Long sampleId, Integer scode, String samplePhase, Integer location, String projectName, Long assemblyId) {
        this.sampleId = sampleId;
        this.scode = scode;
        this.samplePhase = samplePhase;
        this.location = location;
        this.projectName = projectName;
        this.assemblyId = assemblyId;
    }

    // Getter methods to access the properties of the sample.

    /**
     * Gets the unique identifier for the sample.
     *
     * @return The sample's unique identifier.
     */
    public Long getSampleId() {
        return sampleId;
    }

    /**
     * Gets the sample code.
     *
     * @return The code of the sample.
     */
    public Integer getScode() {
        return scode;
    }

    /**
     * Gets the phase of the sample.
     *
     * @return The sample's phase.
     */
    public String getSamplePhase() {
        return samplePhase;
    }

    /**
     * Gets the storage location identifier for the sample.
     *
     * @return The location identifier where the sample is stored.
     */
    public Integer getLocation() {
        return location;
    }

    /**
     * Sets the storage location identifier for the sample.
     * This allows changing the sample's location after the object has been created.
     *
     * @param location The new location identifier for the sample.
     */
    public void setLocation(Integer location) {
        this.location = location;
    }

    /**
     * Gets the name of the project associated with the sample.
     *
     * @return The name of the project.
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Gets the identifier for the assembly associated with the sample.
     *
     * @return The assembly's identifier.
     */
    public Long getAssemblyId() {
        return assemblyId;
    }

}
