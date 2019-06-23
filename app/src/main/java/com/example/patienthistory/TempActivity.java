package com.example.patienthistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity allows the user to choose their type (patient, doctor, clinic)
 */
public class TempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        Button btn_patient_view = findViewById(R.id.btn_patient_view);
        Button btn_doctor_view = findViewById(R.id.btn_doctor_view);
        Button btn_assistant_view = findViewById(R.id.btn_assistant_view);

        //The user makes their choice and are taken to the sign in activity

        btn_patient_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempActivity.this, SignInActivity.class);
                intent.putExtra("Type", "patient");
                startActivity(intent);
            }
        });

        btn_doctor_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempActivity.this, SignInActivity.class);
                intent.putExtra("Type", "doctor");
                startActivity(intent);
            }
        });

        btn_assistant_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TempActivity.this, SignInActivity.class);
                intent.putExtra("Type", "clinic");
                startActivity(intent);
            }
        });
    }
}
