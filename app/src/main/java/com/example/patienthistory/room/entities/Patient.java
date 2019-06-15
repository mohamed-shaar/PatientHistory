package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table")
public class Patient {

    //patient attributes
    @PrimaryKey
    private int id;

    private String address;

    private String bloodType;

    private String socialStatus;

    private int numberOfChildren;


    //foreign keys which are all the same value
    private int allergiesId;

    private int dietaryInformationId;

    private int familyDiseasesId;

    private int physicalExamId;

    private int remediesId;

    private int socialHabitId;

    private int surgeryId;

    @Ignore
    public Patient(int id, String address, String bloodType, String socialStatus, int numberOfChildren, int allergiesId, int dietaryInformationId, int familyDiseasesId, int physicalExamId, int remediesId, int socialHabitId, int surgeryId) {
        this.id = id;
        this.address = address;
        this.bloodType = bloodType;
        this.socialStatus = socialStatus;
        this.numberOfChildren = numberOfChildren;
        this.allergiesId = allergiesId;
        this.dietaryInformationId = dietaryInformationId;
        this.familyDiseasesId = familyDiseasesId;
        this.physicalExamId = physicalExamId;
        this.remediesId = remediesId;
        this.socialHabitId = socialHabitId;
        this.surgeryId = surgeryId;
    }

    public Patient(String address, String bloodType, String socialStatus, int numberOfChildren, int allergiesId, int dietaryInformationId, int familyDiseasesId, int physicalExamId, int remediesId, int socialHabitId, int surgeryId) {
        this.address = address;
        this.bloodType = bloodType;
        this.socialStatus = socialStatus;
        this.numberOfChildren = numberOfChildren;
        this.allergiesId = allergiesId;
        this.dietaryInformationId = dietaryInformationId;
        this.familyDiseasesId = familyDiseasesId;
        this.physicalExamId = physicalExamId;
        this.remediesId = remediesId;
        this.socialHabitId = socialHabitId;
        this.surgeryId = surgeryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getAllergiesId() {
        return allergiesId;
    }

    public void setAllergiesId(int allergiesId) {
        this.allergiesId = allergiesId;
    }

    public int getDietaryInformationId() {
        return dietaryInformationId;
    }

    public void setDietaryInformationId(int dietaryInformationId) {
        this.dietaryInformationId = dietaryInformationId;
    }

    public int getFamilyDiseasesId() {
        return familyDiseasesId;
    }

    public void setFamilyDiseasesId(int familyDiseasesId) {
        this.familyDiseasesId = familyDiseasesId;
    }

    public int getPhysicalExamId() {
        return physicalExamId;
    }

    public void setPhysicalExamId(int physicalExamId) {
        this.physicalExamId = physicalExamId;
    }

    public int getRemediesId() {
        return remediesId;
    }

    public void setRemediesId(int remediesId) {
        this.remediesId = remediesId;
    }

    public int getSocialHabitId() {
        return socialHabitId;
    }

    public void setSocialHabitId(int socialHabitId) {
        this.socialHabitId = socialHabitId;
    }

    public int getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(int surgeryId) {
        this.surgeryId = surgeryId;
    }
}
