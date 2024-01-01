package com.example.galleryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.jsibbold.zoomage.ZoomageView;
import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageDetailActivity extends AppCompatActivity {


    ZoomageView image;
    String image_file;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);


        image_file = getIntent().getStringExtra("image_file");
        File file = new File(image_file);
        image = findViewById(R.id.image);



        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        switch (id) {
            case 0:
                image.setVisibility(View.VISIBLE);
                if (file.exists()) {
                    Glide.with(this).load(image_file).into(image);
                }
                break;

        }


    }
}