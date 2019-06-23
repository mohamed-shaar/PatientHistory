package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.Allergies;

import java.util.List;

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
public interface AllergiesDao {

    @Insert
    void insert(Allergies allergies);

    @Delete
    void delete(Allergies allergies);

    @Update
    void Update(Allergies allergies);

    @Query("DELETE FROM allergies_table")
    void deleteAllAllergies();

    @Query("SELECT * FROM allergies_table")
    LiveData<List<Allergies>> getAllAllergies();
}
