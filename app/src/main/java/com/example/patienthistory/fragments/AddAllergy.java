package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.Allergies;
import com.example.patienthistory.room.viewmodels.AllergiesViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAllergy extends Fragment {

    private EditText et_patient_allergies;
    private Button btn_patient_allergies_done;

    private AllergiesViewModel allergiesViewModel;

    private String allergy;


    public AddAllergy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_allergy, container, false);

        allergiesViewModel = ViewModelProviders.of(this).get(AllergiesViewModel.class);

        et_patient_allergies = view.findViewById(R.id.et_patient_allergies);
        btn_patient_allergies_done = view.findViewById(R.id.btn_patient_allergies_done);

        btn_patient_allergies_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                allergiesViewModel.insert(new Allergies(1, allergy));
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void extractData(){
        allergy = et_patient_allergies.getText().toString().trim();
    }

}
