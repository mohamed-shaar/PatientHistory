package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.FamilyDiseases;
import com.example.patienthistory.room.viewmodels.FamilyDiseasesViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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

        familyDiseasesViewModel = ViewModelProviders.of(this).get(FamilyDiseasesViewModel.class);

        et_patient_disease_name = view.findViewById(R.id.et_patient_disease_name);
        et_patient_relative = view.findViewById(R.id.et_patient_relative);
        btn_patient_family_disease_done = view.findViewById(R.id.btn_patient_family_diseases_done);

        btn_patient_family_disease_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                familyDiseasesViewModel.insert(new FamilyDiseases(disease_name, patient_relative,1));
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void holdId(int id){idHolder = id;}

    private void extractData(){
        disease_name = et_patient_disease_name.getText().toString().trim();
        patient_relative = et_patient_relative.getText().toString().trim();
    }

}
