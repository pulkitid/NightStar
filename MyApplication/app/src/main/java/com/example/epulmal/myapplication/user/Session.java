package com.example.epulmal.myapplication.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by epulmal on 9/7/2017.
 */

public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusename(String usename) {
        prefs.edit().putString("usename", usename).commit();

    }

    public String getusename() {
        String usename = prefs.getString("usename","");
        return usename;
    }

    public void setCategory(String categoryName) {
        prefs.edit().putString("category", categoryName).commit();

    }

    public String getCategory() {
        String usename = prefs.getString("category","");
        return usename;
    }

    /*public void setOrderMap(HashMap map) {

        prefs.edit().  putString("item",item ).commit();

    }*/

    /*public Cart getCart() {
        usename = prefs. get  ("category","");
        return usename;
    }*/
}
