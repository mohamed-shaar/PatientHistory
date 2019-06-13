package com.example.patienthistory.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.PrimaryKey;

public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String address;

    private String bloodType;

    private String socialStatus;

    private int numberOfChildren;

    private String socialHabitId;

    private String physicalExamId;

    public Patient(String address, String bloodType, String socialStatus, int numberOfChildren) {
        this.address = address;
        this.bloodType = bloodType;
        this.socialStatus = socialStatus;
        this.numberOfChildren = numberOfChildren;
    }

    public String getSocialHabitId() {
        return socialHabitId;
    }

    public void setSocialHabitId(String socialHabitId) {
        this.socialHabitId = socialHabitId;
    }

    public String getPhysicalExamId() {
        return physicalExamId;
    }

    public void setPhysicalExamId(String physicalExamId) {
        this.physicalExamId = physicalExamId;
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

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(@NonNull String bloodType) {
        this.bloodType = bloodType;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(@NonNull String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }
}
