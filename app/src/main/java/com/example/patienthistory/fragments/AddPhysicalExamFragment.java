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
import com.example.patienthistory.room.entities.PhysicalExam;
import com.example.patienthistory.room.viewmodels.PhysicalExamViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static android.content.Context.MODE_PRIVATE;

/**
 * This fragment takes physical exam data from the user, adds it to the database and sends it to the backend
 */
public class AddPhysicalExamFragment extends Fragment {

    private EditText et_patient_blood_pressure;
    private EditText et_patient_heart_rate;
    private Button btn_patient_physical_exam_done;

    private PhysicalExamViewModel physicalExamViewModel;

    String blood_pressure;
    String heart_rate;

    private String url = MainActivity.url + "physicalexam/add";

    private RequestQueue mQueue;

    private JsonObjectRequest jsonObjectRequest;
    private JSONObject patientJsonObject;

    private SharedPreferences sharedPreferences;

    int idHolder = 0;


    public AddPhysicalExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_physical_exam, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        patientJsonObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);

        physicalExamViewModel = ViewModelProviders.of(this).get(PhysicalExamViewModel.class);

        et_patient_blood_pressure = view.findViewById(R.id.et_patient_blood_pressure);
        et_patient_heart_rate = view.findViewById(R.id.et_patient_heart_rate);
        btn_patient_physical_exam_done = view.findViewById(R.id.btn_patient_physical_exam_done);

        physicalExamViewModel.getPhysicalExamLiveData().observe(this, new Observer<PhysicalExam>() {
            @Override
            public void onChanged(PhysicalExam physicalExam) {
                if (physicalExam != null){
                    et_patient_blood_pressure.setText(physicalExam.getBloodPressure());
                    et_patient_heart_rate.setText(physicalExam.getHeartRate());
                    setIdHolder(physicalExam.getId());
                }
            }
        });

        btn_patient_physical_exam_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                if (blood_pressure.isEmpty() || heart_rate.isEmpty()){
                    Toast.makeText(getContext(), "Please Enter the Fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    PhysicalExam physicalExam = new PhysicalExam(1, blood_pressure, heart_rate);
                    if (idHolder == 0){
                        physicalExamViewModel.insert(physicalExam);
                    }
                    else {
                        physicalExam.setId(idHolder);
                        physicalExamViewModel.update(physicalExam);
                    }

                    try {
                        patientJsonObject.put("bloodPressure", blood_pressure);
                        patientJsonObject.put("heartRate", heart_rate);
                        patientJsonObject.put("username", sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("status");
                                Log.d("Add physical data: ", String.valueOf(status));
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
                    mQueue.add(jsonObjectRequest);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

    private void extractData(){
        blood_pressure = et_patient_blood_pressure.getText().toString().trim();
        heart_rate = et_patient_heart_rate.getText().toString().trim();
    }

    private void setIdHolder(int id){ idHolder = id;}

}
