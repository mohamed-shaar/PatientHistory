package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.FamilyDiseasesDao;
import com.example.patienthistory.room.dao.PatientDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.FamilyDiseases;
import com.example.patienthistory.room.entities.Patient;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class FamilyDiseasesRepository {

    private FamilyDiseasesDao familyDiseasesDao;
    private LiveData<List<FamilyDiseases>> familyDiseasesList;

    public FamilyDiseasesRepository(@NonNull Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        familyDiseasesDao = database.familyDiseasesDao();
        familyDiseasesList = familyDiseasesDao.getAllFamilyDiseases();
    }

    private static class InsertFamilyDiseasesAsyncTask extends AsyncTask<FamilyDiseases, Void, Void> {

        private FamilyDiseasesDao familyDiseasesDao;

        private InsertFamilyDiseasesAsyncTask(FamilyDiseasesDao familyDiseasesDao) {
            this.familyDiseasesDao = familyDiseasesDao;
        }

        @Override
        protected Void doInBackground(FamilyDiseases... familyDiseases) {
            familyDiseasesDao.insert(familyDiseases[0]);
            return null;
        }
    }

    public void insert(FamilyDiseases familyDiseases) { new InsertFamilyDiseasesAsyncTask(familyDiseasesDao);}

    public void delete(FamilyDiseases familyDiseases) { new DeleteFamilyDiseasesAsyncTask(familyDiseasesDao);}

    public void deleteAll(){ new DeleteAllFamilyDiseasesAsyncTask(familyDiseasesDao);}

    public LiveData<List<FamilyDiseases>> getFamilyDiseasesList() {
        return familyDiseasesList;
    }

    private static class DeleteFamilyDiseasesAsyncTask extends AsyncTask<FamilyDiseases, Void, Void> {

        private FamilyDiseasesDao familyDiseasesDao;

        private DeleteFamilyDiseasesAsyncTask(FamilyDiseasesDao familyDiseasesDao) {
            this.familyDiseasesDao = familyDiseasesDao;
        }

        @Override
        protected Void doInBackground(FamilyDiseases... familyDiseases) {
            familyDiseasesDao.delete(familyDiseases[0]);
            return null;
        }
    }

    private static class DeleteAllFamilyDiseasesAsyncTask extends AsyncTask<FamilyDiseases, Void, Void> {

        private FamilyDiseasesDao familyDiseasesDao;

        private DeleteAllFamilyDiseasesAsyncTask(FamilyDiseasesDao familyDiseasesDao) {
            this.familyDiseasesDao = familyDiseasesDao;
        }

        @Override
        protected Void doInBackground(FamilyDiseases... familyDiseases) {
            familyDiseasesDao.deleteAllFamilyDiseases();
            return null;
        }
    }
}
