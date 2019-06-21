package com.example.patienthistory.register_user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.patienthistory.MainActivity;
import com.example.patienthistory.R;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                iv_profile_picture.setImageBitmap(bitmap);
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
