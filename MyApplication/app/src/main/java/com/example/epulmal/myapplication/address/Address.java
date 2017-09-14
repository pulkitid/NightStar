package com.example.epulmal.myapplication.address;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epulmal.myapplication.db.AdminDataBaseAdapter;
import com.example.epulmal.myapplication.db.LoginDataBaseAdapter;
import com.example.epulmal.myapplication.R;
import com.example.epulmal.myapplication.user.Session;

public class Address extends Activity {

    EditText add1 = null;
    EditText add2 = null;
    EditText city = null;
    EditText pincode = null;
    AdminDataBaseAdapter adminnDataBaseAdapter;

    private Session session;//global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        adminnDataBaseAdapter = new AdminDataBaseAdapter(this);
        adminnDataBaseAdapter=adminnDataBaseAdapter.open();

        add1 = (EditText) findViewById(R.id.editTextAdd1);
        add2 = (EditText) findViewById(R.id.editTextAdd2);
        city = (EditText) findViewById(R.id.editTextCity);
        pincode = (EditText) findViewById(R.id.editTextPincode);

        Button signin = (Button) findViewById(R.id.buttonAddAddress);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String address1 = add1.getText().toString();
                String address2 = add2.getText().toString();
                String cityname = city.getText().toString();
                String pinCode = pincode.getText().toString();

                Log.i("NightStar Add1", address1);
                Log.i("NightStar Add2", address2);
                Log.i("NightStar City", cityname);
                Log.i("NightStar Pincode", pinCode);

                //session = new Session(Address.this);
                //Log.i("SigninAdd sessiondata", session.getusename());

                //String username = session.getusename();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String username = prefs.getString("usename","");
                Log.i("NightStar prefdata", username);

                try {
                    adminnDataBaseAdapter.insertAddressEntry(username ,address1, address2, cityname, pinCode);
                    Toast.makeText(getApplicationContext(), "New Address Added Sucessfully", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Missing information, Please re-enter", Toast.LENGTH_LONG).show();
                }

                String addressOutput=adminnDataBaseAdapter.getAddressOutput(username);
                Log.i("NightStar addressOutput", addressOutput);
                EditText address = (EditText) findViewById(R.id.AddressOutput);
                address.setText(addressOutput);
                address.setEnabled(false);
            }
        });
    }
}
