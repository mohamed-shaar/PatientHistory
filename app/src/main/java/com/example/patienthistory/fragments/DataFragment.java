package com.example.patienthistory.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.patienthistory.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * This fragment shows the patient all types of data that they can enter and allows them to view it
 */
public class DataFragment extends Fragment {

    private Button btn_patient_data;
    private Button btn_patient_family_diseases;
    private Button btn_patient_allergies;
    private Button btn_patient_dietary_information;
    private Button btn_patient_remedies;
    private Button btn_patient_social_habit;
    private Button btn_patient_physical_exam;
    private Button btn_patient_surgeries;
    private Button btn_patient_view_all_data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_data, container, false);
        btn_patient_data = view.findViewById(R.id.btn_patient_data);
        btn_patient_family_diseases = view.findViewById(R.id.btn_patient_family_diseases);
        btn_patient_allergies = view.findViewById(R.id.btn_patient_allergies);
        btn_patient_dietary_information = view.findViewById(R.id.btn_patient_dietary_information);
        btn_patient_remedies = view.findViewById(R.id.btn_patient_remedies);
        btn_patient_social_habit = view.findViewById(R.id.btn_patient_social_habit);
        btn_patient_physical_exam = view.findViewById(R.id.btn_patient_physical_exam);
        btn_patient_surgeries = view.findViewById(R.id.btn_patient_surgeries);
        btn_patient_view_all_data = view.findViewById(R.id.btn_patient_view_all_data);

        btn_patient_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDataFragment fragment = new AddDataFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_add_data, new AddDataFragment(), null).commit();
            }
        });

        btn_patient_family_diseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFamilyDiseasesFragment fragment = new AddFamilyDiseasesFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_patient_dietary_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDietaryInformationFragment fragment = new AddDietaryInformationFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_patient_allergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAllergy fragment = new AddAllergy();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_patient_remedies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRemediesFragment fragment  = new AddRemediesFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_patient_social_habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSocialHabitFragment fragment = new AddSocialHabitFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_patient_physical_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPhysicalExamFragment fragment = new AddPhysicalExamFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_patient_surgeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddSurgeryFragment fragment = new AddSurgeryFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_patient_view_all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewAllPatientDataFragment fragment = new ViewAllPatientDataFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.patient_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
