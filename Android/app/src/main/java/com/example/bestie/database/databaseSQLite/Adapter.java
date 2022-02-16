package com.example.bestie.database.databaseSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class Adapter {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = Adapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private Helper dbHelper;

    private static final String DATABASE_TABLE = "profileData";
    public static final String KEY_ID = "_id";
    public static final String KEY_VAlUE = "value";

    public Adapter(Context context) {
        this.context = context;
    }

    public Adapter open() throws SQLException {
        dbHelper = new Helper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String value) {
        ContentValues values = new ContentValues();
        values.put( KEY_VAlUE, value );
        return values;
    }

    public long createRow(String value) {
        ContentValues initialValues = createContentValues(value);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public boolean updateRow(long rowID, String value) {
        ContentValues updateValues = createContentValues(value);
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "=" + rowID, null) > 0;
    }

    public boolean deleteRow(long rowID) {
        return database.delete(DATABASE_TABLE, KEY_ID + "=" + rowID, null) > 0;
    }

    public Cursor fetchAllValues() {
        return database.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_VAlUE }, null, null, null, null, null);
    }

    public Cursor fetchValuesByFilter(String filter) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] { KEY_ID, KEY_VAlUE },
                KEY_VAlUE + " like '%"+ filter + "%'", null, null, null, null, null);
        return mCursor;
    }

}
