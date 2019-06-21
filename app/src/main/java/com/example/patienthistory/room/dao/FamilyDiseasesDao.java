package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.FamilyDiseases;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FamilyDiseasesDao {

    @Insert
    void insert(FamilyDiseases familyDiseases);

    @Delete
    void delete(FamilyDiseases familyDiseases);

    @Update
    void update(FamilyDiseases familyDiseases);

    @Query("DELETE FROM family_diseases_table")
    void deleteAllFamilyDiseases();

    @Query("SELECT * FROM family_diseases_table")
    LiveData<List<FamilyDiseases>> getAllFamilyDiseases();
}
