package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.Patient;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
/**
 * This interface uses the room library to perform CRUD operations to the corresponding entity
 * on the sqlite mobile database.
 */
@Dao
public interface PatientDao {

    @Insert
    void insert(Patient patient);

    @Update
    void update(Patient patient);

    @Delete
    void delete(Patient patient);

    @Query("SELECT * FROM patient_table")
    LiveData<Patient> getData();
}
