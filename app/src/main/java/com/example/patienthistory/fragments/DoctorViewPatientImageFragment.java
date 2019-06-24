package com.example.patienthistory.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.patienthistory.R;
import com.example.patienthistory.register_user.SignUpInfoActivity;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static com.example.patienthistory.fragments.DoctorViewPatientData.LINK;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorViewPatientImageFragment extends Fragment {

    private ImageView iv_doctor_view_patient_image;
    private SharedPreferences sharedPreferences;


    public DoctorViewPatientImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_view_patient_image, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);

        view.findViewById(R.id.iv_doctor_view_patient_image);

        String link = sharedPreferences.getString(LINK, "");
        Log.d("link for image: ", link);

        //Glide.with(this.getActivity()).load(link).into(iv_doctor_view_patient_image);

        return view;
    }

}
