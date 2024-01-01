//package com.example.galleryapp.video;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.widget.MediaController;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.example.galleryapp.databinding.ActivityVideoPlayBinding;
//
//
//public class VideoPlayActivity extends AppCompatActivity {
//
//    ActivityVideoPlayBinding binding;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityVideoPlayBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        String path = getIntent().getStringExtra("video");
//
//
//        MediaController mediaController = new MediaController(this, false);
//        binding.videoview.setOnPreparedListener(mp -> {
////            mp.start();
//            mediaController.show(0);
//            mp.setLooping(true);
//        });
//        binding.videoview.setMediaController(mediaController);
//        mediaController.setMediaPlayer(binding.videoview);
//        binding.videoview.requestFocus();
//        binding.videoview.setVideoURI(Uri.parse(path));
//
//    }
//}
package com.example.galleryapp.video;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.galleryapp.R;
import com.example.galleryapp.databinding.ActivityVideoPlayBinding;

public class VideoPlayActivity extends AppCompatActivity {

    ActivityVideoPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String videoPath = getIntent().getStringExtra("video");

        // Load thumbnail using Glide
        Glide.with(this)
                .load(videoPath)
                .into(binding.imagethumbnail);

        // Set onClickListener to play the video
        binding.imagethumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo(videoPath);
                binding.imagethumbnail.setVisibility(View.GONE);
            }
        });
    }

    private void playVideo(String videoPath) {
        MediaController mediaController = new MediaController(this, false);
        binding.videoview.setOnPreparedListener(mp -> {
            mediaController.show(0);
            mp.setLooping(true);
        });
        binding.videoview.setMediaController(mediaController);
        mediaController.setMediaPlayer(binding.videoview);
        binding.videoview.requestFocus();
        binding.videoview.setVideoURI(Uri.parse(videoPath));
        binding.videoview.start(); // Start playing the video
    }
}
