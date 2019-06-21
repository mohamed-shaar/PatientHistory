package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.Surgery;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SurgeryDao {

    @Insert
    void insert(Surgery surgery);

    @Delete
    void delete(Surgery surgery);

    @Update
    void update(Surgery surgery);

    @Query("DELETE FROM surgery_table")
    void deleteAllSurgeries();

    @Query("SELECT * FROM surgery_table")
    LiveData<List<Surgery>> getAllSurgeries();
}
