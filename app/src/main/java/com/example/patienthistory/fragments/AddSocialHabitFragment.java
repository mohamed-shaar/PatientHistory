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
import com.example.patienthistory.room.entities.SocialHabit;
import com.example.patienthistory.room.viewmodels.SocialHabitViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static android.content.Context.MODE_PRIVATE;

/**
 * This fragment takes the social habit info from the user, adds them to the database and sends them to the backend
 */
public class AddSocialHabitFragment extends Fragment {

    private EditText et_patient_social_habit_tobacco;
    private EditText et_patient_social_habit_alcohol;
    private EditText et_patient_social_habit_illicit_drugs;
    private Button btn_patient_social_habit_done;

    private SocialHabitViewModel socialHabitViewModel;

    Boolean tobacco;
    Boolean alcohol;
    Boolean illicit_drugs;

    int idHolder = 0;

    private String url = MainActivity.url + "socialHabit/add";

    private RequestQueue mQueue;

    private JsonObjectRequest jsonObjectRequest;
    private JSONObject patientJsonObject;

    private SharedPreferences sharedPreferences;


    public AddSocialHabitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_social_habit, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        patientJsonObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);

        socialHabitViewModel = ViewModelProviders.of(this).get(SocialHabitViewModel.class);

        et_patient_social_habit_tobacco = view.findViewById(R.id.et_patient_social_habit_tobacco);
        et_patient_social_habit_alcohol = view.findViewById(R.id.et_patient_social_habit_alcohol);
        et_patient_social_habit_illicit_drugs = view.findViewById(R.id.et_patient_social_habit_illicit_drugs);
        btn_patient_social_habit_done = view.findViewById(R.id.btn_patient_social_habit_done);

        socialHabitViewModel.getSocialHabitLiveData().observe(this, new Observer<SocialHabit>() {
            @Override
            public void onChanged(SocialHabit socialHabit) {
                if (socialHabit != null){
                    et_patient_social_habit_alcohol.setText(Boolean.toString(socialHabit.isAlcohol()));
                    et_patient_social_habit_tobacco.setText(Boolean.toString(socialHabit.isTobacco()));
                    et_patient_social_habit_illicit_drugs.setText(Boolean.toString(socialHabit.isDrugs()));
                    setIdHolder(socialHabit.getId());
                }
            }
        });

        btn_patient_social_habit_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extractData();
                if (alcohol == null || tobacco == null || illicit_drugs == null){
                    Toast.makeText(getContext(), "Please Enter Valid Fields.", Toast.LENGTH_SHORT).show();
                }
                else {
                    SocialHabit socialHabit = new SocialHabit(1, illicit_drugs, tobacco, alcohol);
                    if (idHolder == 0){
                        socialHabitViewModel.insert(new SocialHabit(1, illicit_drugs, tobacco, alcohol));
                    }
                    else {
                        socialHabit.setId(idHolder);
                        socialHabitViewModel.update(socialHabit);
                    }

                    try {
                        patientJsonObject.put("illicitDrugs", illicit_drugs);
                        patientJsonObject.put("tobacco", tobacco);
                        patientJsonObject.put("alcohol", alcohol);
                        patientJsonObject.put("username", sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, patientJsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("status");
                                Log.d("Add social habit:" , String.valueOf(status));
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
        tobacco =  Boolean.valueOf(et_patient_social_habit_tobacco.getText().toString().trim());
        alcohol = Boolean.valueOf(et_patient_social_habit_alcohol.getText().toString().trim());
        illicit_drugs = Boolean.valueOf(et_patient_social_habit_illicit_drugs.getText().toString().trim());
    }

    private void setIdHolder(int id){ idHolder = id;}

}
