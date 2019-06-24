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

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SessionFragment extends Fragment {

    private EditText et_doctor_session_details;
    private Button btn_doctor_add_session_done;

    //TODO update url
    private String url = MainActivity.url + "";

    private SharedPreferences sharedPreferences;

    private JSONObject sessionJsonObject;

    private JsonObjectRequest jsonObjectRequest;

    private RequestQueue mQueue;


    public SessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_session, container, false);

        et_doctor_session_details = view.findViewById(R.id.et_doctor_session_details);
        btn_doctor_add_session_done = view.findViewById(R.id.btn_doctor_add_session_done);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        sessionJsonObject = new JSONObject();

        sharedPreferences = this.getActivity().getSharedPreferences(SignUpInfoActivity.SHARED_PREFS, MODE_PRIVATE);

        btn_doctor_add_session_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String details = et_doctor_session_details.getText().toString().trim();
                if (details.isEmpty()){
                    Toast.makeText(getActivity(), "Please Enter Session details.", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        sessionJsonObject.put("username", sharedPreferences.getString("patientUsername", ""));
                        sessionJsonObject.put("session", details);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, sessionJsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("Status");
                                Log.d("Add Session status:", String.valueOf(status));
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
                }
            }
        });

        return view;
    }

}
