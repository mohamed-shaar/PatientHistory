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

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        familyDiseasesViewModel = ViewModelProviders.of(this).get(FamilyDiseasesViewModel.class);
        allergiesViewModel = ViewModelProviders.of(this).get(AllergiesViewModel.class);
        dietaryInformationViewModel = ViewModelProviders.of(this).get(DietaryInformationViewModel.class);
        remediesViewModel = ViewModelProviders.of(this).get(RemediesViewModel.class);
        socialHabitViewModel = ViewModelProviders.of(this).get(SocialHabitViewModel.class);
        physicalExamViewModel = ViewModelProviders.of(this).get(PhysicalExamViewModel.class);
        surgeryViewModel = ViewModelProviders.of(this).get(SurgeryViewModel.class);


        tv_patient_data_view = view.findViewById(R.id.tv_patient_data_view);
        tv_patient_family_diseases_view = view.findViewById(R.id.tv_patient_family_diseases_view);
        tv_patient_allergies_view = view.findViewById(R.id.tv_patient_allergies_view);
        tv_patient_dietary_information_view = view.findViewById(R.id.tv_patient_dietary_information_view);
        tv_patient_remedies_view = view.findViewById(R.id.tv_patient_remedies_view);
        tv_patient_social_habit_view = view.findViewById(R.id.tv_patient_social_habit_view);
        tv_patient_physical_exam_view = view.findViewById(R.id.tv_patient_physical_exam_view);
        tv_patient_surgeries_view = view.findViewById(R.id.tv_patient_surgeries_view);

        patientViewModel.getData().observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                tv_patient_data_view.setText("\n");
                tv_patient_data_view.append(patient.getAddress() + "\n");
                tv_patient_data_view.append(patient.getBloodType() + "\n");
                tv_patient_data_view.append(patient.getSocialStatus());

            }
        });

        familyDiseasesViewModel.getAllFamilyDiseases().observe(this, new Observer<List<FamilyDiseases>>() {
            @Override
            public void onChanged(List<FamilyDiseases> familyDiseases) {
                for (FamilyDiseases current : familyDiseases) {
                    if (current != null) {
                        tv_patient_family_diseases_view.append("\n");
                        tv_patient_family_diseases_view.append(current.getRelation() + " - " + current.getDiseaseName());
                    }
                }
            }
        });

        allergiesViewModel.getAllAllergies().observe(this, new Observer<List<Allergies>>() {
            @Override
            public void onChanged(List<Allergies> allergies) {
                for (Allergies current : allergies) {
                    if (current != null) {
                        tv_patient_allergies_view.append("\n");
                        tv_patient_allergies_view.append(current.getAllergyName());
                    }
                }
            }
        });

        dietaryInformationViewModel.getDietaryInformationList().observe(this, new Observer<List<DietaryInformation>>() {
            @Override
            public void onChanged(List<DietaryInformation> dietaryInformations) {
                for (DietaryInformation current : dietaryInformations) {
                    if (current != null) {
                        tv_patient_dietary_information_view.append("\n");
                        tv_patient_dietary_information_view.append(current.getRestrictions() + "\n");
                        tv_patient_dietary_information_view.append(current.getStimulants() + "\n");
                        tv_patient_dietary_information_view.append(current.getSupplements() + "\n");
                    }
                }
            }
        });

        remediesViewModel.getAllRemedies().observe(this, new Observer<List<Remedies>>() {
            @Override
            public void onChanged(List<Remedies> remedies) {
                for (Remedies current : remedies) {
                    if (current != null) {
                        tv_patient_remedies_view.append("\n");
                        tv_patient_remedies_view.append(current.getName() + "\n");
                        tv_patient_remedies_view.append(current.getStartDate() + "\n");
                        tv_patient_remedies_view.append(String.valueOf(current.getDose()) + "\n");
                        tv_patient_remedies_view.append(current.getOutcome() + "\n");
                    }
                }
            }
        });

        socialHabitViewModel.getSocialHabitLiveData().observe(this, new Observer<SocialHabit>() {
            @Override
            public void onChanged(SocialHabit socialHabit) {
                if (socialHabit != null) {
                    tv_patient_social_habit_view.append("\n");
                    tv_patient_social_habit_view.append(String.valueOf(socialHabit.isAlcohol()) + "\n");
                    tv_patient_social_habit_view.append(String.valueOf(socialHabit.isDrugs()) + "\n");
                    tv_patient_social_habit_view.append(String.valueOf(socialHabit.isTobacco()));
                }
            }
        });

        physicalExamViewModel.getPhysicalExamLiveData().observe(this, new Observer<PhysicalExam>() {
            @Override
            public void onChanged(PhysicalExam physicalExam) {
                if (physicalExam != null) {
                    tv_patient_physical_exam_view.append("\n");
                    tv_patient_physical_exam_view.append(physicalExam.getBloodPressure() + "\n");
                    tv_patient_physical_exam_view.append(physicalExam.getHeartRate());
                }
            }
        });

        surgeryViewModel.getAllSurgeries().observe(this, new Observer<List<Surgery>>() {
            @Override
            public void onChanged(List<Surgery> surgeries) {
                for (Surgery current : surgeries) {
                    if (current != null) {
                        tv_patient_surgeries_view.append("\n");
                        tv_patient_surgeries_view.append(current.getName());
                    }
                }
            }
        });


        return view;
    }

}
