package com.example.patienthistory.room.dao;

import com.example.patienthistory.room.entities.PhysicalExam;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PhysicalExamDao {

    @Insert
    void insert(PhysicalExam physicalExam);

    @Update
    void update(PhysicalExam physicalExam);

    @Delete
    void delete(PhysicalExam physicalExam);

    @Query("SELECT * FROM physical_exam_table")
    LiveData<PhysicalExam> getPhysicalExam();
}
