package com.example.epulmal.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by epulmal on 9/7/2017.
 */

public class AdminDataBaseAdapter {

    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
    static final String DATABASE_CREATE_ADDRESS = "create table ADDRESS (ID integer primary key autoincrement,USERNAME text,ADDRESS1  text,ADDRESS2 text,CITY text, PINCODE text);";

    public SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public AdminDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public AdminDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertAddressEntry(String userName,String address1,String address2,String city, String pincode)
    {
        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("ADDRESS1", address1);
        newValues.put("ADDRESS2",address2);
        newValues.put("CITY",city);
        newValues.put("PINCODE",pincode);


        // Insert the row into your table
        db.insert("ADDRESS", null, newValues);
        Log.i("NightStar ","Address value inserted");
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public String getAddressOutput(String userName)
    {
        Cursor cursor=db.query("ADDRESS", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String addressOutput = "Address : \n" + cursor.getString(cursor.getColumnIndex("ADDRESS1")) +"\n" + cursor.getString(cursor.getColumnIndex("ADDRESS2")) +"\n"
        +cursor.getString(cursor.getColumnIndex("CITY")) +"\n"+  cursor.getString(cursor.getColumnIndex("PINCODE"));
        cursor.close();
        return addressOutput;
    }
}
