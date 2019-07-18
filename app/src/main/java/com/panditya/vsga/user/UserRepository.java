package com.panditya.vsga.user;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class UserRepository extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String CREATE_TABLE_QUERY_STRING = "CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT);";
    private static SQLiteDatabase db;


    public UserRepository(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY_STRING);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(User user) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("address", user.getAddress());

        long id = db.insert(TABLE_NAME, null, values);

        return id > 0;
    }

    public boolean update(User user) {
        db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("name", user.getName());
        values.put("address", user.getAddress());

        long id = db.update(TABLE_NAME, values, "id = ?", new String[]{user.getId().toString()});

        return id > 0;
    }

    public boolean delete(User user) {
        db = this.getWritableDatabase();
        long id = db.delete(TABLE_NAME, "id = ?", new String[]{user.getId().toString()});

        return id > 0;
    }

    public boolean truncate() {
        db = this.getWritableDatabase();
        db.rawQuery("DELETE FROM " + TABLE_NAME, new String[]{});

        return true;
    }

    public ArrayList<User> getAll(@Nullable String order, @Nullable Integer limit) {
        db = this.getReadableDatabase();
        final ArrayList<User> users = new ArrayList<>();
        String SQL_QUERY = "SELECT * FROM " + TABLE_NAME;

        if (order != null) {
            SQL_QUERY = SQL_QUERY + "  ORDER BY id " + order;
        }

        if (limit != null) {
            SQL_QUERY = SQL_QUERY + " LIMIT " + limit;
        }


        final Cursor cursor = db.rawQuery(SQL_QUERY, new String[]{});

        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String address = cursor.getString(cursor.getColumnIndex("address"));

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setAddress(address);

                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return users;
    }
}
