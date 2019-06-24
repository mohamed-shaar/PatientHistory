package com.example.patienthistory.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.patienthistory.MainActivity;
import com.example.patienthistory.R;
import com.example.patienthistory.VolleySingleton;
import com.example.patienthistory.register_user.SignUpInfoActivity;

import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class ClinicFragment extends Fragment {

    private String url = MainActivity.url + "";

    private RequestQueue mQueue;

    private SharedPreferences sharedPreferences;

    private JsonObjectRequest jsonObjectRequest;

    private ListView listView;
    private ArrayAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_clinic, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        listView = view.findViewById(R.id.lv_doctor_clinics);


        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);


        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mQueue.add(jsonObjectRequest);

        return view;
    }
}
