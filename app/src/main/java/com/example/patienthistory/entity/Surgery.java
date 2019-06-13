package com.example.patienthistory.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "surgery")
public class Surgery {

    @PrimaryKey
    private int id;

    private int patientId;

    private String name;

    public Surgery(int patientId, String name) {
        this.patientId = patientId;
        this.name = name;
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
}
