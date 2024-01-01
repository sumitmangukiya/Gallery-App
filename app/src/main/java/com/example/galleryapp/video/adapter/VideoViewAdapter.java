package com.example.galleryapp.video.adapter;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.galleryapp.ImageDetailActivity;
import com.example.galleryapp.R;
import com.example.galleryapp.video.VideoPlayActivity;
import com.example.galleryapp.video.model.VideoAlbum;

import java.util.ArrayList;

public class VideoViewAdapter extends RecyclerView.Adapter<VideoViewAdapter.VideoHolder> {
    Context context;
    ArrayList<VideoAlbum> videoAlbums = new ArrayList<>();

    int int_position;
    final int AdsType = 0, Datatype = 1;

    public VideoViewAdapter(Context context, ArrayList<VideoAlbum> al_videos, int int_position) {
        this.context = context;
        this.videoAlbums = al_videos;
        this.int_position = int_position;
    }




    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_play, parent, false);
        Log.e("TAG", "onCreateViewHolder: " + int_position);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, @SuppressLint("RecyclerView") int position) {


        Glide.with(context).load(videoAlbums.get(int_position).getAl_videopath().get(position)).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("video", videoAlbums.get(int_position).getAl_videopath().get(position));
                context.startActivity(intent);
//                Intent intent = new Intent(context, ImageDetailActivity.class);
////                intent.putExtra("video", videoAlbums.get(int_position).getAl_videopath().get(position));
//                intent.putExtra("id",1);
//                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return videoAlbums.get(int_position).getAl_videopath().size();
    }

    public class VideoHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public VideoHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.idIVImage);

        }
    }
}