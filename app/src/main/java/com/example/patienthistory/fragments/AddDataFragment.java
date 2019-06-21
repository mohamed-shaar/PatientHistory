package com.example.patienthistory.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.patienthistory.R;
import com.example.patienthistory.SignInActivity;
import com.example.patienthistory.VolleySingleton;
import com.example.patienthistory.register_user.SignUpInfoActivity;
import com.example.patienthistory.room.entities.Patient;
import com.example.patienthistory.room.viewmodels.PatientViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static android.content.Context.MODE_PRIVATE;


public class AddDataFragment extends Fragment {

    public static String SHARED_PREFS = SignUpInfoActivity.SHARED_PREFS;
    public static String USERNAME = SignUpInfoActivity.USERNAME;

    private EditText et_patient_address;
    private EditText et_patient_blood_type;
    private EditText et_patient_social_status;
    private EditText et_patient_number_of_children;
    private Button btn_patient_data_done;

    private PatientViewModel patientViewModel;

    private String address;
    private String blood_type;
    private String social_status;
    private String number_of_children;

    private String type = "insert";
    private String url = "http://192.168.88.141:3000/patient/addpatientdata";

    private int idHolder;

    private RequestQueue mQueue;

    private JsonObjectRequest jsonObjectRequest;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_data, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        final JSONObject patientJSONObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        et_patient_address = view.findViewById(R.id.et_patient_address);
        et_patient_blood_type = view.findViewById(R.id.et_patient_blood_type);
        et_patient_social_status = view.findViewById(R.id.et_patient_social_status);
        et_patient_number_of_children = view.findViewById(R.id.et_patient_number_of_children);
        btn_patient_data_done = view.findViewById(R.id.btn_patient_data_done);

        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.getData().observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                if (patient != null){
                    et_patient_address.setText(patient.getAddress());
                    et_patient_blood_type.setText(patient.getBloodType());
                    et_patient_social_status.setText(patient.getSocialStatus());
                    et_patient_number_of_children.setText(String.valueOf(patient.getNumberOfChildren()));
                    holdId(patient.getId());
                    setType("update");
                }
            }
        });

        btn_patient_data_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                Patient patient = new Patient(address, blood_type, social_status, Integer.parseInt(number_of_children));
                if (type.equals("insert")){
                    patientViewModel.insert(patient);
                    try {
                        patientJSONObject.put("address", address);
                        patientJSONObject.put("bloodType", blood_type);
                        patientJSONObject.put("socialStatus", social_status);
                        patientJSONObject.put("numberOfChildren", number_of_children);
                        patientJSONObject.put(SignInActivity.USERNAME, sharedPreferences.getString("username", ""));
                        Log.d("username", sharedPreferences.getString("username", ""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJSONObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    patientViewModel.insert(patient);
                }
                else {
                    try {
                        patientJSONObject.put("address", address);
                        patientJSONObject.put("bloodType", blood_type);
                        patientJSONObject.put("socialStatus", social_status);
                        patientJSONObject.put("numberOfChildren", number_of_children);
                        patientJSONObject.put(SignInActivity.USERNAME, sharedPreferences.getString("username", ""));
                        Log.d("username", sharedPreferences.getString("username", ""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJSONObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    patient.setId(idHolder);
                    patientViewModel.update(patient);
                }
                mQueue.add(jsonObjectRequest);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void extractData(){
        address = et_patient_address.getText().toString().trim();
        blood_type = et_patient_blood_type.getText().toString();
        social_status = et_patient_social_status.getText().toString();
        number_of_children = et_patient_number_of_children.getText().toString();
    }

    private void holdId(int id){ idHolder = id;}

    private void setType(String op){ type = op;}


}
