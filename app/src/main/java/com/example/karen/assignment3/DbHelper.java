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

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTable1(db);
        createTable2(db);
        loadDummyData(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void createTable1(SQLiteDatabase database) {
        String cmd = "CREATE TABLE " + OfficeContract.LOCATION + " (" +
                OfficeContract.ColumnsLocation.LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OfficeContract.ColumnsLocation.LOCATION_NAME + " TEXT NOT NULL," +
                OfficeContract.ColumnsLocation.LOCATION_ADDRESS + " TEXT," +
                OfficeContract.ColumnsLocation.LOCATION_ZIPCODE + " TEXT," +
                OfficeContract.ColumnsLocation.LOCATION_CITY + " TEXT," +
                OfficeContract.ColumnsLocation.LOCATION_PHONE + " TEXT);";
        database.execSQL(cmd);
    }
    private void createTable2(SQLiteDatabase database) {
        String cmd = "CREATE TABLE " + OfficeContract.INFO + " (" +
                OfficeContract.ColumnsInfo.COMPANY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                OfficeContract.ColumnsInfo.COMPANY_NAME + " TEXT NOT NULL," +
                OfficeContract.ColumnsInfo.COMPANY_WEBSITE + " TEXT NOT NULL," +
                OfficeContract.ColumnsInfo.COMPANY_HISTORY + " TEXT," +
                OfficeContract.ColumnsInfo.COMPANY_ADDRESS + " TEXT NOT NULL," +
                OfficeContract.ColumnsInfo.COMPANY_ZIPCODE + " TEXT NOT NULL," +
                OfficeContract.ColumnsInfo.COMPANY_CITY + " TEXT NOT NULL," +
                OfficeContract.ColumnsInfo.COMPANY_PHONE + " TEXT NOT NULL," +
                OfficeContract.ColumnsInfo.URL_FOTO1 + " TEXT," +
                OfficeContract.ColumnsInfo.URL_FOTO2 + " TEXT," +
                OfficeContract.ColumnsInfo.URL_FOTO3 + " TEXT);";
        database.execSQL(cmd);
    }

    private void loadDummyData(SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        values.put(OfficeContract.ColumnsInfo.COMPANY_NAME, "Test Company");
        values.put(OfficeContract.ColumnsInfo.COMPANY_WEBSITE, "www.testcompany.nl");
        values.put(OfficeContract.ColumnsInfo.COMPANY_HISTORY, "HYSTORY JSHDKJHSKKJH" +
                "KHKHHKJFHKDHFKDFHKDJHFKDHFKDJHF" +
                "KHDKFHKDFJHDKJF");
        values.put(OfficeContract.ColumnsInfo.COMPANY_ADDRESS, "Stationweg 415");
        values.put(OfficeContract.ColumnsInfo.COMPANY_ZIPCODE, "1025 KL");
        values.put(OfficeContract.ColumnsInfo.COMPANY_CITY, "Amsterdam");
        values.put(OfficeContract.ColumnsInfo.COMPANY_PHONE , "070-3596215");
        database.insert(OfficeContract.INFO, null, values);

        values = new ContentValues();
        values.put(OfficeContract.ColumnsLocation.LOCATION_NAME, "Den haag 1");
        values.put(OfficeContract.ColumnsLocation.LOCATION_ADDRESS, "Spui 58");
        values.put(OfficeContract.ColumnsLocation.LOCATION_ZIPCODE, "2658 KL");
        values.put(OfficeContract.ColumnsLocation.LOCATION_CITY, "Den Haag");
        values.put(OfficeContract.ColumnsLocation.LOCATION_PHONE, "070-3596215");
        database.insert(OfficeContract.LOCATION, null, values);

        values = new ContentValues();
        values.put(OfficeContract.ColumnsLocation.LOCATION_NAME, "Den haag 2");
        values.put(OfficeContract.ColumnsLocation.LOCATION_ADDRESS, "Herenstraat 90");
        values.put(OfficeContract.ColumnsLocation.LOCATION_ZIPCODE, "2558 HN");
        values.put(OfficeContract.ColumnsLocation.LOCATION_CITY, "Den Haag");
        values.put(OfficeContract.ColumnsLocation.LOCATION_PHONE, "070-3558588");
        database.insert(OfficeContract.LOCATION, null, values);


    }

}
