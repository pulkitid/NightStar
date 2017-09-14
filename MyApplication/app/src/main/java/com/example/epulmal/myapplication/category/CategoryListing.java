package com.example.epulmal.myapplication.category;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.epulmal.myapplication.R;
import com.example.epulmal.myapplication.db.CategoryDataBaseAdapter;
import com.example.epulmal.myapplication.user.Admin;
import com.example.epulmal.myapplication.user.LoginActivity;
import com.example.epulmal.myapplication.user.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CategoryListing extends Activity {

    CategoryDataBaseAdapter categoryDataBaseAdapter;
    ArrayList<String> dishList;
    ArrayList<String> priceList;
    HashMap<String, EditText> resultMap;
    HashMap<String, String> orderMap;
    private Session session;
    String quatityValue = null;
    String dishValue = null;
    String itemValue = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_listing);

        //final EditText editTextQuantity = new EditText(this);

        //EditText valueQuantityText, editTextQuantity;
        //TextView dishText, priceText;

        categoryDataBaseAdapter = new CategoryDataBaseAdapter(this);
        categoryDataBaseAdapter=categoryDataBaseAdapter.open();

        //Category in session
        session = new Session(getApplicationContext());

        TableLayout listItems = (TableLayout)findViewById(R.id.TableDisplay);
        listItems.setStretchAllColumns(true);
        listItems.bringToFront();
        listItems.setVerticalScrollBarEnabled(true);
        dishList = new ArrayList<String>();
        dishList = categoryDataBaseAdapter.getAllDishDetails(session.getCategory());

        priceList = new ArrayList<String>();
        priceList = categoryDataBaseAdapter.getAllPriceDetails(session.getCategory());

        TableRow headerRow =  new TableRow(this);
        //headerRow.

        TableRow itemTr =  new TableRow(this);
        TextView item = new TextView(this);
        item.setText("Item");
        //item.setTextSize(Float.parseFloat("18dp"));
        item.setTextSize(22);
        item.setTypeface(null, Typeface.BOLD);
        itemTr.addView(item);

        TextView quantity = new TextView(this);
        quantity.setText("Price");
        //quantity.setTextSize(Float.parseFloat("18dp"));
        quantity.setTypeface(null, Typeface.BOLD);
        quantity.setTextSize(22);
        itemTr.addView(quantity);

        TextView price = new TextView(this);
        price.setText("Quantity");
        //price.setTextSize(Float.parseFloat("18dp"));
        price.setTypeface(null, Typeface.BOLD);
        price.setTextSize(20);
        itemTr.addView(price);

        listItems.addView(itemTr);

        String temp =null;
        resultMap= new HashMap<String, EditText>();

        for(int i = 0; i < dishList.size(); i++){
            Log.i("NightStar ", "i = "+i);
            TableRow tr =  new TableRow(this);

            TextView dishText = new TextView(this);
            dishText.setText(dishList.get(i));
            dishText.setTextSize(16);

            TextView priceText = new TextView(this);
            priceText.setText(priceList.get(i));
            priceText.setTextSize(16);

            temp = dishText.getText().toString() +","+ priceText.getText().toString();

            EditText editTextQuantity = new EditText(this);
            editTextQuantity.setText("");
            editTextQuantity.setId(i);
            editTextQuantity.setInputType(2);
            editTextQuantity.setTextSize(16);

            resultMap.put(temp,editTextQuantity);

            tr.addView(dishText);
            tr.addView(priceText);
            tr.addView(editTextQuantity);
            listItems.addView(tr);
        }

        TableRow tr =  new TableRow(this);
        final Button addCart = new Button(this);
        addCart.setId(Integer.parseInt(String.valueOf(10)));
        addCart.setText("Add to Cart");
        addCart.setTextSize(20);
        addCart.setGravity(Gravity.CENTER);
        tr.addView(addCart);
        tr.setGravity(Gravity.CENTER);
        listItems.addView(tr);

        // priceText.getText().toString();

        try {
            //Log.i("NightStar ID -", "");

            Button addToCart = (Button) findViewById(addCart.getId());
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  Iterator it = resultMap.entrySet().iterator();
                    orderMap = new HashMap<String, String>();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        String tempKey = pair.getKey().toString();
                        String tempValue = ((EditText)pair.getValue()).getText().toString();
                        Log.i("NightStar", tempKey + " = " + tempValue);
                        if (tempValue!=null && !tempValue.equals(""))
                        {
                            Log.i("NightStar", tempKey + " = " + tempValue);
                            orderMap.put(tempKey,tempValue);
                        }
                        }
                    Toast.makeText(getApplicationContext(), "OrderMap Ready", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(CategoryListing.this, OrderSummary.class);
                    mainIntent.putExtra("orderSummary",orderMap);
                    CategoryListing.this.startActivity(mainIntent);
                    }

            });
        } catch (Exception e)
        {
            Log.i("NightStar ", "Exception");
        }
    }

}
