package com.example.patienthistory.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.patienthistory.MainActivity;
import com.example.patienthistory.R;
import com.example.patienthistory.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;
import static com.example.patienthistory.register_user.SignUpInfoActivity.SHARED_PREFS;
import static com.example.patienthistory.register_user.SignUpInfoActivity.USERNAME;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPostFragment extends Fragment {

    private String url = MainActivity.url + "post/add";

    private TextView et_doctor_add_post_Title;
    private TextView et_doctor_add_post_content;
    private Button btn_doctor_add_post_done;

    private RequestQueue mQueue;
    private JsonObjectRequest jsonObjectRequest;
    private JSONObject postObject;

    private SharedPreferences sharedPreferences;


    public AddPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_post, container, false);

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        postObject = new JSONObject();

        et_doctor_add_post_Title = view.findViewById(R.id.et_doctor_add_post_Title);
        et_doctor_add_post_content = view.findViewById(R.id.et_doctor_add_post_content);
        btn_doctor_add_post_done = view.findViewById(R.id.btn_doctor_add_post_done);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        btn_doctor_add_post_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_doctor_add_post_Title.getText().toString();
                String content = et_doctor_add_post_content.getText().toString();

                if (title.isEmpty() || content.isEmpty()){
                    Toast.makeText(getContext(), "Please Enter Post Details.", Toast.LENGTH_LONG).show();
                }
                else {
                    try {
                        postObject.put("title", title);
                        postObject.put("article", content);
                        postObject.put("username", sharedPreferences.getString(USERNAME, ""));

                        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postObject, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    mQueue.add(jsonObjectRequest);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });


        return view;
    }

}
