package com.example.patienthistory.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dietary_information")
public class DietaryInformation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int patientId;

    private String restrictions;

    private String supplements;

    private String stimulants;

    public DietaryInformation(int patientId, String restrictions, String supplements, String stimulants) {
        this.patientId = patientId;
        this.restrictions = restrictions;
        this.supplements = supplements;
        this.stimulants = stimulants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    public String getSupplements() {
        return supplements;
    }

    public void setSupplements(String supplements) {
        this.supplements = supplements;
    }

    public String getStimulants() {
        return stimulants;
    }

    public void setStimulants(String stimulants) {
        this.stimulants = stimulants;
    }
}
