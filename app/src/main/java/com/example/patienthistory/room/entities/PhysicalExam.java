package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "physical_exam_table", foreignKeys = @ForeignKey(entity = Patient.class,
        parentColumns = "id",
        childColumns = "physicalExamPatientId",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index("physicalExamPatientId")})

public class PhysicalExam {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int physicalExamPatientId;

    private String bloodPressure;
    private String heartRate;

    public PhysicalExam(int physicalExamPatientId, String bloodPressure, String heartRate) {
        this.physicalExamPatientId = physicalExamPatientId;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
    }

    public int getPhysicalExamPatientId() {
        return physicalExamPatientId;
    }

    public void setPhysicalExamPatientId(int physicalExamPatientId) {
        this.physicalExamPatientId = physicalExamPatientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
