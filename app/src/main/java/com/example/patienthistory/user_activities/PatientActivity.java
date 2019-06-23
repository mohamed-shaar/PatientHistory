package com.example.patienthistory.user_activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.patienthistory.R;
import com.example.patienthistory.fragments.ChatFragment;
import com.example.patienthistory.fragments.DataFragment;
import com.example.patienthistory.fragments.FindFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * This is the view for the patient. The activity find the bottom navigation view for the clinic and sets the menu items to it
 */
public class PatientActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        bottomNavigationView = findViewById(R.id.patient_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.patient_frame_layout, new DataFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.patient_data:
                            fragment = new DataFragment();
                            break;
                        case R.id.patient_find:
                            fragment = new FindFragment();
                            break;
                        case R.id.patient_chat:
                            fragment = new ChatFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.patient_frame_layout, fragment).commit();
                    return true;
                }
            };
}
