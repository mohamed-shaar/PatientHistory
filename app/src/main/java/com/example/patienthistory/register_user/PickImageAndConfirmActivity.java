package com.example.patienthistory.register_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.example.patienthistory.MainActivity;
import com.example.patienthistory.R;
import com.example.patienthistory.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity lets the patient pick an image as a profile picture, collects all the sign up data and sends it in a request.
 */
public class PickImageAndConfirmActivity extends AppCompatActivity {

    private final int PICK_IMAGE_REQUEST = 71;
    public static String SHARED_PREFS = SignUpInfoActivity.SHARED_PREFS;
    public static String PICTURE_PATH = "picture_path";
    public static int FINISHED_REGISTER_ACTIVITIES = 1;

    private Uri filepath;

    private ImageView iv_profile_picture;
    private Button btn_pick_profile_picture;
    private Button btn_finish_sign_up;
    private CheckBox cb_terms;

    private RequestQueue mQueue;

    private Bitmap bitmap;

    private boolean pickedImage;
    private boolean termsAgreed;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_image_and_confirm);

        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mQueue = VolleySingleton.getInstance(this).getRequestQueue();

        iv_profile_picture = findViewById(R.id.iv_profile_picture);
        btn_pick_profile_picture = findViewById(R.id.btn_pick_profile_picture);
        btn_finish_sign_up = findViewById(R.id.btn_finish_sign_up);
        cb_terms = findViewById(R.id.cb_terms);

        btn_pick_profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btn_finish_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termsAgreed = cb_terms.isChecked();
                if (termsAgreed && pickedImage){
                    editor.apply();

                    String url = "http://192.168.88.141:3000/patient/register";

                    final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    String username = sharedPreferences.getString(SignUpInfoActivity.USERNAME, "");
                    String email = sharedPreferences.getString(SignUpInfoActivity.EMAIL, "");
                    String phone = sharedPreferences.getString(ContactInfoActivity.PHONE_NUMBER, "");
                    String name = sharedPreferences.getString(ContactInfoActivity.FULL_NAME, "");
                    String age = sharedPreferences.getString(ContactInfoActivity.AGE, "");
                    String id = sharedPreferences.getString(SignUpInfoActivity.NATIONAL_ID, "");

                    JSONObject object = new JSONObject();
                    try {
                        object.put("username", username);
                        object.put("email",email);
                        object.put("phone", phone);
                        object.put("name",name);
                        object.put("age", age);
                        object.put("nationalId", id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, object, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(PickImageAndConfirmActivity.this, "Success", Toast.LENGTH_LONG).show();
                            Log.d("net", " it worked");
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(PickImageAndConfirmActivity.this, "Failed", Toast.LENGTH_LONG).show();
                            Log.d("net", " it failed");
                        }
                    });

                    request.setRetryPolicy(new DefaultRetryPolicy(
                            10000,
                            0,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    mQueue.add(request);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    final byte [] imageBytes = byteArrayOutputStream.toByteArray();

                    /*StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    })

                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("username", sharedPreferences.getString(SignUpInfoActivity.USERNAME, ""));
                            params.put("image", Base64.encodeToString(imageBytes, Base64.DEFAULT));
                            return params;
                        }
                    };

                    mQueue.add(stringRequest);*/

                    Intent intent = new Intent(PickImageAndConfirmActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                //iv_profile_picture.setImageBitmap(bitmap);
                Glide.with(getApplicationContext()).load(filepath).into(iv_profile_picture);
                pickedImage = true;
                editor.putString(PICTURE_PATH, filepath.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

}
