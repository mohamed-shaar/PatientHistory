package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.PatientDao;
import com.example.patienthistory.room.dao.SurgeryDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.Patient;
import com.example.patienthistory.room.entities.Surgery;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SurgeryRepository {

    private SurgeryDao surgeryDao;
    private LiveData<List<Surgery>> surgeriesLiveData;

    public SurgeryRepository(Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        surgeryDao = database.surgeryDao();
        surgeriesLiveData = surgeryDao.getAllSurgeries();
    }

    public void insert(Surgery surgery){ new InsertSurgeryAsyncTask(surgeryDao).execute(surgery);}

    public void delete(Surgery surgery){ new DeleteSurgeryAsyncTask(surgeryDao).execute(surgery);}

    public void deleteAll(){ new DeleteAllSurgeryAsyncTask(surgeryDao).execute();}

    public LiveData<List<Surgery>> getSurgeriesLiveData() {
        return surgeriesLiveData;
    }

    private static class InsertSurgeryAsyncTask extends AsyncTask<Surgery, Void, Void> {

        private SurgeryDao surgeryDao;

        private InsertSurgeryAsyncTask(SurgeryDao surgeryDao) {
            this.surgeryDao = surgeryDao;
        }

        @Override
        protected Void doInBackground(Surgery... surgeries) {
            surgeryDao.insert(surgeries[0]);
            return null;
        }
    }

    private static class DeleteSurgeryAsyncTask extends AsyncTask<Surgery, Void, Void> {

        private SurgeryDao surgeryDao;

        private DeleteSurgeryAsyncTask(SurgeryDao surgeryDao) {
            this.surgeryDao = surgeryDao;
        }

        @Override
        protected Void doInBackground(Surgery... surgeries) {
            surgeryDao.delete(surgeries[0]);
            return null;
        }
    }

    private static class DeleteAllSurgeryAsyncTask extends AsyncTask<Surgery, Void, Void> {

        private SurgeryDao surgeryDao;

        private DeleteAllSurgeryAsyncTask(SurgeryDao surgeryDao) {
            this.surgeryDao = surgeryDao;
        }

        @Override
        protected Void doInBackground(Surgery... surgeries) {
            surgeryDao.deleteAllSurgeries();
            return null;
        }
    }
}
