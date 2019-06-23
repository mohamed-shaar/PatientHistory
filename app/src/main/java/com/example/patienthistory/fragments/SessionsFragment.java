package com.example.patienthistory.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 *
 * add medicine code
 */
public class SessionsFragment extends Fragment {

    private String url = MainActivity.url + "/medicine/add";
    /**
     * username patient
     * diseaseId
     * medicine name
     * treatmentFor
     * dose
     * times
     * interval
     */

    private RequestQueue mQueue;

    private JSONObject medicineJsonObject;

    private SharedPreferences sharedPreferences;

    private JsonObjectRequest jsonObjectRequest;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        medicineJsonObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SignInActivity.SHARED_PREFS, Context.MODE_PRIVATE);

        try {
            medicineJsonObject.put("username", "phoneId");
            medicineJsonObject.put("diseaseId", "100");
            medicineJsonObject.put("name", "panadol");
            medicineJsonObject.put("treatment_for", "headache");
            medicineJsonObject.put("dose", "100mg");
            medicineJsonObject.put("times", "3");
            medicineJsonObject.put("interval", "1");

            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, medicineJsonObject, new Response.Listener<JSONObject>() {
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

        return view;
    }
}
