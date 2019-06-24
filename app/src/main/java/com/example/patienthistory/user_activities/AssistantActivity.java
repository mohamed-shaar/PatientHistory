package com.example.patienthistory.user_activities;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.patienthistory.R;
import com.example.patienthistory.fragments.BookingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/**
 * This is the view for the clinic. The activity find the bottom navigation view for the clinic and sets the menu items to it
 */
public class AssistantActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant);

        bottomNavigationView = findViewById(R.id.assistant_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.assistant_frame_layout, new BookingFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;
                    switch (item.getItemId()){
                        case R.id.assistant_bookings:
                            fragment = new BookingFragment();
                            break;
                        }
                    getSupportFragmentManager().beginTransaction().replace(R.id.assistant_frame_layout, fragment).commit();
                    return true;
                }
            };
}
