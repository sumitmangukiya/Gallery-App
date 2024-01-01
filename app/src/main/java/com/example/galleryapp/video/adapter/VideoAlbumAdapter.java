package com.example.galleryapp.video.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.galleryapp.R;
import com.example.galleryapp.video.ViewVideoActivity;
import com.example.galleryapp.video.model.VideoAlbum;


import java.util.ArrayList;

public class VideoAlbumAdapter extends RecyclerView.Adapter<VideoAlbumAdapter.AlbumHolder> {

    Context context;
    ArrayList<VideoAlbum> videoAlbums;

    public VideoAlbumAdapter(Context context, ArrayList<VideoAlbum> al_menu) {
        this.context = context;
        this.videoAlbums = al_menu;
    }


    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_folder, parent, false);
        return new AlbumHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        holder.folderName.setText(videoAlbums.get(position).getStr_vfolder());
        holder.folderSize.setText(videoAlbums.get(position).getAl_videopath().size() + " Files");
        Glide.with(context).load(videoAlbums.get(position).getAl_videopath().get(0)).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewVideoActivity.class);
                intent.putExtra("albumlist1", videoAlbums);
                intent.putExtra("value", position);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return videoAlbums.size();
    }

    public class AlbumHolder extends RecyclerView.ViewHolder {
        TextView folderSize, folderName;
        ImageView imageView;

        public AlbumHolder(@NonNull View itemView) {
            super(itemView);

            folderSize = itemView.findViewById(R.id.folder_size);
            folderName = itemView.findViewById(R.id.folder_name);
            imageView = itemView.findViewById(R.id.image);



        }
    }
}