package com.example.karen.assignment3;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by Karen on 13-12-2015.
 */
public class OfficesProvider extends ContentProvider {

    private static final String DB_NAME = "offices.db";
    private static final int DB_SCHEME_VERSION = 1;

    private DbHelper databaseHelper;

    @Override
    public boolean onCreate() {
        databaseHelper = new DbHelper(getContext(), DB_NAME, null, DB_SCHEME_VERSION);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        int match = OfficeContract.URI_MATCHER.match(uri);

        Cursor c;

        switch (match) {
            case OfficeContract.ALL_ROWS:

                c = db.query(OfficeContract.LOCATION, projection,
                        selection, selectionArgs,
                        null, null, sortOrder);
                c.setNotificationUri(
                        getContext().getContentResolver(),
                        OfficeContract.CONTENT_URI);
                break;
            case OfficeContract.SINGLE_ROW:

                long idLocation = ContentUris.parseId(uri);
                c = db.query(OfficeContract.LOCATION, projection,
                        OfficeContract.Columns._ID + " = " + idLocation,
                        selectionArgs, null, null, sortOrder);
                c.setNotificationUri(
                        getContext().getContentResolver(),
                        OfficeContract.CONTENT_URI);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        return c;
    }

    @Override
    public String getType(Uri uri) {
        switch (OfficeContract.URI_MATCHER.match(uri)) {
            //---get all offices---
            case OfficeContract.ALL_ROWS:
                return OfficeContract.MULTIPLE_MIME;

            //---get a particular office---
            case OfficeContract.SINGLE_ROW:
                return OfficeContract.SINGLE_MIME;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (OfficeContract.URI_MATCHER.match(uri) != OfficeContract.ALL_ROWS) {
            throw new IllegalArgumentException("Unsupported URI : " + uri);
        }
        ContentValues contentValues;
        if (values != null) {
            contentValues = new ContentValues(values);
        } else {
            contentValues = new ContentValues();
        }

        // If necessary, check values
        // Inserting new row
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long rowId = db.insert(OfficeContract.LOCATION,
                null, contentValues);
        if (rowId > 0) {
            Uri uri_location =
                    ContentUris.withAppendedId(
                            OfficeContract.CONTENT_URI, rowId);
            getContext().getContentResolver().
                    notifyChange(uri_location, null);
            return uri_location;
        }
        throw new SQLException("Failure to insert row in : " + uri);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        int match = OfficeContract.URI_MATCHER.match(uri);
        int affected;

        switch (match) {
            case OfficeContract.ALL_ROWS:
                affected = db.delete(OfficeContract.LOCATION,
                        selection,
                        selectionArgs);
                break;
            case OfficeContract.SINGLE_ROW:
                long idLocation = ContentUris.parseId(uri);
                affected = db.delete(OfficeContract.LOCATION,
                        OfficeContract.Columns._ID + "=" + idLocation
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                // Report change associated with uri
                getContext().getContentResolver().
                        notifyChange(uri, null);
                break;
            default:
                throw new IllegalArgumentException("Location unknown: " +
                        uri);
        }
        return affected;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int affected;
        switch (OfficeContract.URI_MATCHER.match(uri)) {
            case OfficeContract.ALL_ROWS:
                affected = db.update(OfficeContract.LOCATION, values,
                        selection, selectionArgs);
                break;
            case OfficeContract.SINGLE_ROW:
                String idLocation = uri.getPathSegments().get(1);
                affected = db.update(OfficeContract.LOCATION, values,
                        OfficeContract.Columns._ID + "=" + idLocation
                                + (!TextUtils.isEmpty(selection) ?
                                " AND (" + selection + ')' : ""),
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return affected;
    }
}
