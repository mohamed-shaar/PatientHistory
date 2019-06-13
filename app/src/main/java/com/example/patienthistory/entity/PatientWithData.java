package com.example.patienthistory.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

@Entity(tableName = "patient_table")
public class PatientWithData {

    @Embedded
    public Patient patient;

    @Relation(parentColumn = "id", entityColumn = "patientId", entity = Allergies.class)
    public List<Allergies> allergies;

    @Relation(parentColumn = "id", entityColumn = "patientId", entity = DietaryInformation.class)
    public List<DietaryInformation> dietaryInformation;

    @Relation(parentColumn = "id", entityColumn = "patientId", entity = FamilyDiseases.class)
    public List<FamilyDiseases> familyDiseases;

    @Relation(parentColumn = "id", entityColumn = "patientId", entity = Remedies.class)
    public List<Remedies> remedies;

    @Relation(parentColumn = "id", entityColumn = "patientId", entity = Surgery.class)
    public List<Surgery> surgeries;
}
