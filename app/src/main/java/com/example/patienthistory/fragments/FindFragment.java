package com.example.patienthistory.fragments;

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
import com.example.patienthistory.VolleySingleton;
import com.example.patienthistory.register_user.SignUpInfoActivity;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static com.example.patienthistory.SignInActivity.SHARED_PREFS;

/**
 * This fragment allows the patient to enter the doctor's identifier to give the doctor access to
 * their data.
 */

public class FindFragment extends Fragment {

    private EditText et_patient_add_doctor;
    private Button btn_patient_add_doctor_done;

    private String doctor_username;

    private String url = MainActivity.url + "patient/permission";

    private JSONObject patientJsonObject;

    private JsonObjectRequest jsonObjectRequest;

    private RequestQueue mQueue;

    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        et_patient_add_doctor = view.findViewById(R.id.et_patient_add_doctor);
        btn_patient_add_doctor_done = view.findViewById(R.id.btn_patient_add_doctor_done);

        patientJsonObject = new JSONObject();

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        btn_patient_add_doctor_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                if (doctor_username.isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter a Doctor username.", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        patientJsonObject.put("username", sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
                        patientJsonObject.put("name", sharedPreferences.getString("name", ""));
                        patientJsonObject.put("doctorusername", doctor_username);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("status");
                                Log.d("Find status: ", String.valueOf(status));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(getActivity(), "An error occurred, please try again.", Toast.LENGTH_LONG).show();
                        }
                    });
                    mQueue.add(jsonObjectRequest);
                }
            }
        });


        return view;
    }
    private void extractData(){
        doctor_username = et_patient_add_doctor.getText().toString().trim();
    }
}
