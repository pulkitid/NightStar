package com.example.epulmal.myapplication.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epulmal.myapplication.R;
import com.example.epulmal.myapplication.category.AddCategory;
import com.example.epulmal.myapplication.category.DeleteCategory;
import com.example.epulmal.myapplication.db.LoginDataBaseAdapter;

public class Admin extends Activity {

    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Log.i("NightStar ", "Inside Admin");
        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter.open();

        Button addCategory = (Button) findViewById(R.id.buttonAddCategory);
        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mainIntent = new Intent(Admin.this,AddCategory.class);
                Admin.this.startActivity(mainIntent);
                //Admin.this.finish();
            }
        });


        Button deleteCategory = (Button) findViewById(R.id.buttonDeleteCategory);
        deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(Admin.this,DeleteCategory.class);
                Admin.this.startActivity(mainIntent);
                //Admin.this.finish();
            }
        });
    }

}
