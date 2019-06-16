package com.example.patienthistory.room.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.PatientDao;
import com.example.patienthistory.room.dao.SocialHabitDao;
import com.example.patienthistory.room.databases.PatientDatabase;
import com.example.patienthistory.room.entities.Patient;
import com.example.patienthistory.room.entities.SocialHabit;

import androidx.lifecycle.LiveData;

public class SocialHabitRepository {

    private SocialHabitDao socialHabitDao;
    private LiveData<SocialHabit> socialHabitLiveData;

    public SocialHabitRepository(Application application){
        PatientDatabase database = PatientDatabase.getInstance(application);
        socialHabitDao = database.socialHabitDao();
        socialHabitLiveData = socialHabitDao.getSocialHabit();
    }

    public void insert(SocialHabit socialHabit){ new InsertSocialHabitAsyncTask(socialHabitDao).execute(socialHabit);}

    public void update(SocialHabit socialHabit){ new UpdateSocialHabitAsyncTask(socialHabitDao).execute(socialHabit);}

    public void delete(SocialHabit socialHabit){ new DeleteSocialHabitAsyncTask(socialHabitDao).execute(socialHabit);}

    public LiveData<SocialHabit> getSocialHabitLiveData() {
        return socialHabitLiveData;
    }

    private static class InsertSocialHabitAsyncTask extends AsyncTask<SocialHabit, Void, Void> {

        private SocialHabitDao socialHabitDao;

        private InsertSocialHabitAsyncTask(SocialHabitDao socialHabitDao) {
            this.socialHabitDao = socialHabitDao;
        }

        @Override
        protected Void doInBackground(SocialHabit... socialHabits) {
            socialHabitDao.insert(socialHabits[0]);
            return null;
        }
    }

    private static class UpdateSocialHabitAsyncTask extends AsyncTask<SocialHabit, Void, Void> {

        private SocialHabitDao socialHabitDao;

        private UpdateSocialHabitAsyncTask(SocialHabitDao socialHabitDao) {
            this.socialHabitDao = socialHabitDao;
        }

        @Override
        protected Void doInBackground(SocialHabit... socialHabits) {
            socialHabitDao.update(socialHabits[0]);
            return null;
        }
    }

    private static class DeleteSocialHabitAsyncTask extends AsyncTask<SocialHabit, Void, Void> {

        private SocialHabitDao socialHabitDao;

        private DeleteSocialHabitAsyncTask(SocialHabitDao socialHabitDao) {
            this.socialHabitDao = socialHabitDao;
        }

        @Override
        protected Void doInBackground(SocialHabit... socialHabits) {
            socialHabitDao.delete(socialHabits[0]);
            return null;
        }
    }
}
