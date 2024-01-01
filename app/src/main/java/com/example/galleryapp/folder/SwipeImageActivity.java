package com.example.galleryapp.folder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.galleryapp.databinding.ActivitySwipeImageBinding;
import com.example.galleryapp.folder.adapter.SwipeAdapter;
import com.example.galleryapp.folder.modal.PhotoAlbum;

import java.util.ArrayList;

public class SwipeImageActivity extends AppCompatActivity {

    ArrayList<PhotoAlbum>urls=new ArrayList<>();
    ActivitySwipeImageBinding binding;
    int position,albumPosition;
    SwipeAdapter swipeAdapter;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySwipeImageBinding.inflate(getLayoutInflater());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(binding.getRoot());



        sharedPreferences=this.getSharedPreferences("Images",MODE_PRIVATE);
        editor =sharedPreferences.edit();

        Intent intent = this.getIntent();
        urls = (ArrayList<PhotoAlbum>) intent.getSerializableExtra("albumlist");
        albumPosition = intent.getIntExtra("positionalbum", 0);
        position = intent.getIntExtra("position", 0);
        swipeAdapter = new SwipeAdapter(urls, this, albumPosition);
        binding.viewPager1.setAdapter(swipeAdapter);
        binding.viewPager1.setCurrentItem(position);


        binding.imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}