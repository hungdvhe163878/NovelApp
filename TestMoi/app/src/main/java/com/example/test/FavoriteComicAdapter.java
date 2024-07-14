package com.example.mynovelapp;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
public class FavoriteComicAdapter extends RecyclerView.Adapter<FavoriteComicAdapter.ComicViewHolder> {

    private Context context;
    private List<Comic> favoriteComics;

    public FavoriteComicAdapter(Context context) {
        this.context = context;
        this.favoriteComics = new ArrayList<>();
    }

    public void setData(List<Comic> comics) {
        favoriteComics.clear();
        favoriteComics.addAll(comics);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        Comic comic = favoriteComics.get(position);
        holder.tvTitle.setText(comic.getTitle());

        // Set image from byte array
        byte[] imageBytes = comic.getImg();
        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imgComic.setImageBitmap(bitmap);
        } else {
            // Set default image if no image data available
            holder.imgComic.setImageResource(R.drawable.ic_launcher_background);
        }

        // Handle click event if needed
        holder.itemView.setOnClickListener(v -> {
            // Handle click on favorite comic item, if necessary
        });
    }

    @Override
    public int getItemCount() {
        return favoriteComics.size();
    }

    public static class ComicViewHolder extends RecyclerView.ViewHolder {
        ImageView imgComic;
        TextView tvTitle;

        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            imgComic = itemView.findViewById(R.id.img_favorite_comic);
            tvTitle = itemView.findViewById(R.id.tv_favorite_title);
        }
    }
}