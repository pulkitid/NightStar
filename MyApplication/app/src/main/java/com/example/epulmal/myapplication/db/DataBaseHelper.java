package com.example.epulmal.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.epulmal.myapplication.db.LoginDataBaseAdapter;

/**
 * Created by epulmal on 9/7/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // Called when no database exists in disk and the helper class needs
    // to create a new one.
    @Override
    public void onCreate(SQLiteDatabase _db)
    {
        _db.execSQL(LoginDataBaseAdapter.DATABASE_CREATE_LOGIN);
        Log.w("NightStar", "LOGIN TABLE CREATED");
        _db.execSQL(CategoryDataBaseAdapter.DATABASE_CREATE_CATEGORY);
        Log.w("NightStar", "ADDRESS TABLE CREATED");
        _db.execSQL(AdminDataBaseAdapter.DATABASE_CREATE_ADDRESS);
        Log.w("NightStar", "CATEGORY TABLE CREATED");
    }
    // Called when there is a database version mismatch meaning that the version
    // of the database on disk needs to be upgraded to the current version.
    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion)
    {
        // Log the version upgrade.
        Log.w("NightStar", "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");

        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + "TEMPLATE");
        // Create a new one.
        onCreate(_db);
    }
}
