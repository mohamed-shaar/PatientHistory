package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.Patient;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PatientDao {

    @Insert
    void Insert(Patient patient);

    @Update
    void Update(Patient patient);

    @Delete
    void Delete(Patient patient);

    @Query("SELECT * FROM patient_table")
    LiveData<Patient> getData();
}
