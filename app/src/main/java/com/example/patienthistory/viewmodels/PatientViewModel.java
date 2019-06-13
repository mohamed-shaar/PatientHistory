package com.example.patienthistory.viewmodels;

import android.app.Application;

import com.example.patienthistory.PatientRepository;
import com.example.patienthistory.entity.PatientWithData;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository repository;
    private LiveData<PatientWithData> patient;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        repository = new PatientRepository(application);
        patient = repository.getData();
    }

    public void insert(PatientWithData patientWithData){
        repository.insert(patientWithData);
    }

    public void update(PatientWithData patientWithData){
        repository.update(patientWithData);
    }

    public void delete(PatientWithData patientWithData){
        repository.delete(patientWithData);
    }

    public LiveData<PatientWithData> getData(){
        return patient;
    }
}
