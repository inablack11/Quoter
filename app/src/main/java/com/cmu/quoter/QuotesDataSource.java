package com.cmu.quoter;

/**
 * Created by inablack11 on 5/31/2015.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class QuotesDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private String[] allColumns = { SQLiteHelper.COLUMN_ID,
            SQLiteHelper.COLUMN_QUOTE };

    public QuotesDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Quote createQuote(String quote) {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelper.COLUMN_QUOTE, quote);
        long insertId = database.insert(SQLiteHelper.TABLE_QUOTES, null,
                values);
        Cursor cursor = database.query(SQLiteHelper.TABLE_QUOTES,
                allColumns, SQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Quote newQuote = cursorToQuote(cursor);
        cursor.close();
        return newQuote;
    }

    public void deleteQuote(Quote quote) {
        long id = quote.getId();
        System.out.println("Quote deleted with id: " + id);
        database.delete(SQLiteHelper.TABLE_QUOTES, SQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Quote> getAllQuotes() {
        List<Quote> quotes = new ArrayList<Quote>();

        Cursor cursor = database.query(SQLiteHelper.TABLE_QUOTES,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Quote quote = cursorToQuote(cursor);
            quotes.add(quote);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return quotes;
    }

    private Quote cursorToQuote(Cursor cursor) {
        Quote quote = new Quote();
        quote.setId(cursor.getLong(0));
        quote.setQuote(cursor.getString(1));
        return quote;
    }
}
