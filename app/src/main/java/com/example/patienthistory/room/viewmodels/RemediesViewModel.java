package com.example.patienthistory.room.viewmodels;

import android.app.Application;

import com.example.patienthistory.room.entities.Remedies;
import com.example.patienthistory.room.repositories.RemediesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * ViewModels act as the layer between views and repositories for accessing database
 */

public class RemediesViewModel extends AndroidViewModel {

    private RemediesRepository remediesRepository;
    private LiveData<List<Remedies>> allRemedies;

    public RemediesViewModel(@NonNull Application application) {
        super(application);
        remediesRepository = new RemediesRepository(application);
        allRemedies = remediesRepository.getAllRemedies();
    }

    public void insert(Remedies remedies) { remediesRepository.insert(remedies);}

    public void update(Remedies remedies){ remediesRepository.insert(remedies);}

    public void delete(Remedies remedies){ remediesRepository.delete(remedies);}

    public void deleteAll(){ remediesRepository.deleteAll();}

    public LiveData<List<Remedies>> getAllRemedies() {
        return allRemedies;
    }
}
