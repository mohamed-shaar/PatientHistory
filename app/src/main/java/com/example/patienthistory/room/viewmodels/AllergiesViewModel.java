package com.example.patienthistory.room.viewmodels;

import android.app.Application;

import com.example.patienthistory.room.entities.Allergies;
import com.example.patienthistory.room.repositories.AllergiesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AllergiesViewModel extends AndroidViewModel {

    private AllergiesRepository allergiesRepository;
    private LiveData<List<Allergies>> allAllergies;

    public AllergiesViewModel(@NonNull Application application){
        super(application);
        allergiesRepository = new AllergiesRepository(application);
        allAllergies = allergiesRepository.getAllergies();
    }

    public void insert(Allergies allergies) { allergiesRepository.insert(allergies);}

    public void delete(Allergies allergies) {allergiesRepository.delete(allergies);}

    public void update(Allergies allergies) {allergiesRepository.update(allergies);}

    public void deleteAllAllergies() { allergiesRepository.deleteAllAllergies();}

    public LiveData<List<Allergies>> getAllAllergies() {return allAllergies;}
}
