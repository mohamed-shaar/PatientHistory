package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.AllergiesDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.Allergies;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * This class performs CRUD operation on a background thread for the corresponding entity
 */
public class AllergiesRepository {

    private AllergiesDao allergiesDao;
    private LiveData<List<Allergies>> allergies;

    public AllergiesRepository(Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        allergiesDao = database.allergiesDao();
        allergies = allergiesDao.getAllAllergies();
    }

    public void insert(Allergies allergies){ new InsertAllergiesAsyncTask(allergiesDao).execute(allergies);}

    public void update(Allergies allergies){ new UpdateAllergiesAsyncTask(allergiesDao).execute(allergies);}

    public void delete(Allergies allergies){ new DeleteAllergiesAsyncTask(allergiesDao).execute(allergies);}

    public void deleteAllAllergies(){ new DeleteAllAllergiesAsyncTask(allergiesDao).execute();}

    public LiveData<List<Allergies>> getAllergies() { return allergies; }

    private static class InsertAllergiesAsyncTask extends AsyncTask<Allergies, Void, Void> {

        private AllergiesDao allergiesDao;

        private InsertAllergiesAsyncTask(AllergiesDao allergiesDao) {
            this.allergiesDao = allergiesDao;
        }

        @Override
        protected Void doInBackground(Allergies... allergies) {
            allergiesDao.insert(allergies[0]);
            return null;
        }
    }

    private static class DeleteAllergiesAsyncTask extends AsyncTask<Allergies, Void, Void> {

        private AllergiesDao allergiesDao;

        private DeleteAllergiesAsyncTask(AllergiesDao allergiesDao) {
            this.allergiesDao = allergiesDao;
        }

        @Override
        protected Void doInBackground(Allergies... allergies) {
            allergiesDao.delete(allergies[0]);
            return null;
        }
    }

    private static class DeleteAllAllergiesAsyncTask extends AsyncTask<Allergies, Void, Void> {

        private AllergiesDao allergiesDao;

        private DeleteAllAllergiesAsyncTask(AllergiesDao allergiesDao) {
            this.allergiesDao = allergiesDao;
        }

        @Override
        protected Void doInBackground(Allergies... allergies) {
            allergiesDao.deleteAllAllergies();
            return null;
        }
    }

    private static class UpdateAllergiesAsyncTask extends AsyncTask<Allergies, Void, Void> {

        private AllergiesDao allergiesDao;

        private UpdateAllergiesAsyncTask(AllergiesDao allergiesDao) {
            this.allergiesDao = allergiesDao;
        }

        @Override
        protected Void doInBackground(Allergies... allergies) {
            allergiesDao.Update(allergies[0]);
            return null;
        }
    }

}
