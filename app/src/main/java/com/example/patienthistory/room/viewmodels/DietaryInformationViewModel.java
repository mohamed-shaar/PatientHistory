package com.example.patienthistory.room.viewmodels;

import android.app.Application;

import com.example.patienthistory.room.entities.DietaryInformation;
import com.example.patienthistory.room.repositories.DietaryInformationRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * ViewModels act as the layer between views and repositories for accessing database
 */

public class DietaryInformationViewModel extends AndroidViewModel {

    private DietaryInformationRepository dietaryInformationRepository;
    private LiveData<List<DietaryInformation>> dietaryInformationList;

    public DietaryInformationViewModel(@NonNull Application application) {
        super(application);
        dietaryInformationRepository = new DietaryInformationRepository(application);
        dietaryInformationList = dietaryInformationRepository.getAllDietaryInformation();
    }

    public void insert(DietaryInformation dietaryInformation){
        dietaryInformationRepository.insert(dietaryInformation);
    }

    public void update(DietaryInformation dietaryInformation){
        dietaryInformationRepository.update(dietaryInformation);
    }

    public void delete(DietaryInformation dietaryInformation){
        dietaryInformationRepository.delete(dietaryInformation);
    }

    public void deleteAll(){ dietaryInformationRepository.deleteAll();}

    public LiveData<List<DietaryInformation>> getDietaryInformationList() { return dietaryInformationList;}
}
