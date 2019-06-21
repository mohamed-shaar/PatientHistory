package com.example.patienthistory.register_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patienthistory.MainActivity;
import com.example.patienthistory.R;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpInfoActivity extends AppCompatActivity {

    public static String SHARED_PREFS = "sharedPref";
    public static String USERNAME = "username";
    public static String EMAIL = "email";
    public static String PASSWORD = "password";
    public static String NATIONAL_ID = "id";

    private TextView tv_logIn;
    private EditText et_username;
    private EditText et_email;
    private EditText et_password;
    private EditText et_national_id;
    private ImageView iv_right_arrow;

    private String logIn;
    private String username;
    private String email;
    private String password;
    private String nationalId;

    private boolean usernameFilled;
    private boolean emailFilled;
    private boolean passwordFilled;
    private boolean nationalIdFilled;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_info);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        tv_logIn = findViewById(R.id.log_in_sign_up);
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_national_id = findViewById(R.id.et_nationalId);
        iv_right_arrow = findViewById(R.id.iv_right_arrow_sign_up);

        iv_right_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractUsername();
                extractEmail();
                extractPassword();
                extractNationalId();
                if (usernameFilled && emailFilled && passwordFilled && nationalIdFilled){
                    editor.apply();
                    Intent intent = new Intent(SignUpInfoActivity.this, ContactInfoActivity.class);
                    startActivity(intent);
                }
            }
        });

        tv_logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void extractUsername(){
        username = et_username.getText().toString().trim();
        if (username.isEmpty()){
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(USERNAME, username);
            usernameFilled = true;
        }
    }

    private void extractEmail(){
        email = et_email.getText().toString().trim();
        if (email.isEmpty()){
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(EMAIL, email);
            emailFilled = true;
        }
    }

    private void extractPassword(){
        password = et_password.getText().toString().trim();
        //confirmPassword = et_national_id.getText().toString().trim();

        if (password.isEmpty()){
            Toast.makeText(this, "Please enter password and confirm password", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(PASSWORD, password);
            passwordFilled = true;
        }
    }

    private void extractNationalId(){
        nationalId = et_national_id.getText().toString().trim();
        if (nationalId.isEmpty()){
            Toast.makeText(this, "Please enter national Id.", Toast.LENGTH_SHORT).show();
        }
        else {
            editor.putString(NATIONAL_ID, nationalId);
            nationalIdFilled = true;
        }
    }


}
