package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "surgery_table",
        foreignKeys = @ForeignKey(entity = Patient.class,
                parentColumns = "surgeryId",
                childColumns = "surgeryPatientId",
                onDelete = ForeignKey.CASCADE))
public class Surgery {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int surgeryPatientId;

    private String name;

    public Surgery(int surgeryPatientId, String name) {
        this.surgeryPatientId = surgeryPatientId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSurgeryPatientId() {
        return surgeryPatientId;
    }

    public void setSurgeryPatientId(int surgeryPatientId) {
        this.surgeryPatientId = surgeryPatientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
