package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.Remedies;
import com.example.patienthistory.room.viewmodels.RemediesViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRemediesFragment extends Fragment {

    private EditText et_patient_remedies_name;
    private EditText et_patient_remedies_start_Date;
    private EditText et_patient_remedies_dose;
    private EditText et_patient_remedies_outcome;

    private Button btn_patient_remedies_done;

    private RemediesViewModel remediesViewModel;

    String name;
    String date;
    int dose;
    String outcome;


    public AddRemediesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_remedies, container, false);

        et_patient_remedies_name = view.findViewById(R.id.et_patient_remedies_name);
        et_patient_remedies_start_Date = view.findViewById(R.id.et_patient_remedies_start_Date);
        et_patient_remedies_dose = view.findViewById(R.id.et_patient_remedies_dose);
        et_patient_remedies_outcome = view.findViewById(R.id.et_patient_remedies_outcome);
        btn_patient_remedies_done = view.findViewById(R.id.btn_patient_remedies_done);

        remediesViewModel = ViewModelProviders.of(this).get(RemediesViewModel.class);

        btn_patient_remedies_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                if (name.isEmpty() || date.isEmpty() || outcome.isEmpty()){
                    Toast.makeText(getContext(), "Please enter fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    remediesViewModel.insert(new Remedies(1, name, date, dose, outcome));
                    getActivity().getSupportFragmentManager().popBackStack();
                }

            }
        });
        return view;
    }

    private void extractData(){
        name = et_patient_remedies_name.getText().toString().trim();
        date = et_patient_remedies_start_Date.getText().toString().trim();
        dose = Integer.parseInt(et_patient_remedies_dose.getText().toString());
        outcome = et_patient_remedies_outcome.getText().toString().trim();
    }

}
