package com.example.mynovelapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgDetail;
    private TextView tvTitleDetail;
    private TextView tvDescriptionDetail;
    private Button btnFavorite;
    private int userId;
    private int comicId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imgDetail = findViewById(R.id.imgDetail);
        tvTitleDetail = findViewById(R.id.textTitle);
        tvDescriptionDetail = findViewById(R.id.tvDescriptionDetail);
        btnFavorite = findViewById(R.id.btnFavorite);

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle favorite button click
                addToFavorites(userId, comicId);
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            byte[] imageBytes = intent.getByteArrayExtra("image");
            userId = intent.getIntExtra("Id", -1); // Replace with your actual key for userId
            comicId = intent.getIntExtra("Id", -1); // Replace with your actual key for comicId
            if (title != null) {
                tvTitleDetail.setText(title);
            }
            if (description != null) {
                tvDescriptionDetail.setText(description);
            }
            if (imageBytes != null && imageBytes.length > 0) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                imgDetail.setImageBitmap(bitmap);
            }else {
                imgDetail.setImageResource(R.drawable.ic_launcher_background);
            }
        }
    }
    public void addToFavorites(int userId, int comicId) {
        DBContext dbContext = new DBContext(this);
        boolean isSuccess = dbContext.addToFavorite(userId, comicId);

        if (isSuccess) {
            Toast.makeText(this, "Added to favorites!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add to favorites.", Toast.LENGTH_SHORT).show();
        }
    }
}