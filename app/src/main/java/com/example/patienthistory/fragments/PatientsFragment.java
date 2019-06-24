package com.example.patienthistory.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.content.Context.MODE_PRIVATE;

/**
 * This fragment allows the doctor to see all of their patients
 */

public class PatientsFragment extends Fragment {

    private String url = MainActivity.url + "doctor/patients";

    private RequestQueue mQueue;

    private JSONObject doctorJsonObject;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private JsonObjectRequest jsonObjectRequest;

    private List<String> patientNames;
    private List<String> patientUserNames;

    private ListView listView;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patients, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        doctorJsonObject = new JSONObject();

        patientNames = new ArrayList<>();
        patientUserNames = new ArrayList<>();

        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        listView = view.findViewById(R.id.lv_patients);

        try {
            doctorJsonObject.put(SignUpInfoActivity.USERNAME, sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, doctorJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    Log.d("status", status);
                    JSONArray jsonArray = response.getJSONArray("mai");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String username = object.getString("username");
                        String name = object.getString("name");
                        Log.d("username", username);
                        Log.d("name", name);
                        patientNames.add(name);
                        patientUserNames.add(username);
                    }
                    ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, patientNames);
                    listView.setAdapter(adapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            editor.putString("patientUsername", patientUserNames.get(position));
                            editor.apply();
                            DoctorChoices fragment = new DoctorChoices();
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.doctor_frame_layout, fragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                    });

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

        return view;
    }
}
