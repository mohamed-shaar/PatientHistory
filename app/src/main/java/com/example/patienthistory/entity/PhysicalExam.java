package com.example.patienthistory.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Patient.class,
        parentColumns = "physicalExamId",
        childColumns = "company_id",
        onDelete = ForeignKey.CASCADE))
public class PhysicalExam {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int patientId;

    private String bloodPressure;
    private String heartRate;
}
