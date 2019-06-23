package com.example.patienthistory.room.viewmodels;

import android.app.Application;

import com.example.patienthistory.room.entities.Patient;
import com.example.patienthistory.room.repositories.PatientRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * ViewModels act as the layer between views and repositories for accessing database
 */

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository repository;
    private LiveData<Patient> patient;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        repository = new PatientRepository(application);
        patient = repository.getData();
    }

    public void insert(Patient patient) {
        repository.insert(patient);
    }

    public void update(Patient patient) {
        repository.update(patient);
    }

    public void delete(Patient patient) {
        repository.delete(patient);
    }

    public LiveData<Patient> getData() {
        return patient;
    }
}
