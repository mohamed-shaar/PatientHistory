package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "remedies_table",
        foreignKeys = @ForeignKey(entity = Patient.class,
                parentColumns = "remediesId",
                childColumns = "remediesPatientId",
                onDelete = ForeignKey.CASCADE))
public class Remedies {
    @PrimaryKey
    private int id;

    private int remediesPatientId;

    private String name;
    private String startDate;
    private int dose;
    private String outcome;

    public Remedies(int remediesPatientId, String name, String startDate, int dose, String outcome) {
        this.remediesPatientId = remediesPatientId;
        this.name = name;
        this.startDate = startDate;
        this.dose = dose;
        this.outcome = outcome;
    }

    public int getRemediesPatientId() {
        return remediesPatientId;
    }

    public void setRemediesPatientId(int remediesPatientId) {
        this.remediesPatientId = remediesPatientId;
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
