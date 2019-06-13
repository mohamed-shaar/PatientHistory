package com.example.patienthistory.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "family_diseases")
public class FamilyDiseases {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String diseaseName;

    private String relation;

    public FamilyDiseases(String diseaseName, String relation) {
        this.diseaseName = diseaseName;
        this.relation = relation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
