package com.example.epulmal.myapplication.category;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.epulmal.myapplication.R;
import com.example.epulmal.myapplication.db.CategoryDataBaseAdapter;
import com.example.epulmal.myapplication.db.LoginDataBaseAdapter;

import java.util.ArrayList;

import static com.example.epulmal.myapplication.R.id.parent;

public class DeleteCategory extends Activity implements AdapterView.OnItemSelectedListener {

    CategoryDataBaseAdapter categoryDataBaseAdapter;
    String selectedCategoryName = null;
    ArrayAdapter<String> dishAdapter;
    ArrayAdapter<String> categoryAdapter;
    ArrayList<String> dishOutput;
    ArrayList<String> categoryOutput;

    Spinner dropdown1;
    Spinner dropdown2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);
        categoryDataBaseAdapter = new CategoryDataBaseAdapter(this);
        categoryDataBaseAdapter=categoryDataBaseAdapter.open();

        Log.i("NightStar", "DeleteCategory");

        setContentView(R.layout.activity_delete_category);

        Button deleteCategory = (Button) findViewById(R.id.buttonDeleteCategory);
        deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("NightStar Button", selectedCategoryName);
                String result = categoryDataBaseAdapter.deleteCategory(selectedCategoryName);
                Log.i("NightStar Deletion", result);
                Toast.makeText(getApplicationContext(), "Deletion Sucessfully", Toast.LENGTH_LONG).show();

            }
        });

        //Spinner1
        dropdown1 = (Spinner) findViewById(R.id.categorySpinner);
        dropdown1.setOnItemSelectedListener(this);
        categoryOutput =  new ArrayList<String>();
        categoryOutput = categoryDataBaseAdapter.getAllCategorydetails();
        Log.i("NightStar Category Size", String.valueOf(categoryOutput.size()));
        categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, categoryOutput);
        dropdown1.setAdapter(categoryAdapter);

        //Spinner2
        dishOutput = new ArrayList<String>();

}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        Log.i("NightStar onItem", "onItemSelected");
        if(spinner.getId() == R.id.categorySpinner)
        {
            Log.i("NightStar onItem", "Inside IF");
            selectedCategoryName = dropdown1.getSelectedItem().toString();
            Log.i("NightStar selectedItem", selectedCategoryName);
            dropdown2 = (Spinner) findViewById(R.id.dishnameSpinner);
            dishOutput = categoryDataBaseAdapter.getAllDishDetails(selectedCategoryName);
            Log.i("NightStar Dishname", String.valueOf(dishOutput.size()));
            dishAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dishOutput);
            dropdown2.setAdapter(dishAdapter);

        }
        else if(spinner.getId() == R.id.dishnameSpinner)
        {
            Log.i("NightStar onItem", "Else");

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        Log.i("NightStar ", "NothingSelected");
    }



}
