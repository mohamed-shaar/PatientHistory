package com.example.patienthistory.room.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "social_habit_table",
        foreignKeys = @ForeignKey(entity = Patient.class,
        parentColumns = "id",
        childColumns = "socialHabitPatientId",
        onDelete = ForeignKey.CASCADE),
indices = {@Index("socialHabitPatientId")})
public class SocialHabit {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int socialHabitPatientId;

    private boolean drugs;

    private boolean tobacco;

    private boolean alcohol;

    public SocialHabit(int socialHabitPatientId, boolean drugs, boolean tobacco, boolean alcohol) {
        this.socialHabitPatientId = socialHabitPatientId;
        this.drugs = drugs;
        this.tobacco = tobacco;
        this.alcohol = alcohol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSocialHabitPatientId() {
        return socialHabitPatientId;
    }

    public void setSocialHabitPatientId(int socialHabitPatientId) {
        this.socialHabitPatientId = socialHabitPatientId;
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
