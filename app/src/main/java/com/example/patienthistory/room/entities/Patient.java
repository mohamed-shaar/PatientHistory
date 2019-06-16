package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table", indices = {@Index("id")})
public class Patient {

    //patient attributes
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String address;

    private String bloodType;

    private String socialStatus;

    private int numberOfChildren;


    //foreign keys which are all the same value
    /*private int allergiesId;

    private int dietaryInformationId;

    private int familyDiseasesId;

    private int physicalExamId;

    private int remediesId;

    private int socialHabitId;

    private int surgeryId;*/


    /*public Patient(String address, String bloodType, String socialStatus, int numberOfChildren, int allergiesId, int dietaryInformationId, int familyDiseasesId, int physicalExamId, int remediesId, int socialHabitId, int surgeryId) {
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
    }*/

    public Patient(String address, String bloodType, String socialStatus, int numberOfChildren) {
        this.address = address;
        this.bloodType = bloodType;
        this.socialStatus = socialStatus;
        this.numberOfChildren = numberOfChildren;
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
}
