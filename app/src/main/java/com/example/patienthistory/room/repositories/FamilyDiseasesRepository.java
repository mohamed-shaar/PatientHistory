package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.FamilyDiseasesDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.FamilyDiseases;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
/**
 * This class performs CRUD operation on a background thread for the corresponding entity
 */
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

    public void insert(FamilyDiseases familyDiseases) { new InsertFamilyDiseasesAsyncTask(familyDiseasesDao).execute(familyDiseases);}

    public void update(FamilyDiseases familyDiseases){ new UpdateFamilyDiseasesAsyncTask(familyDiseasesDao).execute(familyDiseases);}

    public void delete(FamilyDiseases familyDiseases) { new DeleteFamilyDiseasesAsyncTask(familyDiseasesDao).execute(familyDiseases);}

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

    private static class UpdateFamilyDiseasesAsyncTask extends AsyncTask<FamilyDiseases, Void, Void> {

        private FamilyDiseasesDao familyDiseasesDao;

        private UpdateFamilyDiseasesAsyncTask(FamilyDiseasesDao familyDiseasesDao) {
            this.familyDiseasesDao = familyDiseasesDao;
        }

        @Override
        protected Void doInBackground(FamilyDiseases... familyDiseases) {
            familyDiseasesDao.update(familyDiseases[0]);
            return null;
        }
    }
}
