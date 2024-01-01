package com.example.galleryapp.folder.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.galleryapp.R;
import com.example.galleryapp.folder.ViewImageActivity;
import com.example.galleryapp.folder.modal.PhotoAlbum;


import java.util.ArrayList;

public class PhotoAlbumAdapter extends RecyclerView.Adapter<PhotoAlbumAdapter.AlbumHolder> {
    Context context;
    ArrayList<PhotoAlbum> photoAlbums = new ArrayList<>();


    public PhotoAlbumAdapter(Context context, ArrayList<PhotoAlbum> photoAlbums) {
        this.context = context;
        this.photoAlbums = photoAlbums;

    }


    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_folder, parent, false);
        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.folderName.setText(photoAlbums.get(position).getStr_folder());
        holder.folderSize.setText(photoAlbums.get(position).getAl_imagepath().size() + "  images");
        Glide.with(context).load(photoAlbums.get(position).getAl_imagepath().get(0)).into(holder.image);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewImageActivity.class);
                intent.putExtra("list", photoAlbums);
                intent.putExtra("value", position);
                intent.putExtra("obj", photoAlbums.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return photoAlbums.size();

    }

    public static class AlbumHolder extends RecyclerView.ViewHolder {
        TextView folderSize, folderName;
        CardView item;
        ImageView image;

        public AlbumHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            image = itemView.findViewById(R.id.image);
            folderSize = itemView.findViewById(R.id.folder_size);
            folderName = itemView.findViewById(R.id.folder_name);
         }
    }
}
