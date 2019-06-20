package com.example.patienthistory.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.Patient;
import com.example.patienthistory.room.viewmodels.PatientViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DataFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data, container, false);
        final TextView textView = view.findViewById(R.id.tv_patient_data);

        PatientViewModel patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.getData().observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                textView.append(String.valueOf(patient.getId()) + "\n");
                textView.append(String.valueOf(patient.getNumberOfChildren()) + "\n");
                textView.append(patient.getAddress() + "\n");
                textView.append(patient.getBloodType());
            }
        });

        return view;
    }
}
