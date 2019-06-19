package com.example.patienthistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.patienthistory.user_activities.AssistantActivity;
import com.example.patienthistory.user_activities.DoctorActivity;
import com.example.patienthistory.user_activities.PatientActivity;

import androidx.appcompat.app.AppCompatActivity;

public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        Button btn_patient_view = findViewById(R.id.btn_patient_view);
        Button btn_doctor_view = findViewById(R.id.btn_doctor_view);
        Button btn_assistant_view = findViewById(R.id.btn_assistant_view);

        btn_patient_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempActivity.this, PatientActivity.class);
                startActivity(intent);
            }
        });

        btn_doctor_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempActivity.this, DoctorActivity.class);
                startActivity(intent);
            }
        });

        btn_assistant_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempActivity.this, AssistantActivity.class);
                startActivity(intent);
            }
        });
    }
}
