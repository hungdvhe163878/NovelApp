package com.example.test;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBContext  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "comicDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // User table
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USER_NAME = "name";
    private static final String COLUMN_USER_EMAIL = "email";
    private static final String COLUMN_USER_PASSWORD = "password";
    private static final String COLUMN_USER_ROLE = "role";

    // Role table
    private static final String TABLE_ROLE = "role";
    private static final String COLUMN_ROLE_ID = "id";
    private static final String COLUMN_ROLE_NAME = "name";

    // FavoriteComic table
    private static final String TABLE_FAVORITE_COMIC = "favoriteComic";
    private static final String COLUMN_FAVORITE_COMIC_IDUSER = "iduser";
    private static final String COLUMN_FAVORITE_COMIC_IDCOMIC = "idComic";

    // Comic table
    private static final String TABLE_COMIC = "comic";
    private static final String COLUMN_COMIC_ID = "id";
    private static final String COLUMN_COMIC_TITLE = "title";
    private static final String COLUMN_COMIC_DESCRIPTION = "description";
    private static final String COLUMN_COMIC_AUTHOR = "author";
    private static final String COLUMN_COMIC_DETAIL = "detail";
    private static final String COLUMN_COMIC_DATEPLUBLIC = "dateplublic";
    private static final String COLUMN_COMIC_IMG = "img";
    private static final String COLUMN_COMIC_CATEGORIES = "categories";

    // Categories table
    private static final String TABLE_CATEGORIES = "categories";
    private static final String COLUMN_CATEGORIES_ID = "id";
    private static final String COLUMN_CATEGORIES_NAME = "name";


    public DBContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public DBContext(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBContext(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DBContext(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }
    private static final String CREATE_TABLE_ROLE = "CREATE TABLE " + TABLE_ROLE + " (" +
            COLUMN_ROLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ROLE_NAME + " TEXT NOT NULL" + ");";

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_USER_NAME + " TEXT NOT NULL, " +
            COLUMN_USER_EMAIL + " TEXT NOT NULL UNIQUE, " +
            COLUMN_USER_PASSWORD + " TEXT NOT NULL, " +
            COLUMN_USER_ROLE + " INTEGER, " +
            "FOREIGN KEY(" + COLUMN_USER_ROLE + ") REFERENCES " + TABLE_ROLE + "(" + COLUMN_ROLE_ID + ")" + ");";

    private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE " + TABLE_CATEGORIES + " (" +
            COLUMN_CATEGORIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CATEGORIES_NAME + " TEXT NOT NULL" + ");";

    private static final String CREATE_TABLE_COMIC="CREATE TABLE " + TABLE_COMIC + "("
            + COLUMN_COMIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_COMIC_TITLE + " TEXT,"
            + COLUMN_COMIC_DESCRIPTION + " TEXT,"
            + COLUMN_COMIC_AUTHOR + " TEXT,"
            + COLUMN_COMIC_DETAIL + " TEXT,"
            + COLUMN_COMIC_DATEPLUBLIC + " TEXT,"
            + COLUMN_COMIC_IMG + " TEXT,"
            + COLUMN_COMIC_CATEGORIES + " INTEGER" + ")";


    private static final String CREATE_TABLE_FAVORITE_COMIC= "CREATE TABLE " + TABLE_FAVORITE_COMIC + "("
            + COLUMN_FAVORITE_COMIC_IDUSER + " INTEGER,"
            + COLUMN_FAVORITE_COMIC_IDCOMIC + " INTEGER,"
            + "PRIMARY KEY (" + COLUMN_FAVORITE_COMIC_IDUSER + ", " + COLUMN_FAVORITE_COMIC_IDCOMIC + ")"
            + ")";



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ROLE);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_CATEGORIES);
        db.execSQL(CREATE_TABLE_COMIC);
        db.execSQL(CREATE_TABLE_FAVORITE_COMIC);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITE_COMIC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMIC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROLE);
        onCreate(db);
    }
}
