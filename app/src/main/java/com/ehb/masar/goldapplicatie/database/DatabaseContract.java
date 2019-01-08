package com.ehb.masar.goldapplicatie.database;


import android.provider.BaseColumns;

/**
 * Defines names for database tables and colums
 */
final class DatabaseContract {
    /**
     * Private constructor to prevent instantiation
     */
    private DatabaseContract() {

    }


    //tabel producten

    static class ProductEntry implements BaseColumns {

        public static final String TABLE_PRODUCT = "product_table";
        public static final String COL_PRODUCT_NAME = "productName";
        public static final String COL_PRODUCT_DESCRIPTION = "description";
        public static final String COL_PRODUCT_PRICE = "price";
        //public static final String COL_PRODUCT_CATEGORIE_ID = "categorie_id";
        public static final String COL_PRODUCT_PHOTO = "photo";

        private ProductEntry() {
        }

    }

    //tabel bestelingen
    static class OrderEntry implements BaseColumns {

        public static final String TABLE_ORDER = "order_table";
        public static final String COL_ORDER_TOTALPRICE = "totalePrice";
        public static final String COL_ORDER_PAYMETHOD = "paymethod";
       // public static final String COL_ORDER_USERID = "userID";
        //public static final String COL_ORDER__CATEGORIE_ID = "categorie_id";
        public static final String COL_ORDER_DATE = "datum";


        private OrderEntry() {

        }

    }

    //tabel rating
    static class RatingEntry implements BaseColumns {
        public static final String TABLE_RATING = "rating_table";
        public static final String COL_RATING_PRODUCT_ID = "product_id";
        public static final String COL_RATING_RATING = "rating";

        private RatingEntry() {

        }

    }


}
