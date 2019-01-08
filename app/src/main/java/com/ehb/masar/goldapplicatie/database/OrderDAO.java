package com.ehb.masar.goldapplicatie.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ehb.masar.goldapplicatie.domain.Order;

public class OrderDAO {

    private final DatabaseHelper dbHelper;
    private final Context pContext;

    public OrderDAO(Context context) {
        pContext = context;
        dbHelper = DatabaseHelper.getInstance(context);

    }


    //een bestelling toevoegen
    public Order addOrder(Order order) {
        //wij gaan gegevens schrijven naar de database
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.OrderEntry.COL_ORDER_TOTALPRICE, order.getTotalePrice());
        values.put(DatabaseContract.OrderEntry.COL_ORDER_PAYMETHOD, order.getPayMethod());
        values.put(DatabaseContract.OrderEntry.COL_ORDER_DATE, order.getOrder_date());

        //wij gaan de values ingeven aan de tabel

        long newRowId = db.insert(DatabaseContract.OrderEntry.TABLE_ORDER, null, values);
        order.setId(newRowId);
        return order;

    }


    //get Allebestelingen


    //delete Bestelingen


}
