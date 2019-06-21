package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.Remedies;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RemediesDao {

    @Insert
    void insert(Remedies remedies);

    @Delete
    void delete(Remedies remedies);

    @Update
    void update(Remedies remedies);

    @Query("DELETE FROM remedies_table")
    void deleteAllRemedies();

    @Query("SELECT * FROM remedies_table")
    LiveData<List<Remedies>> getAllRemedies();
}
