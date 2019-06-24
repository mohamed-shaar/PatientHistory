package com.example.patienthistory.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.patienthistory.MainActivity;
import com.example.patienthistory.R;
import com.example.patienthistory.VolleySingleton;

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
import static com.example.patienthistory.register_user.SignUpInfoActivity.SHARED_PREFS;

public class PostsFragment extends Fragment {

    private String url = MainActivity.url + "post/";

    public static String POSTID = "postid";
    public static String POSTCONTENT = "postContent";
    public static String POSTTITLE = "postTitle";

    private RequestQueue mQueue;
    private ListView listView;

    private List<String> postTitles;
    private List<String> postContent;
    private List<String> postIds;

    private JsonObjectRequest jsonObjectRequest;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private Button btn_doctor_add_post;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts, container, false);

        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mQueue = VolleySingleton.getInstance(getContext()).getRequestQueue();
        btn_doctor_add_post = view.findViewById(R.id.btn_doctor_add_post);
        listView = view.findViewById(R.id.lv_doctor_posts);
        postIds = new ArrayList<>();
        postTitles = new ArrayList<>();
        postContent = new ArrayList<>();

        jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //todo parse

                try {
                    JSONArray postsArray = response.getJSONArray("posts");

                    for (int i = 0; i < postsArray.length(); i++) {
                        JSONObject postObject = postsArray.getJSONObject(i);
                        String postId = postObject.getString("id");

                        JSONObject contentObject = postObject.getJSONObject("post");
                        String content = contentObject.getString("article");
                        String title = contentObject.getString("title");

                        postIds.add(postId);
                        Log.d("post id", postIds.get(i));
                        postTitles.add(title);
                        Log.d("post title: ", postTitles.get(i));
                        postContent.add(content);
                        Log.d("post Content: ", postContent.get(i));
                    }


                    ArrayAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, postTitles);
                    if (adapter.isEmpty()){
                        Log.d("adapter", String.valueOf(postTitles.size()));
                    }else {
                        listView.setAdapter(adapter);
                    }

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            editor.putString(POSTID, postIds.get(position));
                            editor.putString(POSTTITLE, postTitles.get(position));
                            editor.putString(POSTCONTENT, postContent.get(position));
                            editor.apply();
                            PostDetailsFragment fragment = new PostDetailsFragment();
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

        btn_doctor_add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPostFragment fragment = new AddPostFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.doctor_frame_layout, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
