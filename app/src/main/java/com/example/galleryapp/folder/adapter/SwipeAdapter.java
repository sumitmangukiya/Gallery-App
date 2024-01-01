package com.example.galleryapp.folder.adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.galleryapp.R;
import com.example.galleryapp.folder.modal.PhotoAlbum;

import java.util.ArrayList;

public class SwipeAdapter extends PagerAdapter {

    ArrayList<PhotoAlbum> photoAlbums = new ArrayList<>();
    Context context;
    int albumPosition;
    LayoutInflater inflater;
    public ImageView imageview;


    public SwipeAdapter(ArrayList<PhotoAlbum> photoAlbums, Context context, int albumPosition) {
        this.photoAlbums = photoAlbums;
        this.context = context;
        this.albumPosition = albumPosition;
    }

    @Override
    public int getCount() {
        return photoAlbums.get(albumPosition).getAl_imagepath().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.image_pager, container, false);
        imageview = view.findViewById(R.id.myZoomageView);

//        imageview.setImageURI(Uri.parse(photoAlbums.get(albumPosition).getAl_imagepath().get(position)));
        Glide.with(context)
                .load(photoAlbums.get(albumPosition).getAl_imagepath().get(position))
                .into(imageview);
        container.addView(view);
        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}