package com.example.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {

    private Context context;
    private List<Comic> comics;

    public ComicAdapter(Context context) {
        this.context = context;
    }

    public void updateList(List<Comic> newComicsList) {
        if (comics == null) {
            comics = new ArrayList<>();
        }
        comics.clear(); // Clear existing list
        comics.addAll(newComicsList); // Add new data to list
        notifyDataSetChanged(); // Notify RecyclerView that dataset has changed
    }

    @NonNull
    @Override
    public ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comic, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicViewHolder holder, int position) {
        Comic comic = comics.get(position);
        holder.tvName.setText(comic.getTitle());

        // Convert byte array to Bitmap and set to ImageView
        byte[] imageBytes = comic.getImg();
        if (imageBytes != null && imageBytes.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imgProduct.setImageBitmap(bitmap);
        } else {
            // Set a default image if no image data
            holder.imgProduct.setImageResource(R.drawable.img2);
        }

        holder.button1.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("comicId", comic.getId());
            intent.putExtra("title", comic.getTitle());
            intent.putExtra("description", comic.getDescription());
            intent.putExtra("image", comic.getImg());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return comics == null ? 0 : comics.size();
    }
    public void setData(List<Comic> list) {
        this.comics = list;
        notifyDataSetChanged();
    }
    public static class ComicViewHolder extends RecyclerView.ViewHolder {
        private ImageButton imgProduct;
        private TextView tvName;
        private Button button1;

        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.id1);
            tvName = itemView.findViewById(R.id.tv1);
            button1 = itemView.findViewById(R.id.btnDetail);
        }
    }
}
