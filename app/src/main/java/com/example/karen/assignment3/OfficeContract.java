package com.example.karen.assignment3;

import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Karen on 13-12-2015.
 */
public class OfficeContract {

    //Content Provider authority to compare.
    public static final String AUTHORITY = "com.example.karen.assignment3contentprovider";
    public static final String LOCATION = "location";

    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + LOCATION);

    // helper constants for use with the UriMatcher
    public static final int ALL_ROWS = 1;
    public static final int SINGLE_ROW = 2;
    public static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, LOCATION, ALL_ROWS);
        URI_MATCHER.addURI(AUTHORITY, LOCATION + "/#", SINGLE_ROW);
    }

    public static class Columns implements BaseColumns {

        private Columns() {
        }

        public static final String LOCATION_ID = "_id";
        public static final String LOCATION_NAME = "name";
        public static final String LOCATION_ADDRESS = "address";
        public static final String LOCATION_CITY = "city";
        public static final String LOCATION_PHONE = "phone";
        public static final String LOCATION_URL_FOTO = "foto";

    }


    public final static String SINGLE_MIME =
            "vnd.android.cursor.item/vnd." + AUTHORITY + LOCATION;

    public final static String MULTIPLE_MIME =
            "vnd.android.cursor.dir/vnd." + AUTHORITY + LOCATION;


}
