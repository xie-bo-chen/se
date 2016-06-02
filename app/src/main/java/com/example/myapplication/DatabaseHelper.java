package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static int version= 1;
    static String db_name = "database_name";
    static String SQLex =  "CREATE TABLE IF NOT EXISTS table_name ( _id integer primary key not null, " +
                            "date text, base text, name text, age text, height)";
    static String SQL_setting = "CREATE TABLE IF NOT EXISTS personal_table ( " +
            " name NOT NULL, age NOT NULL, height NOT NULL, weight NOT NULL, _id integer primary key not null )";

    public DatabaseHelper(Context context) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLex);
        db.execSQL(SQL_setting);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SQLex");
        db.execSQL("DROP TABLE IF EXISTS SQL_setting");
        onCreate(db);
    }
}