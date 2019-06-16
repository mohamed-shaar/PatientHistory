package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.DietaryInformationDao;
import com.example.patienthistory.room.dao.PatientDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.DietaryInformation;
import com.example.patienthistory.room.entities.Patient;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class DietaryInformationRepository {

    private DietaryInformationDao dietaryInformationDao;
    private LiveData<List<DietaryInformation>> allDietaryInformation;

    public DietaryInformationRepository(@NonNull Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        dietaryInformationDao = database.dietaryInformationDao();
        allDietaryInformation = dietaryInformationDao.getAllDietaryInformation() ;
    }

    public void insert(DietaryInformation dietaryInformation) {new InsertDietaryInformationAsyncTask(dietaryInformationDao).execute(dietaryInformation);}

    public void delete(DietaryInformation dietaryInformation){ new DeleteDietaryInformationAsyncTask(dietaryInformationDao).execute(dietaryInformation);}

    public void deleteAll(){ new DeleteAllDietaryInformationAsyncTask(dietaryInformationDao).execute();}

    public LiveData<List<DietaryInformation>> getAllDietaryInformation() { return  allDietaryInformation; }

    private static class InsertDietaryInformationAsyncTask extends AsyncTask<DietaryInformation, Void, Void> {

        private DietaryInformationDao dietaryInformationDao;

        private InsertDietaryInformationAsyncTask(DietaryInformationDao dietaryInformationDao) {
            this.dietaryInformationDao = dietaryInformationDao;
        }

        @Override
        protected Void doInBackground(DietaryInformation... dietaryInformation) {
            dietaryInformationDao.insert(dietaryInformation[0]);
            return null;
        }
    }

    private static class DeleteDietaryInformationAsyncTask extends AsyncTask<DietaryInformation, Void, Void> {

        private DietaryInformationDao dietaryInformationDao;

        private DeleteDietaryInformationAsyncTask(DietaryInformationDao dietaryInformationDao) {
            this.dietaryInformationDao = dietaryInformationDao;
        }

        @Override
        protected Void doInBackground(DietaryInformation... dietaryInformation) {
            dietaryInformationDao.delete(dietaryInformation[0]);
            return null;
        }
    }

    private static class DeleteAllDietaryInformationAsyncTask extends AsyncTask<DietaryInformation, Void, Void> {

        private DietaryInformationDao dietaryInformationDao;

        private DeleteAllDietaryInformationAsyncTask(DietaryInformationDao dietaryInformationDao) {
            this.dietaryInformationDao = dietaryInformationDao;
        }

        @Override
        protected Void doInBackground(DietaryInformation... dietaryInformation) {
            dietaryInformationDao.deleteAllDietaryInformation();
            return null;
        }
    }

}
