package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table", indices = {@Index("id")})
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String address;

    private String bloodType;

    private String socialStatus;

    private int numberOfChildren;

    public Patient(String address, String bloodType, String socialStatus, int numberOfChildren) {
        this.address = address;
        this.bloodType = bloodType;
        this.socialStatus = socialStatus;
        this.numberOfChildren = numberOfChildren;
    }

    @Ignore
    public Patient(int id, String address, String bloodType, String socialStatus, int numberOfChildren) {
        this.id = id;
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
