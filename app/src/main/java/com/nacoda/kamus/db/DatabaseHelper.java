package com.nacoda.kamus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.nacoda.kamus.db.Constants.KamusColumns.DESCRIPTION;
import static com.nacoda.kamus.db.Constants.KamusColumns.WORD;
import static com.nacoda.kamus.db.Constants.TABLE_ENGLISH_INDONESIA;
import static com.nacoda.kamus.db.Constants.TABLE_INDONESIA_ENGLISH;

/**
 * Created by dicoding on 12/1/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";

    private static final int DATABASE_VERSION = 1;

    private static String CREATE_TABLE_ENGLISH_INDONESIA = "create table " + TABLE_ENGLISH_INDONESIA +
            " (" + _ID + " integer primary key autoincrement, " +
            WORD + " text not null, " +
            DESCRIPTION + " text not null);";
    private static String CREATE_TABLE_INDONESIA_ENGLISH = "create table " + TABLE_INDONESIA_ENGLISH +
            " (" + _ID + " integer primary key autoincrement, " +
            WORD + " text not null, " +
            DESCRIPTION + " text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH_INDONESIA);
        db.execSQL(CREATE_TABLE_INDONESIA_ENGLISH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENGLISH_INDONESIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INDONESIA_ENGLISH);
        onCreate(db);
    }
}
