package com.example.galleryapp.video.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.galleryapp.ImageDetailActivity;
import com.example.galleryapp.R;
import com.example.galleryapp.video.VideoPlayActivity;

import java.io.File;
import java.util.ArrayList;

public class AllVideoAdapter extends RecyclerView.Adapter<AllVideoAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<String> images_list;

    public AllVideoAdapter(Context context, ArrayList<String> images_list) {
        this.context = context;
        this.images_list = images_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_play, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        File image_file = new File(images_list.get(position));

        if (image_file.exists()) {
            Glide.with(context).load(image_file).into(holder.image);
        }

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("video", images_list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.idIVImage);
        }
    }
}
