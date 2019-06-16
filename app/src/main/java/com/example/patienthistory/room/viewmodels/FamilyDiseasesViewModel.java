package com.example.patienthistory.room.viewmodels;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;

import com.example.patienthistory.room.entities.FamilyDiseases;
import com.example.patienthistory.room.repositories.FamilyDiseasesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    public void deleteAll(){ familyDiseasesRepository.deleteAll();}
}