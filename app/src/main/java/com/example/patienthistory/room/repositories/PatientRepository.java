package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;


import com.example.patienthistory.room.dao.PatientDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.Patient;

import androidx.lifecycle.LiveData;

public class PatientRepository {

    private PatientDao patientDao;
    private LiveData<Patient> patient;

    public PatientRepository(Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        patientDao = database.patientDao();
        patient = patientDao.getData();
    }

    public void insert(Patient patient) { new InsertNoteAsyncTask(patientDao).execute(patient); }

    public void update(Patient patient) { new UpdateNoteAsyncTask(patientDao).execute(patient); }

    public void delete(Patient patient) { new DeleteNoteAsyncTask(patientDao).execute(patient); }

    public LiveData<Patient> getData() { return patient; }

    private static class InsertNoteAsyncTask extends AsyncTask<Patient, Void, Void> {

        private PatientDao patientDao;

        private InsertNoteAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patient) {
            patientDao.insert(patient[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Patient, Void, Void> {

        private PatientDao patientDao;

        private UpdateNoteAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patient) {
            patientDao.update(patient[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Patient, Void, Void> {

        private PatientDao patientDao;

        private DeleteNoteAsyncTask(PatientDao patientDao) {
            this.patientDao = patientDao;
        }

        @Override
        protected Void doInBackground(Patient... patient) {
            patientDao.delete(patient[0]);
            return null;
        }
    }
}
