package com.example.patienthistory.entity;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Patient.class,
        parentColumns = "socialHabitId",
        childColumns = "patientId",
        onDelete = ForeignKey.CASCADE))
public class SocialHabit {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int patientId;

    private boolean drugs;

    private boolean tobacco;

    private boolean alcohol;

    public SocialHabit(int patientId, boolean drugs, boolean tobacco, boolean alcohol) {
        this.patientId = patientId;
        this.drugs = drugs;
        this.tobacco = tobacco;
        this.alcohol = alcohol;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public boolean isDrugs() {
        return drugs;
    }

    public void setDrugs(boolean drugs) {
        this.drugs = drugs;
    }

    public boolean isTobacco() {
        return tobacco;
    }

    public void setTobacco(boolean tobacco) {
        this.tobacco = tobacco;
    }

    public boolean isAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }
}
