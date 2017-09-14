package com.example.epulmal.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by epulmal on 9/7/2017.
 */

public class CategoryDataBaseAdapter {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE_CATEGORY = "create table CATEGORY (ID integer primary key autoincrement,CATEGORYNAME text,DISHNAME text,PRICE text);";

    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;

    public CategoryDataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public CategoryDataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public void insertCategoryEntry(String categoryName, String dishName, String price) {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("CATEGORYNAME", categoryName);
        newValues.put("DISHNAME", dishName);
        newValues.put("PRICE", price);

        // Insert the row into your table
        db.insert("CATEGORY", null, newValues);
        Log.i("NightStar", "category value inserted");
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }

    public String getCategoryOutput(String categoryName) {
        Cursor cursor = db.query("CATEGORY", null, " CATEGORYNAME=?", new String[]{categoryName}, null, null, null);
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String categoryOutput = "Category : \n" + cursor.getString(cursor.getColumnIndex("CATEGORYNAME")) + "\n" + cursor.getString(cursor.getColumnIndex("DISHNAME")) + "\n"
                + cursor.getString(cursor.getColumnIndex("PRICE"));
        cursor.close();
        return categoryOutput;
    }

    public ArrayList getAllCategorydetails() {
        Log.i("NightStar ", "getAllCategorydetails");
        Cursor cursor = db.query("CATEGORY", null, null, null, null, null, null);
        ArrayList<String> categoryOutput = new ArrayList<String>();
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            Log.i("NightStar ", "IF");
            cursor.close();
            categoryOutput.add("NOT EXIST");
            return categoryOutput;
        } else {
            try {
                Log.i("NightStar ", "Else try");
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    Log.i("NightStar ", "Inside For");
                    categoryOutput.add(cursor.getString(cursor.getColumnIndex("CATEGORYNAME")));
                }
            } catch (Exception e) {
                Log.i("NightStar", "Exception Cursor");
                e.printStackTrace();
            }
        }

        cursor.close();
        return categoryOutput;
    }

    public ArrayList getAllDishDetails(String categoryName) {
        Cursor cursor = db.query("CATEGORY", null, " CATEGORYNAME=?", new String[]{categoryName}, null, null, null);
        ArrayList<String> dishOutput = new ArrayList<String>();
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            Log.i("NightStar ", "IF");
            cursor.close();
            dishOutput.add("NOT EXIST");
            return dishOutput;
        } else {
            try {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    Log.i("NightStar ", "Inside For");
                    dishOutput.add(cursor.getString(cursor.getColumnIndex("DISHNAME")));
                }
            } catch (Exception e) {
                Log.i("NightStar", "Exception Cursor");
                e.printStackTrace();
            }
        }
        cursor.close();
        return dishOutput;
    }

    public ArrayList getAllPriceDetails(String categoryName) {
        Cursor cursor = db.query("CATEGORY", null, " CATEGORYNAME=?", new String[]{categoryName}, null, null, null);
        ArrayList<String> priceOutput = new ArrayList<String>();
        if (cursor.getCount() < 1) // UserName Not Exist
        {
            Log.i("NightStar ", "IF");
            cursor.close();
            priceOutput.add("NOT EXIST");
            return priceOutput;
        } else {
            try {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    Log.i("NightStar ", "For");
                    priceOutput.add(cursor.getString(cursor.getColumnIndex("PRICE")));
                }
            } catch (Exception e) {
                Log.i("NightStar", "Exception Cursor");
                e.printStackTrace();
            }
        }
        cursor.close();
        return priceOutput;
    }

    public String deleteCategory(String categoryName) {
        String output = null;
        try {
            Log.i("NightStar ", "IF");

            int result = db.delete("CATEGORY", "CATEGORYNAME = ?", new String[]{categoryName});
            if (result < 1) // UserName Not Exist
            {
                return "NOT EXIST";
            } else {
                output = "Result Entries Deleted";
            }
        } catch (Exception e)
        {
            Log.i("NightStar ", "Exception");
        }
        return output;
    }

}
