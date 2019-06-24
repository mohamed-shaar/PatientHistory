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

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static android.content.Context.MODE_PRIVATE;
import static com.example.patienthistory.fragments.PatientsFragment.PATIENTUSERNAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorViewPatientData extends Fragment {

    //todo parse request
    private String url = MainActivity.url + "file/";
    public static String LINK = "link";

    private ListView listView;
    private List<String> fileType;
    private List<String> fileDownloadLink;

    private RequestQueue mQueue;
    private JsonObjectRequest jsonObjectRequest;
    private JSONObject jsonObject;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;




    public DoctorViewPatientData() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_view_patient_data, container, false);

        listView = view.findViewById(R.id.lv_doctor_view_patient_details);
        preferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);
        editor = preferences.edit();
        jsonObject = new JSONObject();
        listView = view.findViewById(R.id.lv_doctor_view_patient_details);
        fileType = new ArrayList<>();
        fileDownloadLink = new ArrayList<>();

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        try {
            jsonObject.put("username", preferences.getString(PATIENTUSERNAME, ""));

            jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray fileArray = response.getJSONArray("file");
                        if (fileArray != null) Log.d("filearray", "null");

                        for (int i = 0; i < fileArray.length(); i++) {

                            JSONObject currentObject = fileArray.getJSONObject(i);

                            String type = currentObject.getString("type");
                            String link = currentObject.getString("link");

                            fileType.add(type);
                            fileDownloadLink.add(link);

                            Log.d("type: ", fileType.get(i));
                            Log.d("links: ", fileDownloadLink.get(i));

                        }

                        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, fileType);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String link = fileDownloadLink.get(position);
                                Log.d("Link", link);
                                editor.putString(LINK, link);
                                editor.apply();
                                DoctorViewPatientImageFragment fragment = new DoctorViewPatientImageFragment();
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

        } catch (JSONException e) {
            e.printStackTrace();
        }



        return view;
    }

}
