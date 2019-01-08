package com.ehb.masar.goldapplicatie.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ehb.masar.goldapplicatie.database.DatabaseContract.ProductEntry;
import com.ehb.masar.goldapplicatie.database.DatabaseContract.OrderEntry;
import com.ehb.masar.goldapplicatie.database.DatabaseContract.RatingEntry;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database
    public static final String DATABASE_NAME = "Shop.db";
    //databaseVersion
    private static final int DATABASE_VERSION = 1;
    //type
    private static final String TYPE_TEXT = " TEXT";
    private static final String TYPE_INTEGER = " INTEGER";
    private static final String TYPE_DATE = "DATE";
    private static final String TYPE_FLOAT = "FLOAT";

    //Tabel Product maken

    private static final String SQL_CREATE_PRODUCT = " CREATE TABLE " + ProductEntry.TABLE_PRODUCT + "("
            + ProductEntry._ID + TYPE_INTEGER + " PRIMARY KEY,"
            + ProductEntry.COL_PRODUCT_NAME + TYPE_TEXT
            + ProductEntry.COL_PRODUCT_DESCRIPTION + TYPE_TEXT
            + ProductEntry.COL_PRODUCT_PRICE + TYPE_INTEGER
            + ProductEntry.COL_PRODUCT_PHOTO + TYPE_TEXT
            + ")";


    //Tabel Order maken

    private static final String SQL_CREATE_ORDER = " CREATE TABLE "+ OrderEntry.TABLE_ORDER + "("
            + OrderEntry._ID + TYPE_INTEGER + " PRIMARY KEY, "
            + OrderEntry.COL_ORDER_TOTALPRICE + TYPE_INTEGER + ","
            + OrderEntry.COL_ORDER_PAYMETHOD + TYPE_TEXT
            + OrderEntry.COL_ORDER_DATE + TYPE_DATE
            + ")";

    private static final String SQL_CREATE_RATING = " CREATE TABLE "+ RatingEntry.TABLE_RATING + "("
           +RatingEntry._ID + TYPE_INTEGER + " PRIMARY KEY, "
            + RatingEntry.COL_RATING_RATING + TYPE_FLOAT + ","
            + RatingEntry.COL_RATING_PRODUCT_ID + TYPE_INTEGER + "," +
            "FOREIGN KEY ("+ RatingEntry.COL_RATING_PRODUCT_ID + ")" +
            "REFERENCES " + ProductEntry.TABLE_PRODUCT + "("+ ProductEntry._ID + ")" +
            ")";


    static final String SQL_DELETE_PRODUCT = "DROP TABLE IF EXISTS " + ProductEntry.TABLE_PRODUCT;
    static final String SQL_DELETE_ORDER = "DROP TABLE IF EXISTS " + OrderEntry.TABLE_ORDER;
    static final String SQL_DELETE_RATING = "DROP TABLE IF EXISTS" + RatingEntry.TABLE_RATING;

    private static DatabaseHelper sInstance;





    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    /**
     * This method ensures that only one DatabaseHelper will exist at a given time
     *
     * @param context The context the MusicDbHelper is called from
     * @return An initialized DataBaseHelper(i.e.: database singleton)
     */

    static synchronized DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCT);
        db.execSQL(SQL_CREATE_ORDER);
        db.execSQL(SQL_CREATE_RATING);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_PRODUCT);
        db.execSQL(SQL_DELETE_ORDER);
        db.execSQL(SQL_DELETE_RATING);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



}
