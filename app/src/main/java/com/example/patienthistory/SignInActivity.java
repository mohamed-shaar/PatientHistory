package com.example.patienthistory;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

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

        et_username = findViewById(R.id.et_username_sign_in);
        et_password = findViewById(R.id.et_password_sign_in);
        tv_login = findViewById(R.id.tv_log_in_sign_in);
        mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                String url = String.valueOf(R.string.localhost + R.string.port);

                JsonObjectRequest signIn = new JsonObjectRequest(Request.Method.GET,
                        url,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    // Todo get a json array with if the user exists, their type and id
                                    String exists = response.getString("response");
                                    if (exists.equals("true")){
                                        // TODO start new intent based on user type
                                    }
                                    else {
                                        Toast.makeText(SignInActivity.this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                mRequestQueue.add(signIn);
            }
        });
    }
}
