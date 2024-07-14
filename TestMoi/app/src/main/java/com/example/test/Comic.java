package com.example.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Comic {

    public  int Id;
    public  String Title;

    public  String Description;

    public String Author;

    public String Detail;

    public String DatePublic;

    public byte[] Img;

    public int Categories;

    public Comic(byte[]  Img, String title) {
        this.Img = Img;
        this.Title = title;
    }

    public Comic(Object o, String title, String imageUrl) {
    }

    public Comic() {
        // Default constructor
    }

    public byte[] getImg() {
        return Img;
    }

    public void setImg(byte[] img) {
        this.Img = img;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getDatePublic() {
        return DatePublic;
    }

    public void setDatePublic(String datePublic) {
        DatePublic = datePublic;
    }



    public int getCategories() {
        return Categories;
    }

    public void setCategories(int categories) {
        Categories = categories;
    }


    public Comic(int id, String title, String description, String author, String detail, String datePublic, byte[] img, int categories) {
        Id = id;
        Title = title;
        Description = description;
        Author = author;
        Detail = detail;
        DatePublic = datePublic;
        Img = img;
        Categories = categories;
    }
}
