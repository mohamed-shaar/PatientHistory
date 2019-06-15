package com.example.patienthistory.room.databases;

import android.content.Context;
import android.os.AsyncTask;

import com.example.patienthistory.room.dao.PatientDao;
import com.example.patienthistory.room.entities.Patient;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Patient.class, version = 1, exportSchema = false)
public abstract  class PatientDatabase extends RoomDatabase {

    private static PatientDatabase instance;

    public abstract PatientDao patientDao();

    public static synchronized PatientDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PatientDatabase.class,
                    "patient_database")
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
            /*PatientWithData patientWithData = null;
            patientWithData.patient.setId(1);
            patientWithData.patient.setAddress("64 el dowal");
            patientWithData.patient.setBloodType("A+");
            patientWithData.patient.setNumberOfChildren(0);
            patientWithData.patient.setSocialStatus("Single");

            //patientWithData.socialHabit.setPatientId(1);
            patientWithData.socialHabit.setAlcohol(true);
            patientWithData.socialHabit.setDrugs(false);
            patientWithData.socialHabit.setTobacco(true);

            //patientWithData.physicalExam.setPatientId(1);
            patientWithData.physicalExam.setBloodPressure("30/30");
            patientWithData.physicalExam.setHeartRate("30/30");

            Allergies allergies = null;
            allergies.setPatientId(1);
            allergies.setAllergyName("Lactose Intolerant");

            DietaryInformation dietaryInformation = null;
            dietaryInformation.setPatientId(1);
            dietaryInformation.setRestrictions("None");
            dietaryInformation.setSupplements("None");
            dietaryInformation.setStimulants("None");

            FamilyDiseases familyDiseases = null;
            //familyDiseases.setPatientId(1);
            familyDiseases.setDiseaseName("Cancer");
            familyDiseases.setRelation("Grandparent");

            Remedies remedies = null;
            remedies.setPatientId(1);
            remedies.setName("Herbs");
            remedies.setDose(20);
            remedies.setStartDate("14/6/2019");
            remedies.setOutcome("Fixed the coughing");

            Surgery surgery = null;
            surgery.setPatientId(1);
            surgery.setName("open heart");

            patientWithData.allergies.add(allergies);
            patientWithData.dietaryInformation.add(dietaryInformation);
            patientWithData.familyDiseases.add(familyDiseases);
            patientWithData.remedies.add(remedies);
            patientWithData.surgeries.add(surgery);

            patientDao.Insert(patientWithData);*/
            return null;
        }
    }
}
