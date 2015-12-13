package com.example.karen.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Karen on 13-12-2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable(db);
        loadDummyData(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTable(SQLiteDatabase database) {
        String cmd = "CREATE TABLE " + OfficeContract.LOCATION + " (" +
                OfficeContract.Columns.LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OfficeContract.Columns.LOCATION_NAME + " TEXT NOT NULL," +
                OfficeContract.Columns.LOCATION_ADDRESS + " TEXT NOT NULL," +
                OfficeContract.Columns.LOCATION_CITY + " TEXT NOT NULL," +
                OfficeContract.Columns.LOCATION_PHONE + " TEXT," +
                OfficeContract.Columns.LOCATION_URL_FOTO + " TEXT);";
        database.execSQL(cmd);
    }

    private void loadDummyData(SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(OfficeContract.Columns.LOCATION_NAME, "Den haag 1");
        values.put(OfficeContract.Columns.LOCATION_ADDRESS, "Spui 58, 2658KL");
        values.put(OfficeContract.Columns.LOCATION_CITY, "Den Haag");
        values.put(OfficeContract.Columns.LOCATION_PHONE, "070-3596215");
        values.put(OfficeContract.Columns.LOCATION_URL_FOTO, "/resources/img1");
        database.insert(OfficeContract.LOCATION, null, values);

        values = new ContentValues();
        values.put(OfficeContract.Columns.LOCATION_NAME, "Den haag 2");
        values.put(OfficeContract.Columns.LOCATION_ADDRESS, "Herenstraat 25, 2555HD");
        values.put(OfficeContract.Columns.LOCATION_CITY, "Den Haag");
        values.put(OfficeContract.Columns.LOCATION_PHONE, "070-3596215");
        values.put(OfficeContract.Columns.LOCATION_URL_FOTO, "/resources/img1");
        database.insert(OfficeContract.LOCATION, null, values);

    }

}
