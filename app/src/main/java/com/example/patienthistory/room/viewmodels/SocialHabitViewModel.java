package com.example.patienthistory.room.viewmodels;

import android.app.Application;

import com.example.patienthistory.room.entities.SocialHabit;
import com.example.patienthistory.room.repositories.SocialHabitRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SocialHabitViewModel extends AndroidViewModel {

    private SocialHabitRepository socialHabitRepository;
    private LiveData<SocialHabit> socialHabitLiveData;

    public SocialHabitViewModel(@NonNull Application application) {
        super(application);
        socialHabitRepository = new SocialHabitRepository(application);
        socialHabitLiveData = socialHabitRepository.getSocialHabitLiveData();
    }

    public void insert(SocialHabit socialHabit){
        socialHabitRepository.insert(socialHabit);
    }

    public void update(SocialHabit socialHabit){
        socialHabitRepository.update(socialHabit);
    }

    public void delete(SocialHabit socialHabit){
        socialHabitRepository.delete(socialHabit);
    }

    public LiveData<SocialHabit> getSocialHabitLiveData() {return socialHabitRepository.getSocialHabitLiveData();}
}
