package com.example.android.inventoryappstage1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.inventoryappstage1.data.InvContract;
import com.example.android.inventoryappstage1.data.InvDbHelper;

import static com.example.android.inventoryappstage1.data.InvContract.InvEntry.TABLE_NAME;
import static com.example.android.inventoryappstage1.data.InvDbHelper.DATABASE_NAME;
import static com.example.android.inventoryappstage1.data.InvDbHelper.DATABASE_VERSION;

public class CatalogActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private InvDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.

        mDbHelper = new InvDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabase();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the database.
     */
    private void displayDatabase() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM inv"
        // to get a Cursor that contains all rows from the it table.
        // Cursor cursor = db.rawQuery("SELECT * FROM " + InvContract.InvEntry.TABLE_NAME, null);

        String[] projection = {
                InvContract.InvEntry.COLUMN_ID,
                InvContract.InvEntry.COLUMN_NAME,
                InvContract.InvEntry.COLUMN_PRICE,
                InvContract.InvEntry.COLUMN_QUANTITY,
                InvContract.InvEntry.COLUMN_SUPPLIER_NAME,
                InvContract.InvEntry.COLUMN_SUPPLIER_PHONE
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        TextView displayView = findViewById(R.id.add_text_view);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The it table contains <number of rows in Cursor> products.
            // _id - name - price - quantity - supplier_name - supplier_phone
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The " + TABLE_NAME + " table from " + DATABASE_NAME +
                    " v. " + DATABASE_VERSION + ", contains " + cursor.getCount() + " entries.\n\n");
            displayView.append("The " + TABLE_NAME + " table columns are:\n");
            displayView.append(InvContract.InvEntry.COLUMN_ID + " - " +
                    InvContract.InvEntry.COLUMN_NAME + " - " +
                    InvContract.InvEntry.COLUMN_PRICE + " - " +
                    InvContract.InvEntry.COLUMN_QUANTITY + " - " +
                    InvContract.InvEntry.COLUMN_SUPPLIER_NAME + " - " +
                    InvContract.InvEntry.COLUMN_SUPPLIER_PHONE + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(InvContract.InvEntry.COLUMN_ID);
            int nameColumnIndex = cursor.getColumnIndex(InvContract.InvEntry.COLUMN_NAME);
            int priceColumnIndex = cursor.getColumnIndex(InvContract.InvEntry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(InvContract.InvEntry.COLUMN_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(InvContract.InvEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneColumnIndex = cursor.getColumnIndex(InvContract.InvEntry.COLUMN_SUPPLIER_PHONE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                float currentPrice = cursor.getFloat(priceColumnIndex);
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentNameSupplier = cursor.getString(supplierNameColumnIndex);
                String currentPhoneSupplier = cursor.getString(supplierPhoneColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" +
                        currentID + " \t " +
                        currentName + " \t " +
                        currentPrice + " \t " +
                        currentQuantity + " \t " +
                        currentNameSupplier + " \t " +
                        currentPhoneSupplier));
            }
        } finally {
            // Close the cursor when I'm done reading from it.
            cursor.close();
        }
    }
}
