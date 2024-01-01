package com.example.galleryapp.video;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.galleryapp.databinding.ActivityViewVideoBinding;
import com.example.galleryapp.video.adapter.VideoViewAdapter;
import com.example.galleryapp.video.model.VideoAlbum;

import java.util.ArrayList;

public class ViewVideoActivity extends AppCompatActivity {

    ActivityViewVideoBinding binding;
    ArrayList<VideoAlbum> videoAlbums = new ArrayList<>();


    int albumPosition;
    VideoViewAdapter videoViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        videoAlbums = (ArrayList<VideoAlbum>) getIntent().getSerializableExtra("albumlist1");
        albumPosition = getIntent().getIntExtra("value", 0);
        binding.galleryTotalImages.setText("Total items: " +videoAlbums.get(albumPosition).getAl_videopath().size());
        binding.textView3.setText(videoAlbums.get(albumPosition).getStr_vfolder());

        videoViewAdapter = new VideoViewAdapter(this, videoAlbums, albumPosition);
        binding.listView.setHasFixedSize(true);
        binding.listView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.listView.setAdapter(videoViewAdapter);


    }

    @Override
    public void onBackPressed() {
        finish();
    }
}