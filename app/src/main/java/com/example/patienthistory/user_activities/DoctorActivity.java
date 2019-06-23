package com.example.patienthistory.user_activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.patienthistory.R;
import com.example.patienthistory.fragments.ChatFragment;
import com.example.patienthistory.fragments.ClinicFragment;
import com.example.patienthistory.fragments.PatientsFragment;
import com.example.patienthistory.fragments.PostsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * This is the view for the doctor. The activity find the bottom navigation view for the clinic and sets the menu items to it
 */
public class DoctorActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        bottomNavigationView = findViewById(R.id.doctor_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.doctor_frame_layout, new PatientsFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.doctor_patients:
                            fragment = new PatientsFragment();
                            break;
                        case R.id.doctor_clinic:
                            fragment = new ClinicFragment();
                            break;
                        case R.id.doctor_posts:
                            fragment = new PostsFragment();
                            break;
                        case R.id.doctor_chats:
                            fragment = new ChatFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.doctor_frame_layout, fragment).commit();
                    return true;
                }
            };
}
