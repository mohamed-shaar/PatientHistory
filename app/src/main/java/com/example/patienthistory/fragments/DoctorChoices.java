package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.patienthistory.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorChoices extends Fragment {

    private Button btn_doctor_add_prescription;
    private Button btn_doctor_add_session;
    private Button btn_doctor_view_patient_data;
    private Button btn_doctor_add_data;

    public DoctorChoices() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_choices, container, false);

        btn_doctor_add_prescription = view.findViewById(R.id.btn_doctor_add_prescription);
        btn_doctor_add_session = view.findViewById(R.id.btn_doctor_add_session);
        btn_doctor_view_patient_data = view.findViewById(R.id.btn_doctor_view_patient_data);
        btn_doctor_add_data = view.findViewById(R.id.btn_doctor_add_data);


        btn_doctor_add_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPatientMedicineOrDiseaseFragment fragment = new AddPatientMedicineOrDiseaseFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.doctor_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_doctor_add_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionFragment fragment = new SessionFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.doctor_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_doctor_view_patient_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoctorViewPatientData fragment = new DoctorViewPatientData();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.doctor_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_doctor_add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

}
