package com.example.patienthistory.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.PrimaryKey;

public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Nullable
    private String address;

    @NonNull
    private String bloodType;

    @NonNull
    private String socialStatus;

    @Nullable
    private int numberOfChildren;

    @NonNull
    private String socialHabitId;

    @NonNull
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

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    @NonNull
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(@NonNull String bloodType) {
        this.bloodType = bloodType;
    }

    @NonNull
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
