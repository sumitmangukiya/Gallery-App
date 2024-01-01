package com.example.galleryapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.example.galleryapp.databinding.ActivityStartBinding;
import com.example.galleryapp.folder.FolderImageActivity;
import com.example.galleryapp.video.AllVideoActivity;
import com.example.galleryapp.video.VideoActivity;
import com.example.galleryapp.whatsapp.WhatsappImageActivity;

public class StartActivity extends AppCompatActivity {
    public static final int STORAGE_PERMISSION_CODE = 1;

    String[] required_permissions = new String[]{
            android.Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_VIDEO,
            Manifest.permission.READ_MEDIA_AUDIO
    };

    boolean is_storage_image_permitted = false;
    boolean is_storage_video_permitted = false;
    boolean is_storage_audio_permitted = false;

    public static final String TAG = "StartActivity";

    ActivityStartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.allphoto.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (!allPermissionResultCheck33()) {
                    requestPermissionStorageImages();
                } else {
                    startActivity(new Intent(StartActivity.this, AllphotoActivity.class));
                }
            } else {
                if (checkPermissions()) {
                    startActivity(new Intent(StartActivity.this, AllphotoActivity.class));
                } else {
                    requestPermissions();
                }
            }
        });
        binding.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (!allPermissionResultCheck33()) {
                        requestPermissionStorageImages();
                    } else {
                        startActivity(new Intent(StartActivity.this, WhatsappImageActivity.class));
                    }
                } else {
                    if (checkPermissions()) {
                        startActivity(new Intent(StartActivity.this, WhatsappImageActivity.class));
                    } else {
                        requestPermissions();
                    }
                }
            }
        });
        binding.folder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (!allPermissionResultCheck33()) {
                        requestPermissionStorageImages();
                    } else {
                        startActivity(new Intent(StartActivity.this, FolderImageActivity.class));
                    }
                } else {
                    if (checkPermissions()) {
                        startActivity(new Intent(StartActivity.this, FolderImageActivity.class));
                    } else {
                        requestPermissions();
                    }
                }
            }
        });
        binding.video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (!allPermissionResultCheck33()) {
                        requestPermissionStorageImages();
                    } else {
                        startActivity(new Intent(StartActivity.this, VideoActivity.class));
                    }
                } else {
                    if (checkPermissions()) {
                        startActivity(new Intent(StartActivity.this, VideoActivity.class));
                    } else {
                        requestPermissions();
                    }
                }
            }
        });
        binding.allvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (!allPermissionResultCheck33()) {
                        requestPermissionStorageImages();
                    } else {
                        startActivity(new Intent(StartActivity.this, AllVideoActivity.class));
                    }
                } else {
                    if (checkPermissions()) {
                        startActivity(new Intent(StartActivity.this, AllVideoActivity.class));
                    } else {
                        requestPermissions();
                    }
                }
            }
        });
    }

    public boolean allPermissionResultCheck33() {
        return is_storage_image_permitted && is_storage_video_permitted && is_storage_audio_permitted;
    }

    //    Todo code for read storage media images starts
    public void requestPermissionStorageImages() {
        if (ContextCompat.checkSelfPermission(StartActivity.this, required_permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, required_permissions[0] + " Granted");
            is_storage_image_permitted = true;
            if (!allPermissionResultCheck33()) {
                requestPermissionStorageVideos();
            }
        } else {
            request_permission_launcher_storage_images.launch(required_permissions[0]);
        }

    }


//    Todo code for read storage media Video starts

    private void requestPermissionStorageVideos() {
        if (ContextCompat.checkSelfPermission(StartActivity.this, required_permissions[1]) == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, required_permissions[1] + " Granted");
            is_storage_video_permitted = true;
            if (!allPermissionResultCheck33()) {
                requestPermissionStorageAudio();
            }
        } else {
            request_permission_launcher_storage_video.launch(required_permissions[1]);
        }

    }

    //    Todo code for read storage media Audio starts

    private void requestPermissionStorageAudio() {
        if (ContextCompat.checkSelfPermission(StartActivity.this, required_permissions[2]) == PackageManager.PERMISSION_GRANTED) {
            Log.e(TAG, required_permissions[2] + " Granted");
            is_storage_audio_permitted = true;
        } else {
            request_permission_launcher_storage_audio.launch(required_permissions[2]);
        }

    }

    private ActivityResultLauncher<String> request_permission_launcher_storage_images = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            Log.e(TAG, required_permissions[0] + " Granted");
            is_storage_image_permitted = true;
        } else {
            Log.e(TAG, required_permissions[0] + " Not Granted");
            is_storage_image_permitted = false;
        }
        if (!allPermissionResultCheck33()) {
            requestPermissionStorageVideos();
        }
    });
    private ActivityResultLauncher<String> request_permission_launcher_storage_video = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            Log.e(TAG, required_permissions[1] + " Granted");
            is_storage_video_permitted = true;
        } else {
            Log.e(TAG, required_permissions[1] + " Not Granted");
            is_storage_video_permitted = false;
        }
        if (!allPermissionResultCheck33()) {
            requestPermissionStorageAudio();
        }
    });
    private ActivityResultLauncher<String> request_permission_launcher_storage_audio = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
        if (isGranted) {
            Log.e(TAG, required_permissions[2] + " Granted");
            is_storage_audio_permitted = true;
        } else {
            Log.e(TAG, required_permissions[2] + " Not Granted");
            is_storage_audio_permitted = false;
            sendToSettingDialog();
        }
    });

    private void sendToSettingDialog() {
        new AlertDialog.Builder(StartActivity.this).setTitle("Alert for Permissions").setCancelable(false).setMessage("Go to Settings for Permission").setPositiveButton("Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
                dialog.dismiss();
            }
        }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        }).show();
    }

    private boolean checkPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int microphonePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        return cameraPermission == PackageManager.PERMISSION_GRANTED && microphonePermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "onRequestPermissionsResult: " + " Permission Granted ");
            } else {
                Log.e(TAG, "onRequestPermissionsResult: " + " Permission Denied ");
            }
        }
    }

}