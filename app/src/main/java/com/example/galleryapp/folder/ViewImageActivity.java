package com.example.galleryapp.folder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.galleryapp.R;
import com.example.galleryapp.databinding.ActivityViewImageBinding;
import com.example.galleryapp.folder.adapter.AlbumPhotoAdapter;
import com.example.galleryapp.folder.modal.PhotoAlbum;

import java.util.ArrayList;

public class ViewImageActivity extends AppCompatActivity {

    ActivityViewImageBinding binding;
    AlbumPhotoAdapter albumPhotosAdapter;
    int int_position;
    ArrayList<PhotoAlbum> albumList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewImageBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());


        int_position = getIntent().getIntExtra("value", 0);
        albumList = (ArrayList<PhotoAlbum>) getIntent().getSerializableExtra("list");


        PhotoAlbum photoAlbum =(PhotoAlbum)getIntent().getSerializableExtra("obj");
        binding.textView3.setText(photoAlbum.getStr_folder());
        binding.galleryTotalImages.setText("Total items: "+photoAlbum.getAl_imagepath().size());



        albumPhotosAdapter = new AlbumPhotoAdapter(this,albumList,int_position);
        binding.imageRecycler.setHasFixedSize(true);
        binding.imageRecycler.setLayoutManager(new GridLayoutManager(this,3));
        binding.imageRecycler.setAdapter(albumPhotosAdapter);



    }

    @Override
    public void onBackPressed() {
        finish();
    }


}