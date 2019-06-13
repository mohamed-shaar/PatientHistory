package com.example.patienthistory.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Patient.class,
        parentColumns = "physicalExamId",
        childColumns = "company_id",
        onDelete = ForeignKey.CASCADE))
public class PhysicalExam {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int patientId;

    private String bloodPressure;
    private String heartRate;

    public PhysicalExam(int patientId, String bloodPressure, String heartRate) {
        this.patientId = patientId;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
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

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }
}
