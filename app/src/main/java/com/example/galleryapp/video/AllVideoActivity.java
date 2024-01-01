package com.example.galleryapp.video;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.os.Environment.MEDIA_MOUNTED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.TextView;
import android.widget.Toast;

import com.example.galleryapp.AllphotoActivity;
import com.example.galleryapp.R;
import com.example.galleryapp.adapter.GalleryAdapter;
import com.example.galleryapp.video.adapter.AllVideoAdapter;

import java.util.ArrayList;

public class AllVideoActivity extends AppCompatActivity {

    static final int PERMISSION_REQUEST_CODE = 100;
    RecyclerView recycler;
    AllVideoAdapter adapter;
    ArrayList<String> image_list;
    TextView totalimages;
    GridLayoutManager grid_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_video);

        recycler = findViewById(R.id.gallery_recyclerview);
        image_list = new ArrayList<>();
        totalimages = findViewById(R.id.gallery_total_images);

        adapter = new AllVideoAdapter(AllVideoActivity.this, image_list);
        grid_manager = new GridLayoutManager(AllVideoActivity.this, 3);

        recycler.setAdapter(adapter);
        recycler.setLayoutManager(grid_manager);

        checkPermission();
    }

    private void checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        if (result == 0) {
            getImage();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private void getImage() {
        boolean SDCard = Environment.getExternalStorageState().equals(MEDIA_MOUNTED);
        if (SDCard) {
            final String[] columns = {MediaStore.Video.Media.DATA, MediaStore.Video.Media._ID};
            final String orderBy = MediaStore.Video.Media.DATE_TAKEN + " DESC";

            Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
            int count = cursor.getCount();
            totalimages.setText("Total items: " + count);

            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                int columnindex = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
                image_list.add(cursor.getString(columnindex));
            }
            recycler.getAdapter().notifyDataSetChanged();
            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                if (accepted) {
                    getImage();
                } else {
                    Toast.makeText(this, "You have denied the storage permissions.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}