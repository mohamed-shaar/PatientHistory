package com.example.patienthistory.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.Patient;
import com.example.patienthistory.room.viewmodels.PatientViewModel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class AddDataFragment extends Fragment {

    private EditText et_patient_address;
    private EditText et_patient_blood_type;
    private EditText et_patient_social_status;
    private EditText et_patient_number_of_children;
    private Button btn_patient_data_done;

    private PatientViewModel patientViewModel;

    private String address;
    private String blood_type;
    private String social_status;
    private String number_of_children;

    private int idHolder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_data, container, false);

        et_patient_address = view.findViewById(R.id.et_patient_address);
        et_patient_blood_type = view.findViewById(R.id.et_patient_blood_type);
        et_patient_social_status = view.findViewById(R.id.et_patient_social_status);
        et_patient_number_of_children = view.findViewById(R.id.et_patient_number_of_children);
        btn_patient_data_done = view.findViewById(R.id.btn_patient_data_done);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.getData().observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                if (patient != null){
                    et_patient_address.setText(patient.getAddress());
                    et_patient_blood_type.setText(patient.getBloodType());
                    et_patient_social_status.setText(patient.getSocialStatus());
                    et_patient_number_of_children.setText(String.valueOf(patient.getNumberOfChildren()));
                    holdId(patient.getId());
                }
            }
        });

        btn_patient_data_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                Patient patient = new Patient(address, blood_type, social_status, Integer.parseInt(number_of_children));
                patient.setId(idHolder);
                patientViewModel.update(patient);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void extractData(){
        address = et_patient_address.getText().toString().trim();
        blood_type = et_patient_blood_type.getText().toString();
        social_status = et_patient_social_status.getText().toString();
        number_of_children = et_patient_number_of_children.getText().toString();
    }

    private void holdId(int id){ idHolder = id;}


}
