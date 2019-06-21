package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.patienthistory.R;
import com.example.patienthistory.room.entities.Allergies;
import com.example.patienthistory.room.entities.DietaryInformation;
import com.example.patienthistory.room.entities.FamilyDiseases;
import com.example.patienthistory.room.entities.Patient;
import com.example.patienthistory.room.entities.PhysicalExam;
import com.example.patienthistory.room.entities.Remedies;
import com.example.patienthistory.room.entities.SocialHabit;
import com.example.patienthistory.room.entities.Surgery;
import com.example.patienthistory.room.viewmodels.AllergiesViewModel;
import com.example.patienthistory.room.viewmodels.DietaryInformationViewModel;
import com.example.patienthistory.room.viewmodels.FamilyDiseasesViewModel;
import com.example.patienthistory.room.viewmodels.PatientViewModel;
import com.example.patienthistory.room.viewmodels.PhysicalExamViewModel;
import com.example.patienthistory.room.viewmodels.RemediesViewModel;
import com.example.patienthistory.room.viewmodels.SocialHabitViewModel;
import com.example.patienthistory.room.viewmodels.SurgeryViewModel;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAllPatientDataFragment extends Fragment {

    private TextView tv_patient_data_view;
    private TextView tv_patient_family_diseases_view;
    private TextView tv_patient_allergies_view;
    private TextView tv_patient_dietary_information_view;
    private TextView tv_patient_remedies_view;
    private TextView tv_patient_social_habit_view;
    private TextView tv_patient_physical_exam_view;
    private TextView tv_patient_surgeries_view;

    private PatientViewModel patientViewModel;
    private FamilyDiseasesViewModel familyDiseasesViewModel;
    private AllergiesViewModel allergiesViewModel;
    private DietaryInformationViewModel dietaryInformationViewModel;
    private RemediesViewModel remediesViewModel;
    private SocialHabitViewModel socialHabitViewModel;
    private PhysicalExamViewModel physicalExamViewModel;
    private SurgeryViewModel surgeryViewModel;


    public ViewAllPatientDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_all_patient_data, container, false);

        tv_patient_data_view = view.findViewById(R.id.tv_patient_data_view);
        tv_patient_family_diseases_view = view.findViewById(R.id.tv_patient_family_diseases_view);
        tv_patient_allergies_view = view.findViewById(R.id.tv_patient_allergies_view);
        tv_patient_dietary_information_view = view.findViewById(R.id.tv_patient_dietary_information_view);
        tv_patient_remedies_view = view.findViewById(R.id.tv_patient_remedies_view);
        tv_patient_social_habit_view = view.findViewById(R.id.tv_patient_social_habit_view);
        tv_patient_physical_exam_view = view.findViewById(R.id.tv_patient_physical_exam_view);
        tv_patient_surgeries_view = view.findViewById(R.id.tv_patient_surgeries_view);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        familyDiseasesViewModel = ViewModelProviders.of(this).get(FamilyDiseasesViewModel.class);
        allergiesViewModel = ViewModelProviders.of(this).get(AllergiesViewModel.class);
        dietaryInformationViewModel = ViewModelProviders.of(this).get(DietaryInformationViewModel.class);
        remediesViewModel = ViewModelProviders.of(this).get(RemediesViewModel.class);
        socialHabitViewModel = ViewModelProviders.of(this).get(SocialHabitViewModel.class);
        physicalExamViewModel = ViewModelProviders.of(this).get(PhysicalExamViewModel.class);
        surgeryViewModel = ViewModelProviders.of(this).get(SurgeryViewModel.class);



        //TODO parse the data
        patientViewModel.getData().observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {

            }
        });

        familyDiseasesViewModel.getAllFamilyDiseases().observe(this, new Observer<List<FamilyDiseases>>() {
            @Override
            public void onChanged(List<FamilyDiseases> familyDiseases) {

            }
        });

        allergiesViewModel.getAllAllergies().observe(this, new Observer<List<Allergies>>() {
            @Override
            public void onChanged(List<Allergies> allergies) {

            }
        });

        dietaryInformationViewModel.getDietaryInformationList().observe(this, new Observer<List<DietaryInformation>>() {
            @Override
            public void onChanged(List<DietaryInformation> dietaryInformations) {

            }
        });

        remediesViewModel.getAllRemedies().observe(this, new Observer<List<Remedies>>() {
            @Override
            public void onChanged(List<Remedies> remedies) {

            }
        });

        socialHabitViewModel.getSocialHabitLiveData().observe(this, new Observer<SocialHabit>() {
            @Override
            public void onChanged(SocialHabit socialHabit) {

            }
        });

        physicalExamViewModel.getPhysicalExamLiveData().observe(this, new Observer<PhysicalExam>() {
            @Override
            public void onChanged(PhysicalExam physicalExam) {

            }
        });

        surgeryViewModel.getAllSurgeries().observe(this, new Observer<List<Surgery>>() {
            @Override
            public void onChanged(List<Surgery> surgeries) {

            }
        });


        return inflater.inflate(R.layout.fragment_view_all_patient_data, container, false);
    }

}
