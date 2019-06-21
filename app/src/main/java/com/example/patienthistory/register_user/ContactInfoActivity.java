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

public class ContactInfoActivity extends AppCompatActivity {

    public static String SHARED_PREFS = SignUpInfoActivity.SHARED_PREFS;
    public static String PHONE_NUMBER = "phone_number";
    public static String FULL_NAME = "full_name";
    public static String AGE = "age";
    public static String GENDER = "gender";

    private TextView tv_log_in;
    private EditText et_phone_number;
    private EditText et_full_name;
    private EditText et_age;
    private EditText et_gender;
    private ImageView iv_right_arrow;
    private ImageView iv_left_arrow;

    private String phone_number;
    private String full_name;
    private int age;
    private String gender;

    private boolean phoneNumberFilled = false;
    private boolean fullNameFilled = false;
    private boolean ageFilled = false;
    private boolean genderFilled = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tv_log_in = findViewById(R.id.log_in_personal);
        et_phone_number = findViewById(R.id.et_phone_number);
        et_full_name = findViewById(R.id.et_full_name);
        et_age = findViewById(R.id.et_age);
        et_gender = findViewById(R.id.et_gender);
        iv_right_arrow = findViewById(R.id.iv_right_arrow_contact_info);
        iv_left_arrow = findViewById(R.id.iv_left_arrow_contact_info);

        iv_left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_right_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractPhoneNumber();
                extractAge();
                extractFullName();
                extractGender();
                if (phoneNumberFilled && fullNameFilled && genderFilled && ageFilled){
                    editor.apply();
                    Intent intent = new Intent(ContactInfoActivity.this, PersonalInfoActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void extractPhoneNumber(){
        phone_number = et_phone_number.getText().toString().trim();
        if (phone_number.isEmpty()){
            //int phone_number = Integer.parseInt(et_phone_number.getText().toString());
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(PHONE_NUMBER, phone_number);
            phoneNumberFilled = true;
        }
    }

    private void extractFullName(){
        full_name = et_full_name.getText().toString().trim();
        if (full_name.isEmpty()){
            Toast.makeText(this, "Please enter a name.", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(FULL_NAME, full_name);
            fullNameFilled = true;
        }
    }

    private void extractAge(){
        age = Integer.parseInt(et_age.getText().toString().trim());
        if (age >= 18 && age <= 120){
            editor.putString(AGE, String.valueOf(age));
            ageFilled = true;
        }
        else {
            Toast.makeText(this, "You must be between 18 and 120 years old.", Toast.LENGTH_SHORT).show();
        }
    }

    private void extractGender(){
        gender = et_gender.getText().toString().trim();
        if (gender.isEmpty()){
            Toast.makeText(this, "Please specify gender", Toast.LENGTH_SHORT).show();
        }
        else if (gender.equals("Male") ||
                gender.equals("male") ||
                gender.equals("Female") ||
                gender.equals("female")){
            editor.putString(GENDER, gender);
            genderFilled = true;
        }
        else {
            Toast.makeText(this, "Please specify a valid gender.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
