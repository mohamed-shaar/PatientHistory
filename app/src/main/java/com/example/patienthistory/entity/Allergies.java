package com.example.patienthistory.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "allergies")
public class Allergies {

    @PrimaryKey
    private int id;

    private int patientId;

    private String allergyName;

    public Allergies(int patientId, String allergyName) {
        this.patientId = patientId;
        this.allergyName = allergyName;
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

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }
}
