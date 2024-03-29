package com.example.rubricafilm;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "db";
    public static final int DATABASE_VERSION = 1;

    public DBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query = "CREATE TABLE films (id INTEGER PRIMARY KEY AUTOINCREMENT , nome TEXT, genere TEXT, anno_prod TEXT, tipo_supp TEXT, locandina TEXT)";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<ContentValues> getMovies() {

        ArrayList<ContentValues> movies = new ArrayList<>();

        try(SQLiteDatabase db = getReadableDatabase()) {

            String query = "SELECT * FROM films";

            Cursor cursor = db.rawQuery(query, null);

            cursor.moveToFirst();

            while(!cursor.isAfterLast()) {

                ContentValues movie = new ContentValues();

                movie.put("id", cursor.getInt(cursor.getColumnIndex("id")));
                movie.put("nome", cursor.getString(cursor.getColumnIndex("nome")));
                movie.put("genere", cursor.getString(cursor.getColumnIndex("genere")));
                movie.put("anno_prod", cursor.getString(cursor.getColumnIndex("anno_prod")));
                movie.put("tipo_supp", cursor.getString(cursor.getColumnIndex("tipo_supp")));
                movie.put("locandina", cursor.getString(cursor.getColumnIndex("locandina")));

                movies.add(movie);

                cursor.moveToNext();
            }
        }

        return movies;

    }

    void insertMovie(ContentValues movie) {

        try(SQLiteDatabase db = getWritableDatabase()) {

            db.insert("films", null, movie);
        }
    }

    void updateMovie(ContentValues movie) {

            try(SQLiteDatabase db = getWritableDatabase()) {

                db.update("films", movie, "id = ?", new String[] {movie.getAsInteger("id").toString()});
            }
    }

    void deleteMovie(ContentValues movie) {

        try(SQLiteDatabase db = getWritableDatabase()) {

            db.delete("films", "id = ?", new String[] {movie.getAsInteger("id").toString()});
        }

    }

    void deleteAllMovies() {

        try(SQLiteDatabase db = getWritableDatabase()) {

            db.delete("films", "", null);
        }
    }
}
