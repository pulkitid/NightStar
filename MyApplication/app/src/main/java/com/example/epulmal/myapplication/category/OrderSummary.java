package com.example.epulmal.myapplication.category;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.epulmal.myapplication.R;
import com.example.epulmal.myapplication.user.Session;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OrderSummary extends Activity {
    //private Session session;

     HashMap<String, String> finalOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        //username in session
        //session = new Session(getApplicationContext());
        //session.setusename(userName);


        try {
            Intent order = getIntent();
            if (order != null) {
                finalOrder = (HashMap<String, String>) order.getSerializableExtra("orderSummary");
            }
            Iterator it = finalOrder.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Log.i("NightStar Order", pair.getKey() + " = " + pair.getValue());


            }
        } catch (Exception e){
            Log.i("NightStar Order", "Excaption");
            e.printStackTrace();
        }
    }
}
