package com.example.patienthistory;

import android.content.Context;
import android.os.AsyncTask;

import com.example.patienthistory.dao.PatientDao;
import com.example.patienthistory.entity.PatientWithData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = PatientWithData.class, version = 1)
public abstract  class PatientDatabase extends RoomDatabase {

    private static PatientDatabase instance;

    public abstract PatientDao patientDao();

    public static synchronized PatientDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PatientDatabase.class,
                    "note_database")
                    .fallbackToDestructiveMigration() //when updating the database version this will cause all the information to be deleted
                    .addCallback(roomCallback) //called to populate the database when it is first created
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PatientDao patientDao;

        private PopulateDbAsyncTask(PatientDatabase db){
            patientDao = db.patientDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /*noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 2));
            noteDao.insert(new Note("Title 3", "Description 3", 3));*/

            //Todo add database info for first creation
            return null;
        }
    }
}
