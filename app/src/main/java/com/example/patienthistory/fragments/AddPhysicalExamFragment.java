package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.PhysicalExam;
import com.example.patienthistory.room.viewmodels.PhysicalExamViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPhysicalExamFragment extends Fragment {

    private EditText et_patient_blood_pressure;
    private EditText et_patient_heart_rate;
    private Button btn_patient_physical_exam_done;

    private PhysicalExamViewModel physicalExamViewModel;

    String blood_pressure;
    String heart_rate;

    int idHolder = 0;


    public AddPhysicalExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_physical_exam, container, false);

        et_patient_blood_pressure = view.findViewById(R.id.et_patient_blood_pressure);
        et_patient_heart_rate = view.findViewById(R.id.et_patient_heart_rate);
        btn_patient_physical_exam_done = view.findViewById(R.id.btn_patient_physical_exam_done);

        physicalExamViewModel = ViewModelProviders.of(this).get(PhysicalExamViewModel.class);

        physicalExamViewModel.getPhysicalExamLiveData().observe(this, new Observer<PhysicalExam>() {
            @Override
            public void onChanged(PhysicalExam physicalExam) {
                if (physicalExam != null){
                    et_patient_blood_pressure.setText(physicalExam.getBloodPressure());
                    et_patient_heart_rate.setText(physicalExam.getHeartRate());
                    setIdHolder(physicalExam.getId());
                }
            }
        });

        btn_patient_physical_exam_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                if (blood_pressure.isEmpty() || heart_rate.isEmpty()){
                    Toast.makeText(getContext(), "Please Enter the Fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (idHolder == 0){
                        physicalExamViewModel.insert(new PhysicalExam(1, blood_pressure, heart_rate));
                    }
                    else {
                        PhysicalExam physicalExam = new PhysicalExam(1, blood_pressure, heart_rate);
                        physicalExam.setId(idHolder);
                        physicalExamViewModel.update(physicalExam);
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                }
            }
        });

        return view;
    }

    private void extractData(){
        blood_pressure = et_patient_blood_pressure.getText().toString().trim();
        heart_rate = et_patient_heart_rate.getText().toString().trim();
    }

    private void setIdHolder(int id){ idHolder = id;}

}
