package com.example.galleryapp.video;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.galleryapp.databinding.ActivityVideoBinding;
import com.example.galleryapp.video.adapter.VideoAlbumAdapter;
import com.example.galleryapp.video.model.VideoAlbum;

import java.util.ArrayList;

public class VideoActivity extends AppCompatActivity {

    ActivityVideoBinding binding;
    public static ArrayList<VideoAlbum> folderList = new ArrayList<>();

    boolean booleanFolder;
    VideoAlbumAdapter videoAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        albumVideo();

    }

    public void albumVideo() {

        folderList.clear();

        int int_position = 1;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Video.Media.BUCKET_DISPLAY_NAME};

        final String orderBy = MediaStore.Video.Media.DATE_TAKEN;
        cursor = this.getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME);

        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            for (int i = 0; i < folderList.size(); i++) {
                if (folderList.get(i).getStr_vfolder() != null && cursor.getString(column_index_folder_name) != null) {
                    if (folderList.get(i).getStr_vfolder().equals(cursor.getString(column_index_folder_name))) {
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

                al_path.addAll(folderList.get(int_position).getAl_videopath());
                al_path.add(absolutePathOfImage);
                folderList.get(int_position).setAl_videopath(al_path);

                binding.galleryTotalImages.setText("Total items: " + folderList.size());
            } else {
                al_path.add(absolutePathOfImage);
                VideoAlbum obj_model = new VideoAlbum();
                obj_model.setStr_vfolder(cursor.getString(column_index_folder_name));
                obj_model.setAl_videopath(al_path);

                folderList.add(obj_model);

                binding.galleryTotalImages.setText("Total items: " + folderList.size());
            }
        }


        if (folderList.size() == 0) {
        } else {

            binding.galleryRecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
            binding.galleryRecyclerview.setHasFixedSize(true);
            videoAlbumAdapter = new VideoAlbumAdapter(this, folderList);
            binding.galleryRecyclerview.setAdapter(videoAlbumAdapter);
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}