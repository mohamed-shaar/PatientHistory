package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.SocialHabit;

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
public interface SocialHabitDao {

    @Insert
    void insert(SocialHabit socialHabit);

    @Update
    void update(SocialHabit socialHabit);

    @Delete
    void delete(SocialHabit socialHabit);

    @Query("SELECT * FROM social_habit_table")
    LiveData<SocialHabit> getSocialHabit();
}
