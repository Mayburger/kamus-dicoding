package com.nacoda.kamus.db;

import android.provider.BaseColumns;

import com.nacoda.kamus.R;

/**
 * Created by dicoding on 10/18/2017.
 */

public class Constants {

    static String TABLE_ENGLISH_INDONESIA = "table_english_indonesia";
    static String TABLE_INDONESIA_ENGLISH = "table_indonesia_english";

    static final class KamusColumns implements BaseColumns {

        static String WORD = "word";
        static String DESCRIPTION = "description";

    }
}
