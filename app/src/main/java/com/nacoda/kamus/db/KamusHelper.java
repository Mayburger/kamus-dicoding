package com.nacoda.kamus.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.nacoda.kamus.model.Kamus;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.nacoda.kamus.db.Constants.KamusColumns.DESCRIPTION;
import static com.nacoda.kamus.db.Constants.KamusColumns.WORD;
import static com.nacoda.kamus.db.Constants.TABLE_ENGLISH_INDONESIA;
import static com.nacoda.kamus.db.Constants.TABLE_INDONESIA_ENGLISH;

/**
 * Created by dicoding on 12/1/2016.
 */

public class KamusHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    public ArrayList<Kamus> getDataByName(String word, String type) {
        String result = "";

        Cursor cursor;

        if (type.equals(TABLE_ENGLISH_INDONESIA)) {
            cursor = database.query(TABLE_ENGLISH_INDONESIA, null, WORD + " LIKE ?", new String[]{"%" + word + "%"}, null, null, null);
        } else if (type.equals(TABLE_INDONESIA_ENGLISH)) {
            cursor = database.query(TABLE_INDONESIA_ENGLISH, null, WORD + " LIKE ?", new String[]{"%" + word + "%"}, null, null, null);
        } else {
            cursor = null;
        }

        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus mahasiswaModel;
        if (cursor.getCount() > 0) {
            do {
                mahasiswaModel = new Kamus();
                mahasiswaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                mahasiswaModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                mahasiswaModel.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));

                arrayList.add(mahasiswaModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public ArrayList<Kamus> getAllData(String type) {

        Cursor cursor;

        if (type.equals(TABLE_INDONESIA_ENGLISH)) {
            cursor = database.query(TABLE_INDONESIA_ENGLISH, null, null, null, null, null, _ID + " ASC", null);
        } else if (type.equals(TABLE_ENGLISH_INDONESIA)) {
            cursor = database.query(TABLE_ENGLISH_INDONESIA, null, null, null, null, null, _ID + " ASC", null);
        } else {
            cursor = null;
        }

        assert cursor != null;
        cursor.moveToFirst();
        ArrayList<Kamus> arrayList = new ArrayList<>();
        Kamus kamus;
        if (cursor.getCount() > 0) {
            do {
                kamus = new Kamus();
                kamus.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamus.setWord(cursor.getString(cursor.getColumnIndexOrThrow(WORD)));
                kamus.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));


                arrayList.add(kamus);
                cursor.moveToNext();


            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public void insertIndonesiaEnglish(Kamus kamus) {
        String sql = "INSERT INTO " + TABLE_INDONESIA_ENGLISH + " (" + WORD + ", " + DESCRIPTION
                + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamus.getWord());
        stmt.bindString(2, kamus.getDescription());
        stmt.execute();
        stmt.clearBindings();
    }

    public void insertEnglishIndonesia(Kamus kamus) {
        String sql = "INSERT INTO " + TABLE_ENGLISH_INDONESIA + " (" + WORD + ", " + DESCRIPTION
                + ") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamus.getWord());
        stmt.bindString(2, kamus.getDescription());
        stmt.execute();
        stmt.clearBindings();
    }
}
