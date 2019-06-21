package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.DietaryInformation;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DietaryInformationDao {

    @Insert
    void insert(DietaryInformation dietaryInformation);

    @Delete
    void delete(DietaryInformation dietaryInformation);

    @Update
    void update(DietaryInformation dietaryInformation);

    @Query("DELETE FROM dietary_information_table")
    void deleteAllDietaryInformation();

    @Query("SELECT * FROM dietary_information_table")
    LiveData<List<DietaryInformation>> getAllDietaryInformation();
}
