package com.example.patienthistory.room.viewmodels;

import android.app.Application;

import com.example.patienthistory.room.entities.FamilyDiseases;
import com.example.patienthistory.room.repositories.FamilyDiseasesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * ViewModels act as the layer between views and repositories for accessing database
 */

public class FamilyDiseasesViewModel extends AndroidViewModel {

    private FamilyDiseasesRepository familyDiseasesRepository;
    private LiveData<List<FamilyDiseases>> allFamilyDiseases;

    public FamilyDiseasesViewModel(@NonNull Application application) {
        super(application);
        familyDiseasesRepository = new FamilyDiseasesRepository(application);
        allFamilyDiseases = familyDiseasesRepository.getFamilyDiseasesList();
    }

    public void insert(FamilyDiseases familyDiseases){ familyDiseasesRepository.insert(familyDiseases);}

    public void delete(FamilyDiseases familyDiseases){ familyDiseasesRepository.delete(familyDiseases);}

    public void update(FamilyDiseases familyDiseases) { familyDiseasesRepository.update(familyDiseases);}

    public LiveData<List<FamilyDiseases>> getAllFamilyDiseases() {return allFamilyDiseases;}

    public void deleteAll(){ familyDiseasesRepository.deleteAll();}
}
