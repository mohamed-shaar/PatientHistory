package com.example.patienthistory.room.viewmodels;

import android.app.Application;

import com.example.patienthistory.room.entities.Surgery;
import com.example.patienthistory.room.repositories.SurgeryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SurgeryViewModel extends AndroidViewModel {

    private SurgeryRepository surgeryRepository;
    private LiveData<List<Surgery>> allSurgeries;

    public SurgeryViewModel(@NonNull Application application) {
        super(application);
        surgeryRepository = new SurgeryRepository(application);
        allSurgeries = surgeryRepository.getSurgeriesLiveData();
    }

    public void insert(Surgery surgery){
        surgeryRepository.insert(surgery);
    }

    public void delete(Surgery surgery){
        surgeryRepository.delete(surgery);
    }

    public LiveData<List<Surgery>> getAllSurgeries() {
        return allSurgeries;
    }
}
