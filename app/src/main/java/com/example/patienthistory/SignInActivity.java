package com.example.patienthistory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.patienthistory.user_activities.AssistantActivity;
import com.example.patienthistory.user_activities.DoctorActivity;
import com.example.patienthistory.user_activities.PatientActivity;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private TextView tv_login;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final Intent intent = getIntent();
        final String type = intent.getStringExtra("Type");

        et_username = findViewById(R.id.et_username_sign_in);
        et_password = findViewById(R.id.et_password_sign_in);
        tv_login = findViewById(R.id.tv_log_in_sign_in);
        mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Enter Username and Password.", Toast.LENGTH_LONG).show();
                } else {

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username", username);
                        jsonObject.put("password", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String url = "http://192.168.88.141:3000/" + type + "/login";

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                            url,
                            jsonObject,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    int status = 0;
                                    JSONObject userObject;
                                    //TODO parse json object
                                    try {
                                        //todo status is int
                                        status = response.getInt("status");
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    if (status == 404 || status == 0) {
                                        Toast.makeText(SignInActivity.this, "Invalid.", Toast.LENGTH_LONG).show();
                                    } else{
                                        try {
                                            if (type.equals("patient")) {
                                                JSONObject parent = response.getJSONObject("user");
                                                userObject = parent.getJSONObject("user");
                                                String username = userObject.getString("username");
                                                Log.d("user", username);
                                                String email = userObject.getString("email");
                                                Log.d("user", email);
                                                String phone = userObject.getString("phone");
                                                Log.d("user", phone);
                                                String name = userObject.getString("name");
                                                Log.d("user", name);
                                                String age = userObject.getString("age");
                                                Log.d("user", age);
                                                String nationalId = userObject.getString("nationalId");
                                                Log.d("user", nationalId);


                                                Intent intent1 = new Intent(SignInActivity.this, PatientActivity.class);
                                                intent1.putExtra("USERNAME", username);
                                                intent1.putExtra("EMAIL", email);
                                                intent1.putExtra("PHONE", phone);
                                                intent1.putExtra("NAME", name);
                                                intent1.putExtra("AGE", age);
                                                intent1.putExtra("NATIONALID", nationalId);

                                                startActivity(intent1);
                                                finish();
                                            }
                                            else if (type.equals("doctor")){
                                                userObject = response.getJSONObject("user");
                                                String username = userObject.getString("username");
                                                Log.d("user", username);
                                                String name = userObject.getString("name");
                                                Log.d("user", name);
                                                String email = userObject.getString("email");
                                                Log.d("user", email);
                                                String phone = userObject.getString("phone");
                                                Log.d("user", phone);
                                                String major = userObject.getString("major");
                                                Log.d("user", major);
                                                String licenceNumber = userObject.getString("licenceNumber");
                                                Log.d("user", licenceNumber);
                                                String experienceYear = userObject.getString("experienceYear");
                                                Log.d("user", experienceYear);

                                                Intent intent1 = new Intent(SignInActivity.this, DoctorActivity.class);
                                                intent1.putExtra("USERNAME", username);
                                                intent1.putExtra("EMAIL", email);
                                                intent1.putExtra("PHONE", phone);
                                                intent1.putExtra("NAME", name);
                                                intent1.putExtra("MAJOR", major);
                                                intent1.putExtra("LICENSENUMBER", licenceNumber);
                                                intent1.putExtra("EXPERIENCEYEAR", experienceYear);

                                                startActivity(intent1);
                                                finish();
                                            }
                                            else if (type.equals("clinic")){
                                                userObject = response.getJSONObject("user");
                                                String username = userObject.getString("username");
                                                Log.d("user", username);
                                                String password = userObject.getString("password");
                                                Log.d("user", password);

                                                Intent intent1 = new Intent(SignInActivity.this, AssistantActivity.class);
                                                intent1.putExtra("USERNAME", username);
                                                intent1.putExtra("PASSWORD", password);
                                                startActivity(intent1);
                                                finish();

                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    /*else {
                                        Toast.makeText(SignInActivity.this, "Error in Request.", Toast.LENGTH_LONG).show();
                                    }*/
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    request.setRetryPolicy(new DefaultRetryPolicy(
                            100000,
                            0,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    mRequestQueue.add(request);
                }
            }

        });
    }
}
