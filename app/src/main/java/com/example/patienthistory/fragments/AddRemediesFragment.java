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
import com.example.patienthistory.room.entities.Remedies;
import com.example.patienthistory.room.viewmodels.RemediesViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static android.content.Context.MODE_PRIVATE;

/**
 * This fragment takes remedies' information from the user, adds them to the database and sends it to the backend
 */
public class AddRemediesFragment extends Fragment {

    private EditText et_patient_remedies_name;
    private EditText et_patient_remedies_start_Date;
    private EditText et_patient_remedies_dose;
    private EditText et_patient_remedies_outcome;

    private Button btn_patient_remedies_done;

    private RemediesViewModel remediesViewModel;

    private String url = MainActivity.url + "patient/addremediesdata";

    private RequestQueue mQueue;

    private JsonObjectRequest jsonObjectRequest;
    private JSONObject patientJsonObject;

    private SharedPreferences sharedPreferences;

    String name;
    String date;
    int dose;
    String outcome;


    public AddRemediesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_remedies, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        patientJsonObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);

        et_patient_remedies_name = view.findViewById(R.id.et_patient_remedies_name);
        et_patient_remedies_start_Date = view.findViewById(R.id.et_patient_remedies_start_Date);
        et_patient_remedies_dose = view.findViewById(R.id.et_patient_remedies_dose);
        et_patient_remedies_outcome = view.findViewById(R.id.et_patient_remedies_outcome);
        btn_patient_remedies_done = view.findViewById(R.id.btn_patient_remedies_done);

        remediesViewModel = ViewModelProviders.of(this).get(RemediesViewModel.class);

        btn_patient_remedies_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                if (name.isEmpty() || date.isEmpty() || outcome.isEmpty()){
                    Toast.makeText(getContext(), "Please enter fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    remediesViewModel.insert(new Remedies(1, name, date, dose, outcome));

                    try {
                        patientJsonObject.put("name", name);
                        patientJsonObject.put("date", date);
                        patientJsonObject.put("dose", dose);
                        patientJsonObject.put("outcome", outcome);
                        patientJsonObject.put("username", sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("status");
                                Log.d("Remedies: ", String.valueOf(status));
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
        name = et_patient_remedies_name.getText().toString().trim();
        date = et_patient_remedies_start_Date.getText().toString().trim();
        dose = Integer.parseInt(et_patient_remedies_dose.getText().toString());
        outcome = et_patient_remedies_outcome.getText().toString().trim();
    }

}
