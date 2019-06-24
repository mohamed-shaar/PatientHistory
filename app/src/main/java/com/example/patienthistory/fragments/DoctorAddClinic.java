package com.example.patienthistory.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.patienthistory.MainActivity;
import com.example.patienthistory.R;
import com.example.patienthistory.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorAddClinic extends Fragment {

    private String url = MainActivity.url + "";

    private EditText et_doctor_clinic_username;
    private EditText et_doctor_clinic_password;
    private Button btn_doctor_clinic_detail_done;

    private RequestQueue mQueue;
    private JsonObjectRequest jsonObjectRequest;
    private JSONObject clinicObject;

    public DoctorAddClinic() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_add_clinic, container, false);

        et_doctor_clinic_username = view.findViewById(R.id.et_doctor_clinic_username);
        et_doctor_clinic_password = view.findViewById(R.id.et_doctor_clinic_password);
        btn_doctor_clinic_detail_done = view.findViewById(R.id.btn_doctor_clinic_detail_done);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        clinicObject = new JSONObject();

        btn_doctor_clinic_detail_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_doctor_clinic_username.getText().toString();
                String password = et_doctor_clinic_password.getText().toString();

                if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(getContext(), "Please enter data.", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        clinicObject.put("username", username);
                        clinicObject.put("password", password);

                        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, clinicObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return view;
    }

}
