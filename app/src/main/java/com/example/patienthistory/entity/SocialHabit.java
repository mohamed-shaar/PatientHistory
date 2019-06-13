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

}
