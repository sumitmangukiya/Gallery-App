package com.example.galleryapp.adapter;

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

import java.io.File;
import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<String> images_list;

    public GalleryAdapter(Context context, ArrayList<String> images_list) {
        this.context = context;
        this.images_list = images_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
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
                Intent intent = new Intent(context, ImageDetailActivity.class);
                intent.putExtra("image_file", images_list.get(position));
                intent.putExtra("id",0);
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
