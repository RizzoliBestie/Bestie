package com.example.bestie.database.databaseSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "states.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table profileData (_id integer primary key autoincrement, value text not null);";

    public Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS profileData");
        onCreate(database);
    }
}
