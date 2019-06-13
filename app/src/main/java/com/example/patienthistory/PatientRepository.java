package com.example.patienthistory;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.dao.PatientDao;
import com.example.patienthistory.entity.PatientWithData;

import androidx.lifecycle.LiveData;

public class PatientRepository {

    private PatientDao patientDao;
    private LiveData<PatientWithData> patient;

    public PatientRepository(Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDao = database.patientDao();
        patient = patientDao.getData();
    }

    public void insert(PatientWithData patientWithData) { new InsertNoteAsyncTask(patientDao).execute(patientWithData); }

    public void update(PatientWithData patientWithData) { new UpdateNoteAsyncTask(patientDao).execute(patientWithData); }

    public void delete(PatientWithData patientWithData) { new DeleteNoteAsyncTask(patientDao).execute(patientWithData); }

    private static class InsertNoteAsyncTask extends AsyncTask<PatientWithData, Void, Void> {

        private PatientDao patientDao;

        private InsertNoteAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(PatientWithData... patientWithData) {
            patientDao.Insert(patientWithData[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<PatientWithData, Void, Void> {

        private PatientDao patientDao;

        private UpdateNoteAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(PatientWithData... patientWithData) {
            patientDao.Update(patientWithData[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<PatientWithData, Void, Void> {

        private PatientDao patientDao;

        private DeleteNoteAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(PatientWithData... patientWithData) {
            patientDao.Delete(patientWithData[0]);
            return null;
        }
    }
}
