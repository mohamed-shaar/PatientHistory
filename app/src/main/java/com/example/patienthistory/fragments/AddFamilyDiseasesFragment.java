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
import com.example.patienthistory.MainActivity;
import com.example.patienthistory.R;
import com.example.patienthistory.VolleySingleton;
import com.example.patienthistory.register_user.SignUpInfoActivity;
import com.example.patienthistory.room.entities.FamilyDiseases;
import com.example.patienthistory.room.viewmodels.FamilyDiseasesViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static android.content.Context.MODE_PRIVATE;

/**
 * This fragment will take information about family diseases, add them to the database and send to the backend
 */
public class AddFamilyDiseasesFragment extends Fragment {

    private EditText et_patient_disease_name;
    private EditText et_patient_relative;
    private Button btn_patient_family_disease_done;

    private FamilyDiseasesViewModel familyDiseasesViewModel;

    private String disease_name;
    private String patient_relative;

    private String url = MainActivity.url + "familyDisease/add";

    private int idHolder;

    private RequestQueue mQueue;

    private JsonObjectRequest jsonObjectRequest;
    private JSONObject patientJsonObject;

    private SharedPreferences sharedPreferences;



    public AddFamilyDiseasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_family_diseases, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        patientJsonObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);

        familyDiseasesViewModel = ViewModelProviders.of(this).get(FamilyDiseasesViewModel.class);

        et_patient_disease_name = view.findViewById(R.id.et_patient_disease_name);
        et_patient_relative = view.findViewById(R.id.et_patient_relative);
        btn_patient_family_disease_done = view.findViewById(R.id.btn_patient_family_diseases_done);

        btn_patient_family_disease_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                familyDiseasesViewModel.insert(new FamilyDiseases(disease_name, patient_relative,1));

                try {
                    patientJsonObject.put("diseaseName", disease_name);
                    patientJsonObject.put("patientRelative", patient_relative);
                    patientJsonObject.put("username", sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            Log.d("Add fam disease: ", String.valueOf(status));
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
        });

        return view;
    }

    private void holdId(int id){idHolder = id;}

    private void extractData(){
        disease_name = et_patient_disease_name.getText().toString().trim();
        patient_relative = et_patient_relative.getText().toString().trim();
    }

}
