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
import com.example.patienthistory.room.entities.Allergies;
import com.example.patienthistory.room.viewmodels.AllergiesViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static android.content.Context.MODE_PRIVATE;

/**
 * The fragment is used in the patient activity after they press the allergies button
 * It uses the AllergiesViewModel to access the database.
 */
public class AddAllergy extends Fragment {

    //variables for shared preference quick storage
    public static String SHARED_PREFS = SignUpInfoActivity.SHARED_PREFS;
    public static String USERNAME = SignUpInfoActivity.USERNAME;

    //updated url for http request
    private String url = MainActivity.url + "allergies/add";

    private EditText et_patient_allergies;
    private Button btn_patient_allergies_done;

    private AllergiesViewModel allergiesViewModel;

    //contents of edit text will be placed in this variable
    private String allergy;

    //for sending http requests
    private RequestQueue mQueue;

    // to create the http request
    private JsonObjectRequest jsonObjectRequest;
    // to hold the json obejct data to be sent in the request
    private JSONObject patientJSONObject;

    private SharedPreferences sharedPreferences;


    public AddAllergy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_allergy, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        patientJSONObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        //ViewModels that are used in activities or fragments need context when initialized so ViewModelProvider is used.
        allergiesViewModel = ViewModelProviders.of(this).get(AllergiesViewModel.class);

        et_patient_allergies = view.findViewById(R.id.et_patient_allergies);
        btn_patient_allergies_done = view.findViewById(R.id.btn_patient_allergies_done);

        btn_patient_allergies_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the allergy name from the user
                extractData();

                //insert the new allergy into the database
                allergiesViewModel.insert(new Allergies(1, allergy));
                try {

                    //added data to the json object to be sent, first parameter is the identifier and the second is the value
                    patientJSONObject.put("username", sharedPreferences.getString(USERNAME, ""));
                    patientJSONObject.put("name", allergy);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJSONObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            Log.d("Add Allergy Statys", String.valueOf(status));
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

                //add the request to the request queue
                mQueue.add(jsonObjectRequest);

                //return to the previous screen
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void extractData(){
        allergy = et_patient_allergies.getText().toString().trim();
    }

}
