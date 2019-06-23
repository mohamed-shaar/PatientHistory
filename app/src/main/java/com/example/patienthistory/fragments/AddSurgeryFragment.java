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
import com.example.patienthistory.room.entities.Surgery;
import com.example.patienthistory.room.viewmodels.SurgeryViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static android.content.Context.MODE_PRIVATE;

/**
 * This fragment allows the patient to input the names of any surgery that they have done, adds them to the database
 * and send it to the backend.
 */
public class AddSurgeryFragment extends Fragment {

    private EditText et_patient_surgery_name;
    private Button btn_patient_surgeries_done;

    private SurgeryViewModel surgeryViewModel;

    String surgeryName;

    private String url = MainActivity.url + "patient/addsurgerydata";

    private RequestQueue mQueue;

    private JsonObjectRequest jsonObjectRequest;
    private JSONObject patientJsonObject;

    private SharedPreferences sharedPreferences;


    public AddSurgeryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_surgery, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        patientJsonObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);

        et_patient_surgery_name = view.findViewById(R.id.et_patient_surgery_name);
        btn_patient_surgeries_done = view.findViewById(R.id.btn_patient_surgeries_done);

        surgeryViewModel = ViewModelProviders.of(this).get(SurgeryViewModel.class);

        btn_patient_surgeries_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                if (surgeryName.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a surgery name.", Toast.LENGTH_SHORT).show();
                }
                else {
                    surgeryViewModel.insert(new Surgery(1, surgeryName));

                    try {
                        patientJsonObject.put("surgeryName", surgeryName);
                        patientJsonObject.put("username", sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("status");
                                Log.d("Add surgery:" , String.valueOf(status));
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
        surgeryName = et_patient_surgery_name.getText().toString().trim();
    }

}
