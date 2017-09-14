package com.example.epulmal.myapplication.category;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epulmal.myapplication.R;
import com.example.epulmal.myapplication.db.CategoryDataBaseAdapter;
import com.example.epulmal.myapplication.db.LoginDataBaseAdapter;

public class AddCategory extends Activity {

    EditText category = null;
    EditText dish = null;
    EditText price = null;
    EditText categoryResult = null;
    CategoryDataBaseAdapter categoryDataBaseAdapter;
    String categoryName = null;
    String dishName = null;
    String priceValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_main);

        Log.i("NightStar ", "Inside Admin");
        // create a instance of SQLite Database
        categoryDataBaseAdapter = new CategoryDataBaseAdapter(this);
        categoryDataBaseAdapter.open();

        category = (EditText) findViewById(R.id.editTextCategory);
        dish = (EditText) findViewById(R.id.editTextDishname);
        price = (EditText) findViewById(R.id.editTextDishPrice);



        Button submit = (Button) findViewById(R.id.buttonSubmitCategory);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i("NightStar ", "try");
                    categoryName = category.getText().toString();
                    dishName = dish.getText().toString();
                    priceValue = price.getText().toString();

                    Log.i("NightStar categoryName", categoryName);
                    Log.i("NightStar dishName", dishName);
                    Log.i("NightStar priceValue", priceValue);

                    categoryDataBaseAdapter.insertCategoryEntry(categoryName, dishName, priceValue);
                    Log.i("NightStar Category", "InsertCat");
                    Toast.makeText(getApplicationContext(), "Category Created", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.i("NightStar Exception", "Inseration Exception");
                }

                String categoryValue = category.getText().toString();
                Log.i("NightStar categoryValue", categoryValue);
                String categoryOutput=categoryDataBaseAdapter.getCategoryOutput(categoryValue);
                Log.i("NightStar catOutput", categoryOutput);
                categoryResult = (EditText) findViewById(R.id.editTextCategoryOutput);
                categoryResult.setText(categoryOutput);
                categoryResult.setEnabled(false);
            }
        });
    }
}
