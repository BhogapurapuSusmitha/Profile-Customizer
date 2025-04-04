package com.example.profilecustomizer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private Button btnChooseImage;
    private Button btnSaveProfile;
    private ImageView ivChooseImage;
    private static final int PICK_IMAGE_REQUEST=1;
    private Uri imageuri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName=findViewById(R.id.etName);
        btnChooseImage=findViewById(R.id.btnChooseImage);
        btnSaveProfile=findViewById(R.id.btnSaveProfile);
        ivChooseImage=findViewById(R.id.ivChooseImage);
        btnChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });
        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });


    }
    private void openImageChooser(){
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        //Here data is the value of the Directory/location of the image
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null){
            imageuri=data.getData();
            try{
                Bitmap bmp=MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                ivChooseImage.setImageBitmap(bmp);//Display the chosen Image
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private void saveProfile(){
        String name=etName.getText().toString().trim();
        if(name.isEmpty()){
            Toast.makeText(this,"Please Enter your Name",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Your Profile Saved:"+name,Toast.LENGTH_LONG).show();
        }
    }

}