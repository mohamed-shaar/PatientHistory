package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "dietary_information_table", foreignKeys = @ForeignKey(
        entity = Patient.class,
        parentColumns = "dietaryInformationId",
        childColumns = "dietaryInformationPatientId",
        onDelete = ForeignKey.CASCADE
))
public class DietaryInformation {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int dietaryInformationPatientId;

    private String restrictions;

    private String supplements;

    private String stimulants;

    public DietaryInformation(int dietaryInformationPatientId, String restrictions, String supplements, String stimulants) {
        this.dietaryInformationPatientId = dietaryInformationPatientId;
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

    public int getDietaryInformationPatientId() {
        return dietaryInformationPatientId;
    }

    public void setDietaryInformationPatientId(int dietaryInformationPatientId) {
        this.dietaryInformationPatientId = dietaryInformationPatientId;
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
