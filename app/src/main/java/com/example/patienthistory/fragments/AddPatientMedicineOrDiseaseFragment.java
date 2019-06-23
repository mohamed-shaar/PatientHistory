package com.example.patienthistory.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.patienthistory.SignInActivity;
import com.example.patienthistory.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

/**
 * This fragment uses the patient id to add medicine and disease
 */
public class AddPatientMedicineOrDiseaseFragment extends Fragment {

    private String diseaseUrl = MainActivity.url + "disease/add";
    private String medicineUrl = MainActivity.url + "medicine/add";

    private EditText et_disease_description;
    private EditText et_disease_name;
    private Button btn_add_disease;
    private EditText et_medicine_name;
    private EditText et_medicine_treatment_for;
    private EditText et_medicine_times;
    private EditText et_medicine_interval;
    private EditText et_medicine_dose;
    private Button btn_add_medicine;

    private RequestQueue mQueue;

    private JSONObject diseaseJsonObject;
    private JSONObject medicineJsonObject;

    private SharedPreferences sharedPreferences;

    private JsonObjectRequest jsonObjectRequest;

    private String diseaseId;


    public AddPatientMedicineOrDiseaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine_disease, container, false);
        // get patient username from shared preference
        //get disease name and description from edit text
        //

        et_disease_description = view.findViewById(R.id.et_disease_description);
        et_disease_name = view.findViewById(R.id.et_disease_name);
        btn_add_disease = view.findViewById(R.id.btn_add_disease);
        et_medicine_name = view.findViewById(R.id.et_medicine_name);
        et_medicine_treatment_for = view.findViewById(R.id.et_medicine_treatment_for);
        et_medicine_interval = view.findViewById(R.id.et_medicine_interval);
        et_medicine_times = view.findViewById(R.id.et_medicine_times);
        et_medicine_dose = view.findViewById(R.id.et_medicine_dose);
        btn_add_medicine = view.findViewById(R.id.btn_add_medicine);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        diseaseJsonObject = new JSONObject();
        medicineJsonObject = new JSONObject();
        sharedPreferences = this.getActivity().getSharedPreferences(SignInActivity.SHARED_PREFS, Context.MODE_PRIVATE);

        btn_add_disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sharedPreferences.getString("patientUsername", "");
                String description = et_disease_description.getText().toString().trim();
                String name = et_disease_name.getText().toString().trim();
                if (description.isEmpty() || name.isEmpty()) {
                    Toast.makeText(getContext(), "Please Enter Disease Information", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        diseaseJsonObject.put("username", username);
                        diseaseJsonObject.put("description", description);
                        diseaseJsonObject.put("name", name);
                        diseaseJsonObject.put("doctor", sharedPreferences.getString(SignInActivity.USERNAME, ""));

                        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, diseaseUrl, diseaseJsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    int status = response.getInt("status");
                                    Log.d("status", String.valueOf(status));
                                    diseaseId = response.getString("diseaseId");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                        mQueue.add(jsonObjectRequest);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btn_add_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_medicine_name.getText().toString().trim();
                String treatmentFor = et_medicine_treatment_for.getText().toString().trim();
                String times = et_medicine_times.getText().toString().trim();
                String interval = et_medicine_interval.getText().toString().trim();
                String dose = et_medicine_dose.getText().toString().trim();

                if (name.isEmpty() || treatmentFor.isEmpty() || times.isEmpty() || interval.isEmpty() || dose.isEmpty()){
                    Toast.makeText(getContext(), "Please enter medicine data.", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        String username = sharedPreferences.getString("patientUsername", "");
                        medicineJsonObject.put("username", username);
                        medicineJsonObject.put("diseaseId", diseaseId);
                        medicineJsonObject.put("name", name);
                        medicineJsonObject.put("treatment_for", treatmentFor);
                        medicineJsonObject.put("dose", dose);
                        medicineJsonObject.put("times", times);
                        medicineJsonObject.put("interval", interval);

                        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, medicineUrl, medicineJsonObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    int status = response.getInt("status");
                                    Log.d("status", String.valueOf(status));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mQueue.add(jsonObjectRequest);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

}
