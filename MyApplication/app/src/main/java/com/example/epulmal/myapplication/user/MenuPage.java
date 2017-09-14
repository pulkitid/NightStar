package com.example.epulmal.myapplication.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.epulmal.myapplication.R;
import com.example.epulmal.myapplication.address.Address;
import com.example.epulmal.myapplication.category.CategoryListing;

public class MenuPage extends Activity  {
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        //Category in session
        session = new Session(getApplicationContext());

        Button buttonRolls = (Button) findViewById(R.id.buttonRolls);
        buttonRolls.setOnClickListener(onClickListener);
        Button buttonChinese = (Button) findViewById(R.id.buttonChinese);
        buttonChinese.setOnClickListener(onClickListener);
        Button buttonPasta = (Button) findViewById(R.id.buttonPasta);
        buttonPasta.setOnClickListener(onClickListener);

        Button addAddress = (Button) findViewById(R.id.buttonAddress);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MenuPage.this,Address.class);
                MenuPage.this.startActivity(mainIntent);
                //MenuPage.this.finish();
            }
        });

        /*Button veg = (Button) findViewById(R.id.buttonRolls);
        veg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setCategory("CategoryListing");
                Intent mainIntent = new Intent(MenuPage.this,CategoryListing.class);
                MenuPage.this.startActivity(mainIntent);
                //MenuPage.this.finish();
            }
        });

        Button nonveg = (Button) findViewById(R.id.buttonChinese);
        nonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MenuPage.this,Chinese.class);
                MenuPage.this.startActivity(mainIntent);
                //MenuPage.this.finish();
            }
        });*/

        //View view = new View(MenuPage.this);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        Intent mainIntent;
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.buttonRolls:
                    Toast.makeText(getApplicationContext(), "Rolls Clicked", Toast.LENGTH_LONG).show();
                    session.setCategory("Rolls");
                    mainIntent = new Intent(MenuPage.this,CategoryListing.class);
                    MenuPage.this.startActivity(mainIntent);
                    break;
                case R.id.buttonChinese:
                    Toast.makeText(getApplicationContext(), "Chinese Clicked", Toast.LENGTH_LONG).show();
                    session.setCategory("Chinese");
                    mainIntent = new Intent(MenuPage.this,CategoryListing.class);
                    MenuPage.this.startActivity(mainIntent);
                    break;
                case R.id.buttonPasta:
                    Toast.makeText(getApplicationContext(), "Pasta Clicked", Toast.LENGTH_LONG).show();
                    session.setCategory("Pasta");
                    mainIntent = new Intent(MenuPage.this,CategoryListing.class);
                    MenuPage.this.startActivity(mainIntent);
                    break;
            }

        }
    };
}
