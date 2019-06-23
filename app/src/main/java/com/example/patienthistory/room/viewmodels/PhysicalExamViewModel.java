package com.example.patienthistory.room.viewmodels;

import android.app.Application;

import com.example.patienthistory.room.entities.PhysicalExam;
import com.example.patienthistory.room.repositories.PhysicalExamRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * ViewModels act as the layer between views and repositories for accessing database
 */

public class PhysicalExamViewModel extends AndroidViewModel {

    private PhysicalExamRepository physicalExamRepository;
    private LiveData<PhysicalExam> physicalExamLiveData;

    public PhysicalExamViewModel(@NonNull Application application) {
        super(application);
        physicalExamRepository = new PhysicalExamRepository(application);
        physicalExamLiveData = physicalExamRepository.getPhysicalExamLiveData();
    }

    public void insert(PhysicalExam physicalExam) { physicalExamRepository.insert(physicalExam);}

    public void update(PhysicalExam physicalExam) { physicalExamRepository.update(physicalExam);}

    public void delete(PhysicalExam physicalExam){ physicalExamRepository.delete(physicalExam);}

    public LiveData<PhysicalExam> getPhysicalExamLiveData() {
        return physicalExamLiveData;
    }
}
