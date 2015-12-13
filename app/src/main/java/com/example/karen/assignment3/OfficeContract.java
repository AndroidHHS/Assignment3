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
    public static final String INFO = "info";

    public static final Uri CONTENT_URI_INFO =
            Uri.parse("content://" + AUTHORITY + "/" + INFO);
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + LOCATION);


    // helper constants for use with the UriMatcher
    public static final int ALL_ROWS = 1;
    public static final int SINGLE_ROW = 2;
    public static final int ALL_ROWS_INFO = 5;
    public static final int SINGLE_ROW_INFO = 6;
    public static final UriMatcher URI_MATCHER;

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, LOCATION, ALL_ROWS);
        URI_MATCHER.addURI(AUTHORITY, LOCATION + "/#", SINGLE_ROW);
        URI_MATCHER.addURI(AUTHORITY, INFO, ALL_ROWS_INFO);
        URI_MATCHER.addURI(AUTHORITY, INFO + "/#", SINGLE_ROW_INFO);

    }

    public static class ColumnsLocation implements BaseColumns {

        private ColumnsLocation() {
        }

        public static final String LOCATION_ID = "_id";
        public static final String LOCATION_NAME = "locationName";
        public static final String LOCATION_ADDRESS = "locationAddress";
        public static final String LOCATION_ZIPCODE = "locationZipCode";
        public static final String LOCATION_CITY = "locationCity";
        public static final String LOCATION_PHONE = "locationPhone";


    }

    public static class ColumnsInfo implements BaseColumns{
        public ColumnsInfo() {
        }
        public static final String COMPANY_ID = "_id";
        public static final String COMPANY_NAME = "companyName";
        public static final String COMPANY_WEBSITE = "companyWebsite";
        public static final String COMPANY_HISTORY = "companyHistory";
        public static final String COMPANY_ADDRESS = "companyAddress";
        public static final String COMPANY_ZIPCODE = "companyZipCode";
        public static final String COMPANY_CITY = "companyCity";
        public static final String COMPANY_PHONE = "companyPhone";
        public static final String URL_FOTO1 = "foto1";
        public static final String URL_FOTO2 = "foto2";
        public static final String URL_FOTO3 = "foto3";
    }


    public final static String SINGLE_MIME =
            "vnd.android.cursor.item/vnd." + AUTHORITY + LOCATION;

    public final static String SINGLE_MIME_INFO =
            "vnd.android.cursor.item/vnd." + AUTHORITY + INFO;

    public final static String MULTIPLE_MIME =
            "vnd.android.cursor.dir/vnd." + AUTHORITY + LOCATION;

    public final static String MULTIPLE_MIME_INFO =
            "vnd.android.cursor.dir/vnd." + AUTHORITY + INFO;


}
