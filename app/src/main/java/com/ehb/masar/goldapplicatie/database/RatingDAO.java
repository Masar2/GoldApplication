package com.ehb.masar.goldapplicatie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ehb.masar.goldapplicatie.domain.Product;
import com.ehb.masar.goldapplicatie.domain.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatingDAO {
    private final DatabaseHelper dbHelper;
    private final Context pContext;


    public RatingDAO(Context context) {
        pContext = context;
        dbHelper = DatabaseHelper.getInstance(context);

    }

    //User kan een rating geven
    public Rating addRating(Rating rating) {
        //wij gaan gegevens schrijven naar de database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Product product = rating.getProduct();
        ProductDAO productDAO = new ProductDAO(pContext);
        ContentValues values = new ContentValues();

        values.put(DatabaseContract.RatingEntry.COL_RATING_RATING, rating.getRating());
        //als de product rating nog geen id heeft , geef het een ID
        if (product.getId() == -1) {
            product = productDAO.addProduct(product);
            rating.setProduct(product);
        }

        values.put(DatabaseContract.RatingEntry.COL_RATING_PRODUCT_ID, product.getId());
        long nowRowId = db.insert(DatabaseContract.RatingEntry.TABLE_RATING, null, values);

        rating.setId(nowRowId);
        return rating;
    }

    //getRatingsFor product
    public List<Rating> getRatingForProduct(long id) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ProductDAO productDAO = new ProductDAO(pContext);

        //maken van de sql query ;

        String getRatings = "SELECT * FROM" + DatabaseContract.RatingEntry.TABLE_RATING +
                " WHERE " + DatabaseContract.RatingEntry.COL_RATING_RATING + " = " + id;
        Cursor cursor = db.rawQuery(getRatings, null);
        ArrayList<Rating> ratings = new ArrayList<>();

        if (cursor != null) {

            while (cursor.moveToNext()) {
                /*
                Rating rating = new Rating();
                rating.setId(cursor.getLong(cursor.getColumnIndex(DatabaseContract.RatingEntry._ID)));
                rating.setProduct(productDAO.getProduct(cursor.getLong(cursor.getColumnIndex(DatabaseContract.RatingEntry.COL_RATING_PRODUCT_ID))));
                rating.setRating(cursor.getFloat(cursor.getColumnIndex(DatabaseContract.RatingEntry.COL_RATING_RATING)));
                ratings.add(rating);
                */
                ratings.add(mapCursorToRating(cursor));

            }

            cursor.close();
        }
        return ratings;
    }


    public int getRatingCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String countRating = "SELECT * FROM " + DatabaseContract.RatingEntry.TABLE_RATING;
        Cursor cursor = db.rawQuery(countRating, null);
        cursor.close();
        return cursor.getCount();
    }



    private  Rating mapCursorToRating(Cursor cursor){
        Rating rating = new Rating();
        ProductDAO productDAO = new ProductDAO(pContext);

        int idRating = cursor.getColumnIndex(DatabaseContract.RatingEntry._ID);
        rating.setId(cursor.getLong(idRating));
        rating.setRating(cursor.getFloat(cursor.getColumnIndex(DatabaseContract.RatingEntry.COL_RATING_RATING)));
        rating.setProduct(productDAO.getProduct(cursor.getLong(cursor.getColumnIndex(DatabaseContract.RatingEntry.COL_RATING_PRODUCT_ID))));
        return rating;

    }

    //getRating

    public Rating getRating(long id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String getRating = "SELECT * FROM" + DatabaseContract.RatingEntry.TABLE_RATING +
                "WHERE" + DatabaseContract.ProductEntry._ID + id ;

        Cursor cursor = db.rawQuery(getRating , null);
        Rating rating = null ;
        if( cursor !=  null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            rating = mapCursorToRating(cursor);
            cursor.close();
        }

        return  rating;
    }



}
