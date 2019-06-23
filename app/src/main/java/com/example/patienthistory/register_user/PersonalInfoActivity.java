package com.example.patienthistory.register_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patienthistory.R;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity takes personal information about the user
 */
public class PersonalInfoActivity extends AppCompatActivity {

    public static String SHARED_PREFS = SignUpInfoActivity.SHARED_PREFS;
    public static String COUNTRY = "country";
    public static String HEIGHT = "height";
    public static String CITY = "city";
    public static String JOB = "job";

    private TextView tv_log_in;
    private EditText et_country;
    private EditText et_height;
    private EditText et_city;
    private EditText et_job;
    private ImageView iv_right_arrow_personal;
    private ImageView iv_left_arrow_personal;

    private String country;
    private String height;
    private String city;
    private String job;

    private boolean countryFilled;
    private boolean heightFilled;
    private boolean jobFilled;
    private boolean cityFilled;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        preferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = preferences.edit();

        tv_log_in = findViewById(R.id.log_in_personal);
        et_country = findViewById(R.id.et_country);
        et_height = findViewById(R.id.et_height);
        et_city = findViewById(R.id.et_city);
        et_job = findViewById(R.id.et_job);
        iv_left_arrow_personal = findViewById(R.id.iv_left_arrow_personal_info);
        iv_right_arrow_personal = findViewById(R.id.iv_right_arrow_personal_info);

        iv_left_arrow_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_right_arrow_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractCountry();
                extractCity();
                extractHeight();
                extractJob();
                editor.apply();
                if (countryFilled && heightFilled && jobFilled && cityFilled){
                    Intent intent = new Intent(PersonalInfoActivity.this, PickImageAndConfirmActivity.class);
                    editor.apply();
                    startActivity(intent);
                }

            }
        });


    }

    private void extractCountry(){
        country = et_country.getText().toString().trim();
        if (country.isEmpty()){
            Toast.makeText(this, "Please enter a country.", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(COUNTRY, country);
            countryFilled = true;
        }
    }

    private void extractHeight(){
        height = et_height.getText().toString().trim();
        if (height.length() <= 3){
            int intHeight = Integer.parseInt(height);
            if (intHeight > 55 && intHeight < 255){
                editor.putInt(HEIGHT, intHeight);
                heightFilled = true;
            }
            else {
                Toast.makeText(this, "Please enter a valid height.", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(this, "Please enter a valid height.", Toast.LENGTH_SHORT).show();
        }
    }

    private void extractCity(){
        city = et_city.getText().toString().trim();
        if (city.isEmpty()){
            Toast.makeText(this, "Please enter a city name.", Toast.LENGTH_SHORT).show();
        }
        else{
            editor.putString(CITY, city);
            cityFilled = true;
        }

    }

    private void extractJob(){
        job = et_job.getText().toString().trim();
        if (job.isEmpty()){
            Toast.makeText(this, "Please enter your job (or unemplyed).", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(JOB, job);
            jobFilled = true;
        }

    }

    @Override
    public void finish() {
        super.finish();
    }
}
