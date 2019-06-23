package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.PhysicalExamDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.PhysicalExam;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
/**
 * This class performs CRUD operation on a background thread for the corresponding entity
 */
public class PhysicalExamRepository {

    private PhysicalExamDao physicalExamDao;
    private LiveData<PhysicalExam> physicalExamLiveData;


    public PhysicalExamRepository(@NonNull Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        physicalExamDao = database.physicalExamDao();
        physicalExamLiveData = physicalExamDao.getPhysicalExam();
    }


    public void insert(PhysicalExam physicalExam){ new InsertPhysicalExamAsyncTask(physicalExamDao).execute(physicalExam);}

    public void update(PhysicalExam physicalExam){ new UpdatePhysicalExamAsyncTask(physicalExamDao).execute(physicalExam);}

    public  void delete(PhysicalExam physicalExam){ new DeletePhysicalExamAsyncTask(physicalExamDao).execute(physicalExam);}

    public LiveData<PhysicalExam> getPhysicalExamLiveData() {
        return physicalExamLiveData;
    }

    private static class InsertPhysicalExamAsyncTask extends AsyncTask<PhysicalExam, Void, Void> {

        private PhysicalExamDao physicalExamDao;

        private InsertPhysicalExamAsyncTask(PhysicalExamDao physicalExamDao) {
            this.physicalExamDao = physicalExamDao;
        }

        @Override
        protected Void doInBackground(PhysicalExam... physicalExams) {
            physicalExamDao.insert(physicalExams[0]);
            return null;
        }
    }

    private static class UpdatePhysicalExamAsyncTask extends AsyncTask<PhysicalExam, Void, Void> {

        private PhysicalExamDao physicalExamDao;

        private UpdatePhysicalExamAsyncTask(PhysicalExamDao physicalExamDao) {
            this.physicalExamDao = physicalExamDao;
        }

        @Override
        protected Void doInBackground(PhysicalExam... physicalExams) {
            physicalExamDao.update(physicalExams[0]);
            return null;
        }
    }

    private static class DeletePhysicalExamAsyncTask extends AsyncTask<PhysicalExam, Void, Void> {

        private PhysicalExamDao physicalExamDao;

        private DeletePhysicalExamAsyncTask(PhysicalExamDao physicalExamDao) {
            this.physicalExamDao = physicalExamDao;
        }

        @Override
        protected Void doInBackground(PhysicalExam... physicalExams) {
            physicalExamDao.delete(physicalExams[0]);
            return null;
        }
    }



}
