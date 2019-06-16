package com.example.patienthistory.room.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "allergies_table",
        foreignKeys = @ForeignKey(entity = Patient.class,
        parentColumns = "id",
        childColumns = "allergiesPatientId",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index("allergiesPatientId")})
public class Allergies {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int allergiesPatientId;

    private String allergyName;

    public Allergies(int allergiesPatientId, String allergyName) {
        this.allergiesPatientId = allergiesPatientId;
        this.allergyName = allergyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAllergiesPatientId() {
        return allergiesPatientId;
    }

    public void setAllergiesPatientId(int allergiesPatientId) {
        this.allergiesPatientId = allergiesPatientId;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }
}
