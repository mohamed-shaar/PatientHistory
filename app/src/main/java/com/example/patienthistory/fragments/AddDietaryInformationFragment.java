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
import com.example.patienthistory.room.entities.DietaryInformation;
import com.example.patienthistory.room.viewmodels.DietaryInformationViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static android.content.Context.MODE_PRIVATE;

/**
 *  This class add a patient's dietary information to the database and sends it to the backend
 */
public class AddDietaryInformationFragment extends Fragment {

    //EditText variables to get the data from the user
    private EditText et_patient_restrictions;
    private EditText et_patient_supplements;
    private EditText et_patient_stimulants;
    private Button btn_patient_dietary_information_done;

    //viewModel to access the database
    private DietaryInformationViewModel dietaryInformationViewModel;

    //these variable will hold the data from the editTexts
    String restrictions;
    String supplements;
    String stimulants;

    private String url = MainActivity.url + "dietaryInformation/add";

    //request queue
    private RequestQueue mQueue;

    //request object to be sent
    private JsonObjectRequest jsonObjectRequest;

    //json object to hold the data being sent
    private JSONObject patientJsonObject;

    private SharedPreferences sharedPreferences;


    public AddDietaryInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_dietary_information, container, false);

        //singleton initialization
        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        //ViewModel with context initialization
        dietaryInformationViewModel = ViewModelProviders.of(this).get(DietaryInformationViewModel.class);

        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);

        patientJsonObject = new JSONObject();

        //linking edit text with xml
        et_patient_restrictions = view.findViewById(R.id.et_patient_restrictions);
        et_patient_supplements = view.findViewById(R.id.et_patient_supplements);
        et_patient_stimulants = view.findViewById(R.id.et_patient_stimulants);
        btn_patient_dietary_information_done = view.findViewById(R.id.btn_patient_dietary_information_done);

        btn_patient_dietary_information_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from edit text and place into variables
                extractData();
                dietaryInformationViewModel.insert(new DietaryInformation(1, restrictions, supplements, stimulants));

                try {
                    patientJsonObject.put("restrictions", restrictions);
                    patientJsonObject.put("supplements", supplements);
                    patientJsonObject.put("stimulants", stimulants);
                    patientJsonObject.put("username", sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int status = response.getInt("status");
                            Log.d("diet info status: ", String.valueOf(status));
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

                //add request to queue
                mQueue.add(jsonObjectRequest);
                //return to previous screen
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private void extractData(){
        restrictions = et_patient_restrictions.getText().toString().trim();
        supplements = et_patient_supplements.getText().toString().trim();
        stimulants = et_patient_stimulants.getText().toString().trim();
    }

}
