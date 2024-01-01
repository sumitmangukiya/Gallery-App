package com.example.galleryapp.folder;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.galleryapp.databinding.ActivityFolderImageBinding;
import com.example.galleryapp.folder.adapter.PhotoAlbumAdapter;
import com.example.galleryapp.folder.modal.PhotoAlbum;

import java.util.ArrayList;

public class FolderImageActivity extends AppCompatActivity {

    ActivityFolderImageBinding binding;
    ArrayList<PhotoAlbum> albumList = new ArrayList<>();

    boolean booleanFolder;
    PhotoAlbumAdapter photoAlbumAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFolderImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.empty.setVisibility(View.GONE);


        albumImages();

    }

    @Override
    public void onBackPressed() {
        finish();
    }


    public void albumImages() {
        albumList.clear();


        int int_position = 1;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = this.getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);


            for (int i = 0; i < albumList.size(); i++) {
                if (albumList.get(i).getStr_folder() != null && cursor.getString(column_index_folder_name) != null) {
                    if (albumList.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
                        booleanFolder = true;
                        int_position = i;
                        break;
                    } else {
                        booleanFolder = false;
                    }
                }
            }
            ArrayList<String> al_path = new ArrayList<>();
            if (booleanFolder) {

                al_path.addAll(albumList.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
                albumList.get(int_position).setAl_imagepath(al_path);
                binding.galleryTotalImages.setText("Total items: "+albumList.size());
            } else {
                al_path.add(absolutePathOfImage);
                PhotoAlbum obj_model = new PhotoAlbum();
                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
                obj_model.setAl_imagepath(al_path);
                albumList.add(obj_model);
                binding.galleryTotalImages.setText("Total items: "+albumList.size());

            }
        }

        if (albumList.size() == 0) {
            binding.empty.setVisibility(View.VISIBLE);
        } else {

            binding.listView.setLayoutManager(new GridLayoutManager(this,2));
            binding.listView.setHasFixedSize(true);
            photoAlbumAdapter = new PhotoAlbumAdapter(this, albumList);
            binding.listView.setAdapter(photoAlbumAdapter);
        }

    }
}