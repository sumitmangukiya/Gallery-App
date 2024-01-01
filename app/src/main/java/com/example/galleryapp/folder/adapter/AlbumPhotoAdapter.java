package com.example.galleryapp.folder.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.galleryapp.R;
import com.example.galleryapp.folder.SwipeImageActivity;
import com.example.galleryapp.folder.modal.PhotoAlbum;

import java.util.ArrayList;

public class AlbumPhotoAdapter extends RecyclerView.Adapter<AlbumPhotoAdapter.AlbumPhotoViewHolder> {
    Context context;
    ArrayList<PhotoAlbum> photoAlbums;
    int intPosition;

    public AlbumPhotoAdapter(Context context, ArrayList<PhotoAlbum> photoAlbums, int intPosition) {
        this.context = context;
        this.photoAlbums = photoAlbums;
        this.intPosition = intPosition;
    }


    @NonNull
    @Override
    public AlbumPhotoAdapter.AlbumPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumPhotoViewHolder(LayoutInflater.from(context).inflate(R.layout.card_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumPhotoAdapter.AlbumPhotoViewHolder holder, int position) {

        Glide.with(context)
                .load(photoAlbums.get(intPosition).getAl_imagepath().get(position))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .centerCrop()
                .priority(Priority.LOW)
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SwipeImageActivity.class).putExtra("positionalbum", intPosition).putExtra("albumlist", photoAlbums).putExtra("position", position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return photoAlbums.get(intPosition).getAl_imagepath().size();
    }

    public class AlbumPhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public AlbumPhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idIVImage);
        }
    }
}
