package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.Surgery;
import com.example.patienthistory.room.viewmodels.SurgeryViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddSurgeryFragment extends Fragment {

    private EditText et_patient_surgery_name;
    private Button btn_patient_surgeries_done;

    private SurgeryViewModel surgeryViewModel;

    String surgeryName;


    public AddSurgeryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_surgery, container, false);

        et_patient_surgery_name = view.findViewById(R.id.et_patient_surgery_name);
        btn_patient_surgeries_done = view.findViewById(R.id.btn_patient_surgeries_done);

        surgeryViewModel = ViewModelProviders.of(this).get(SurgeryViewModel.class);

        btn_patient_surgeries_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                if (surgeryName.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a surgery name.", Toast.LENGTH_SHORT).show();
                }
                else {
                    surgeryViewModel.insert(new Surgery(1, surgeryName));
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

    private void extractData(){
        surgeryName = et_patient_surgery_name.getText().toString().trim();
    }

}
