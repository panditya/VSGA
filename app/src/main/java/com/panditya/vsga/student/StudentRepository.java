package com.panditya.vsga.student;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class StudentRepository extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_db";
    private static final Integer DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String CREATE_TABLE_QUERY_STRING = "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, hobby TEXT, address TEXT);";

    public StudentRepository(Context context) {
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

    public long insert(Student student) {
        final SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("hobby", student.getHobby());
        values.put("address", student.getAddress());

        Log.d("INSERT_STUDENT", student.toString());

        long insert = db.insert(TABLE_NAME, null, values);

        return insert;
    }

    public ArrayList<Student> getAllStudnetsList() {

        final SQLiteDatabase db = this.getReadableDatabase();
        final ArrayList<Student> students = new ArrayList<>();
        final String SQL_QUERY = "SELECT * FROM " + TABLE_NAME;

            final Cursor cursor = db.rawQuery(SQL_QUERY, new String[]{});

            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student(
                            cursor.getInt(cursor.getColumnIndex("id")),
                            cursor.getString(cursor.getColumnIndex("name")),
                            cursor.getString(cursor.getColumnIndex("hobby")),
                            cursor.getString(cursor.getColumnIndex("address"))
                            );

                    students.add(student);
                } while (cursor.moveToNext());
            }

            cursor.close();

            return students;
    }
}
