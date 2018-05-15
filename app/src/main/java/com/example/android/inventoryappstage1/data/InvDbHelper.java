package com.example.android.inventoryappstage1.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by corina.armasu on 5/14/2018.
 */

public class InvDbHelper extends SQLiteOpenHelper {

    // The name of the database file
    public static final String DATABASE_NAME = "products.db";

    // The version of the database. If you change the database schema, you must increment the database version
    public static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link InvDbHelper}.
     *
     * @param context of the app
     */
    public InvDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // This is called when the database is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the inv table:

        String SQL_CREATE_INV_TABLE = "CREATE TABLE " + InvContract.InvEntry.TABLE_NAME + " (" +
                InvContract.InvEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                InvContract.InvEntry.COLUMN_NAME + " TEXT NOT NULL," +
                InvContract.InvEntry.COLUMN_PRICE + " REAL NOT NULL," +
                InvContract.InvEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                InvContract.InvEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                InvContract.InvEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_INV_TABLE);
    }

    //This is called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}