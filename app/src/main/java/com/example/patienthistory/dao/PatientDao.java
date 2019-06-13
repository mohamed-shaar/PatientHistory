package com.example.patienthistory.dao;

import com.example.patienthistory.entity.PatientWithData;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PatientDao {

    @Insert
    void Insert(PatientWithData patientWithData);

    @Update
    void Update(PatientWithData patientWithData);

    @Delete
    void Delete(PatientWithData patientWithData);

    @Query("SELECT * FROM patient_table")
    LiveData<PatientWithData> getData();
}
