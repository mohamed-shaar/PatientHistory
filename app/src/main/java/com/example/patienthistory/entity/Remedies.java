package com.example.patienthistory.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "remedies")
public class Remedies {
    @PrimaryKey
    private int id;

    private int patientId;

    private String name;
    private String startDate;
    private int dose;
    private String outcome;

    public Remedies(int patientId, String name, String startDate, int dose, String outcome) {
        this.patientId = patientId;
        this.name = name;
        this.startDate = startDate;
        this.dose = dose;
        this.outcome = outcome;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
