package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.DietaryInformation;
import com.example.patienthistory.room.viewmodels.DietaryInformationViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddDietaryInformationFragment extends Fragment {

    private EditText et_patient_restrictions;
    private EditText et_patient_supplements;
    private EditText et_patient_stimulants;
    private Button btn_patient_dietary_information_done;

    private DietaryInformationViewModel dietaryInformationViewModel;

    private String restrictions;
    private String supplements;
    private String stimulants;


    public AddDietaryInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_dietary_information, container, false);

        dietaryInformationViewModel = ViewModelProviders.of(this).get(DietaryInformationViewModel.class);

        et_patient_restrictions = view.findViewById(R.id.et_patient_restrictions);
        et_patient_supplements = view.findViewById(R.id.et_patient_supplements);
        et_patient_stimulants = view.findViewById(R.id.et_patient_stimulants);
        btn_patient_dietary_information_done = view.findViewById(R.id.btn_patient_dietary_information_done);

        btn_patient_dietary_information_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                dietaryInformationViewModel.insert(new DietaryInformation(1, restrictions, supplements, stimulants));
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void extractData(){
        restrictions = et_patient_restrictions.getText().toString().trim();
        supplements = et_patient_supplements.getText().toString().trim();
        stimulants = et_patient_stimulants.getText().toString().trim();
    }

}
