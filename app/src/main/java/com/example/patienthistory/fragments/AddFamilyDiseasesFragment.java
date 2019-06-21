package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.patienthistory.R;
import com.example.patienthistory.room.viewmodels.FamilyDiseasesViewModel;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFamilyDiseasesFragment extends Fragment {

    private EditText et_patient_disease_name;
    private EditText et_patient_relative;
    private Button btn_patient_family_disease_done;

    private FamilyDiseasesViewModel familyDiseasesViewModel;

    private String disease_name;
    private String patient_relative;

    private int idHolder;


    public AddFamilyDiseasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_family_diseases, container, false);

        et_patient_disease_name = view.findViewById(R.id.et_patient_disease_name);
        et_patient_relative = view.findViewById(R.id.et_patient_relative);
        btn_patient_family_disease_done = view.findViewById(R.id.btn_patient_family_diseases_done);



        return view;
    }

}
