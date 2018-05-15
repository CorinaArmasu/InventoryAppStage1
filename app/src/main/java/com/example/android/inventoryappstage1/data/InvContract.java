package com.example.android.inventoryappstage1.data;

import android.provider.BaseColumns;

/**
 * Created by corina.armasu on 5/14/2018.
 */

public final class InvContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.

    private InvContract() {
    }

    /**
     * Inner class that defines constant values for the it products database table.
     * Each entry in the table represents a single it product.
     */
    public static final class InvEntry implements BaseColumns {

        // Name of the database table for products
        public static final String TABLE_NAME = "inv";

        /**
         * Unique ID number for the products (only for use in the database table).
         * Type: INTEGER
         */
        public final static String COLUMN_ID = "_id";

        /**
         * Name of the product.
         * Type: TEXT
         */
        public static final String COLUMN_NAME = "name";

        /**
         * Price of the product.
         * Type: REAL
         */
        public static final String COLUMN_PRICE = "price";

        /**
         * Quantity of the product.
         * Type: INTEGER
         */
        public static final String COLUMN_QUANTITY = "quantity";

        /**
         * Name of the product supplier.
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";

        /**
         * Phone of the product supplier.
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";

    }
}
