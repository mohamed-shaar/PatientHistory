package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.RemediesDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.Remedies;

import java.util.List;

import androidx.lifecycle.LiveData;
/**
 * This class performs CRUD operation on a background thread for the corresponding entity
 */
public class RemediesRepository {

    private RemediesDao remediesDao;
    private LiveData<List<Remedies>> allRemedies;

    public RemediesRepository(Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        remediesDao = database.remediesDao();
        allRemedies = remediesDao.getAllRemedies();
    }

    public void insert(Remedies remedies){ new InsertRemediesAsyncTask(remediesDao).execute(remedies);}

    public void update(Remedies remedies){ new UpdateRemediesAsyncTask(remediesDao).execute(remedies);}

    public void delete(Remedies remedies){ new DeleteAllRemediesAsyncTask(remediesDao).execute(remedies);}

    public void deleteAll(){ new DeleteAllRemediesAsyncTask(remediesDao).execute();}

    public LiveData<List<Remedies>> getAllRemedies() {
        return allRemedies;
    }

    private static class InsertRemediesAsyncTask extends AsyncTask<Remedies, Void, Void> {

        private RemediesDao remediesDao;

        private InsertRemediesAsyncTask(RemediesDao remediesDao) {
            this.remediesDao = remediesDao;
        }

        @Override
        protected Void doInBackground(Remedies... remedies) {
            remediesDao.insert(remedies[0]);
            return null;
        }
    }

    private static class DeleteRemediesAsyncTask extends AsyncTask<Remedies, Void, Void> {

        private RemediesDao remediesDao;

        private DeleteRemediesAsyncTask(RemediesDao remediesDao) {
            this.remediesDao = remediesDao;
        }

        @Override
        protected Void doInBackground(Remedies... remedies) {
            remediesDao.delete(remedies[0]);
            return null;
        }
    }

    private static class DeleteAllRemediesAsyncTask extends AsyncTask<Remedies, Void, Void> {

        private RemediesDao remediesDao;

        private DeleteAllRemediesAsyncTask(RemediesDao remediesDao) {
            this.remediesDao = remediesDao;
        }

        @Override
        protected Void doInBackground(Remedies... remedies) {
            remediesDao.deleteAllRemedies();
            return null;
        }
    }

    private static class UpdateRemediesAsyncTask extends AsyncTask<Remedies, Void, Void> {

        private RemediesDao remediesDao;

        private UpdateRemediesAsyncTask(RemediesDao remediesDao) {
            this.remediesDao = remediesDao;
        }

        @Override
        protected Void doInBackground(Remedies... remedies) {
            remediesDao.update(remedies[0]);
            return null;
        }
    }
}
