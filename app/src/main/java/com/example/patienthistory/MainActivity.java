package com.example.patienthistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.patienthistory.register_user.SignUpInfoActivity;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity is the welcome screen.
 * It holds the base ipv4 address for the localhost
 */
public class MainActivity extends AppCompatActivity {

    public static String url = "http://192.168.43.237:8000/";

    private Button btn_signIn;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_signIn = findViewById(R.id.btn_sign_in);
        btn_register = findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpInfoActivity.class);
                startActivity(intent);
            }
        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TempActivity.class);
                startActivity(intent);
            }
        });

    }
}
