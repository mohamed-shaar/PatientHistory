package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "family_diseases_table",
        foreignKeys = @ForeignKey(
                entity = Patient.class,
                parentColumns = "familyDiseasesId",
                childColumns = "familyDiseasesPatientId",
                onDelete = ForeignKey.CASCADE
        ))
public class FamilyDiseases {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String diseaseName;

    private String relation;

    private int familyDiseasesPatientId;

    public FamilyDiseases(String diseaseName, String relation, int familyDiseasesPatientId) {
        this.diseaseName = diseaseName;
        this.relation = relation;
        this.familyDiseasesPatientId = familyDiseasesPatientId;
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

    public int getFamilyDiseasesPatientId() {
        return familyDiseasesPatientId;
    }

    public void setFamilyDiseasesPatientId(int familyDiseasesPatientId) {
        this.familyDiseasesPatientId = familyDiseasesPatientId;
    }
}
