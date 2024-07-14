//package com.example.test;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.example.test.model.Account;
//
//public class DBContext extends SQLiteOpenHelper {
//
//    private static final String DATABASE_NAME = "comicDatabase.db";
//    private static final int DATABASE_VERSION = 1;
//
//    private static final String TABLE_USER = "user";
//    private static final String COLUMN_USER_ID = "id";
//    private static final String COLUMN_USER_NAME = "name";
//    private static final String COLUMN_USER_EMAIL = "email";
//    private static final String COLUMN_USER_PASSWORD = "password";
//    private static final String COLUMN_USER_ROLE = "role";
//
//    private static final String TABLE_ROLE = "role";
//    private static final String COLUMN_ROLE_ID = "id";
//    private static final String COLUMN_ROLE_NAME = "name";
//
//    private static final String CREATE_TABLE_ROLE = "CREATE TABLE " + TABLE_ROLE + " (" +
//            COLUMN_ROLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_ROLE_NAME + " TEXT NOT NULL" + ");";
//
//    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" +
//            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
//            COLUMN_USER_NAME + " TEXT NOT NULL, " +
//            COLUMN_USER_EMAIL + " TEXT NOT NULL UNIQUE, " +
//            COLUMN_USER_PASSWORD + " TEXT NOT NULL, " +
//            COLUMN_USER_ROLE + " INTEGER, " +
//            "FOREIGN KEY(" + COLUMN_USER_ROLE + ") REFERENCES " + TABLE_ROLE + "(" + COLUMN_ROLE_ID + ")" + ");";
//
//    public DBContext(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(CREATE_TABLE_ROLE);
//        db.execSQL(CREATE_TABLE_USER);
//        db.execSQL("INSERT INTO " + TABLE_ROLE + " (" + COLUMN_ROLE_NAME + ") VALUES ('admin')");
//        db.execSQL("INSERT INTO " + TABLE_ROLE + " (" + COLUMN_ROLE_NAME + ") VALUES ('user')");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROLE);
//        onCreate(db);
//    }
//
//    public boolean AddAccount(Account account) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, account.getmUsername());
//        values.put(COLUMN_USER_PASSWORD, account.getmPass());
//        values.put(COLUMN_USER_EMAIL, account.getmEmail());
//        values.put(COLUMN_USER_ROLE, account.getmRole());
//
//        long result = db.insert(TABLE_USER, null, values);
//        db.close();
//        Log.e("ADD Tk", "TC");
//
//        return result != -1;
//    }
//
//    public Cursor getData() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.rawQuery("SELECT * FROM " + TABLE_USER, null);
//    }
//}



package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.model.Account;

public class DBContext extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "novelApp.db";
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
    public static final String TABLE_COMIC = "comic";
    private static final String COLUMN_COMIC_ID = "id";
    public static final String COLUMN_COMIC_TITLE = "title";
    private static final String COLUMN_COMIC_DESCRIPTION = "description";
    private static final String COLUMN_COMIC_AUTHOR = "author";
    private static final String COLUMN_COMIC_DETAIL = "detail";
    private static final String COLUMN_COMIC_DATEPLUBLIC = "dateplublic";
    public static final String COLUMN_COMIC_IMG = "img";
    private static final String COLUMN_COMIC_CATEGORIES = "categories";

    // Categories table
    private static final String TABLE_CATEGORIES = "categories";
    private static final String COLUMN_CATEGORIES_ID = "id";
    private static final String COLUMN_CATEGORIES_NAME = "name";

    public DBContext(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    private static final String CREATE_TABLE_COMIC = "CREATE TABLE " + TABLE_COMIC + "(" +
            COLUMN_COMIC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_COMIC_TITLE + " TEXT, " +
            COLUMN_COMIC_DESCRIPTION + " TEXT, " +
            COLUMN_COMIC_AUTHOR + " TEXT, " +
            COLUMN_COMIC_DETAIL + " TEXT, " +
            COLUMN_COMIC_DATEPLUBLIC + " TEXT, " +
            COLUMN_COMIC_IMG + " TEXT, " + // Change to BLOB for image storage
            COLUMN_COMIC_CATEGORIES + " INTEGER" + ")";

    private static final String CREATE_TABLE_FAVORITE_COMIC = "CREATE TABLE " + TABLE_FAVORITE_COMIC + "(" +
            COLUMN_FAVORITE_COMIC_IDUSER + " INTEGER, " +
            COLUMN_FAVORITE_COMIC_IDCOMIC + " INTEGER, " +
            "PRIMARY KEY (" + COLUMN_FAVORITE_COMIC_IDUSER + ", " + COLUMN_FAVORITE_COMIC_IDCOMIC + ")" + ")";

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

    public boolean insertComic(String title, String description, String author, String detail, String dateplublic, byte[] img, int categories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_COMIC_TITLE, title);
        contentValues.put(COLUMN_COMIC_DESCRIPTION, description);
        contentValues.put(COLUMN_COMIC_AUTHOR, author);
        contentValues.put(COLUMN_COMIC_DETAIL, detail);
        contentValues.put(COLUMN_COMIC_DATEPLUBLIC, dateplublic);
        contentValues.put(COLUMN_COMIC_IMG, img);
        contentValues.put(COLUMN_COMIC_CATEGORIES, categories);
        long result = db.insert(TABLE_COMIC, null, contentValues);
        return result != -1; // returns false if insert fails
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT " + COLUMN_COMIC_IMG + ", " + COLUMN_COMIC_TITLE + " FROM " + TABLE_COMIC;
        return db.rawQuery(selectQuery, null);
    }
    public Cursor searchComicByTitle(String searchTerm) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {COLUMN_COMIC_TITLE, COLUMN_COMIC_IMG}; // Fetching both title and img
        String selection = COLUMN_COMIC_TITLE + " LIKE ?";
        String[] selectionArgs = {"%" + searchTerm + "%"};
        String sortOrder = COLUMN_COMIC_TITLE + " ASC"; // Example: sorting by title in ascending order
        return db.query(
                TABLE_COMIC,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    public Cursor readDataWithLimitAndOffset(int limit, int offset) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {COLUMN_COMIC_TITLE, COLUMN_COMIC_IMG}; // Fetching both title and img
        String sortOrder = COLUMN_COMIC_TITLE + " ASC"; // Example: sorting by title in ascending order
        String limitOffset = offset + "," + limit; // This forms "OFFSET offset LIMIT limit"
        return db.query(
                TABLE_COMIC,
                projection,
                null,
                null,
                null,
                null,
                sortOrder,
                limitOffset
        );
    }
    private void insertComic(SQLiteDatabase db, String title, String description, String author, String detail, String dateplublic, byte[] img, String categories) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("author", author);
        values.put("detail", detail);
        values.put("dateplublic", dateplublic);
        values.put("img", img);
        values.put("categories", categories);
        db.insert("comic", null, values);
    }

    public boolean AddAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, account.getmUsername());
        values.put(COLUMN_USER_PASSWORD, account.getmPass());
        values.put(COLUMN_USER_EMAIL, account.getmEmail());
        values.put(COLUMN_USER_ROLE, account.getmRole());

        long result = db.insert(TABLE_USER, null, values);
        db.close();
        Log.e("ADD Tk", "TC");

        return result != -1;
    }
    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_USER, null);
    }
}

