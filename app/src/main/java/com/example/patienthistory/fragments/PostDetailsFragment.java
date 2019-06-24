package com.example.patienthistory.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import static com.example.patienthistory.fragments.PostsFragment.POSTCONTENT;
import static com.example.patienthistory.fragments.PostsFragment.POSTID;
import static com.example.patienthistory.fragments.PostsFragment.POSTTITLE;
import static com.example.patienthistory.register_user.SignUpInfoActivity.SHARED_PREFS;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostDetailsFragment extends Fragment {

    private String url = MainActivity.url + "comments/add";

    private TextView tv_doctor_post_title;
    private TextView tv_doctor_post_details;
    private EditText et_doctor_post_comment;
    private Button btn_doctor_add_comment_done;

    private RequestQueue mQueue;
    private JsonObjectRequest request;
    private JSONObject commentObject;

    private SharedPreferences sharedPreferences;

    public PostDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_details, container, false);

        tv_doctor_post_title = view.findViewById(R.id.tv_doctor_post_title);
        tv_doctor_post_details = view.findViewById(R.id.tv_doctor_post_details);
        et_doctor_post_comment = view.findViewById(R.id.et_doctor_post_comment);
        btn_doctor_add_comment_done = view.findViewById(R.id.btn_doctor_add_comment_done);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        final String id = sharedPreferences.getString(POSTID, "");
        String title = sharedPreferences.getString(POSTTITLE, "");
        String details = sharedPreferences.getString(POSTCONTENT, "");
        tv_doctor_post_title.setText(title);
        tv_doctor_post_details.setText(details);

        commentObject = new JSONObject();
        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();

        btn_doctor_add_comment_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = et_doctor_post_comment.getText().toString();
                if (comment.isEmpty()){
                    getActivity().getSupportFragmentManager().popBackStack();
                }
                else {
                    try {
                        //todo add attributes
                        commentObject.put("comment", comment);
                        commentObject.put("id", id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    request = new JsonObjectRequest(Request.Method.POST, url, commentObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int status = response.getInt("status");
                                Log.d("comment added status", String.valueOf(status));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    mQueue.add(request);
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
        return view;
    }

}
