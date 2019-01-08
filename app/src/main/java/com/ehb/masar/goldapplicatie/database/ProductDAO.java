package com.ehb.masar.goldapplicatie.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.ehb.masar.goldapplicatie.R;
import com.ehb.masar.goldapplicatie.database.DatabaseContract;
import com.ehb.masar.goldapplicatie.database.DatabaseHelper;
import com.ehb.masar.goldapplicatie.domain.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private final DatabaseHelper dbHelper;
    private final Context pContext;

    public ProductDAO(Context context) {
        pContext = context;
        dbHelper = DatabaseHelper.getInstance(context);

    }

    //Alleen Admin kan producten adden
    public Product addProduct(Product product) {
        //wij gaan gegevens schrijven naar de database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        /*
              + ProductEntry._ID + TYPE_INTEGER + " PRIMARY KEY,"
            + ProductEntry.COL_PRODUCT_NAME + TYPE_TEXT
            + ProductEntry.COL_PRODUCT_DESCRIPTION + TYPE_TEXT
            + ProductEntry.COL_PRODUCT_PRICE + TYPE_INTEGER
            + ProductEntry.COL_PRODUCT_PHOTO + TYPE_TEXT
            + ")";
         */
        values.put(DatabaseContract.ProductEntry.COL_PRODUCT_NAME, product.getProductName());
        values.put(DatabaseContract.ProductEntry.COL_PRODUCT_DESCRIPTION, product.getDescription());
        values.put(DatabaseContract.ProductEntry.COL_PRODUCT_PRICE, product.getPrice());
        values.put(DatabaseContract.ProductEntry.COL_PRODUCT_PHOTO, product.getPhoto());

        //wij gaan de values ingeven aan de tabel

        long newRowId = db.insert(DatabaseContract.ProductEntry.TABLE_PRODUCT, null, values);
        product.setId(newRowId);
        return product;

    }

    private Product mapCursorToProduct(Cursor cursor) {
        Product product = new Product();
        int id = cursor.getColumnIndex(DatabaseContract.ProductEntry._ID);
        product.setId(cursor.getLong(id));
        product.setProductName(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProductEntry.COL_PRODUCT_NAME)));
        product.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseContract.ProductEntry.COL_PRODUCT_DESCRIPTION)));
        product.setPrice(cursor.getInt(cursor.getColumnIndex(DatabaseContract.ProductEntry.COL_PRODUCT_PRICE)));
        product.setPhoto(cursor.getString((cursor.getColumnIndex(DatabaseContract.ProductEntry.COL_PRODUCT_PHOTO))));
        return product;
    }


    //get alle producten
    public List<Product> getAllProducten() {

        //Get alle data via de ReadableDatabase ,
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //aanmaken van select query.
        String getAllProduct = "SELECT * FROM " + DatabaseContract.ProductEntry.TABLE_PRODUCT;
        //Cursor = is an interface provides random read-write access to the result set returned by a database query.
        Cursor cursor = db.rawQuery(getAllProduct, null);
        ArrayList<Product> products = new ArrayList<>();
        if (cursor != null) {
            while ((cursor.moveToNext())) {
                products.add(mapCursorToProduct(cursor));

            }
            cursor.close();
        }

        return products;
    }

    //get één Product

    public Product getProduct(long id){

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String getProduct = "SELECT * FROM" + DatabaseContract.ProductEntry.TABLE_PRODUCT + "WHERE " + DatabaseContract.ProductEntry._ID + "=" +id ;

        Cursor cursor = db.rawQuery(getProduct , null);
        Product product = null ;
        if(cursor != null && cursor.getCount() > 0 ){
            cursor.moveToFirst();
            product = mapCursorToProduct(cursor);
            cursor.close();
        }
        return product;
    }

    //delete product





}



