package com.example.epulmal.myapplication.user;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;

import android.net.Uri;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.epulmal.myapplication.R;
import com.example.epulmal.myapplication.db.LoginDataBaseAdapter;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    EditText username = null;
    EditText password = null;
    LoginDataBaseAdapter loginDataBaseAdapter;
    private Session session;//global variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        Button signin = (Button) findViewById(R.id.buttonSignIn);
        signin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                username = (EditText) findViewById(R.id.editTextUserName);
                password = (EditText) findViewById(R.id.editTextPassword);

                String userName = username.getText().toString();
                String passWord = password.getText().toString();
                Log.i("NightStar Username", userName );
                Log.i("NightStar Password", passWord);

                //username in session
                session = new Session(getApplicationContext());
                session.setusename(userName);
                Log.i("NightStar sessiondata", session.getusename());
                // fetch the Password form database for respective user name
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);

                Log.i("NightStar dbPassword", storedPassword);
                // check if the Stored password matches with  Password entered by user
                if(passWord.equals(storedPassword))
                {
                    Toast.makeText(getApplicationContext(), "Signin Sucessfully", Toast.LENGTH_LONG).show();
                    if (userName.equals("admin")) {
                        Log.i("NightStar Admin ", userName);
                        Intent mainIntent = new Intent(LoginActivity.this, Admin.class);
                        mainIntent.putExtra("EXTRA_SESSION_ID", storedPassword);
                        LoginActivity.this.startActivity(mainIntent);
                        //LoginActivity.this.finish();
                    } else {
                        Intent mainIntent = new Intent(LoginActivity.this, MenuPage.class);
                        mainIntent.putExtra("EXTRA_SESSION_ID", storedPassword);
                        LoginActivity.this.startActivity(mainIntent);
                        //LoginActivity.this.finish();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "User Name or Password does not match", Toast.LENGTH_LONG).show();

                }
            }
        });

        Button signup = (Button) findViewById(R.id.buttonSignUp);
        signup.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                username = (EditText) findViewById(R.id.editTextUserName);
                password = (EditText) findViewById(R.id.editTextPassword);

                Log.i("NightStar Username", username.getText().toString());
                Log.i("NightStar Password", password.getText().toString());

                String userName = username.getText().toString();
                String passWord =  password.getText().toString();
                try {
                    loginDataBaseAdapter.insertEntry(userName, passWord);
                    Log.i("NightStar Before", "SMS");
                    Toast.makeText(getApplicationContext(), "Registration Sucessfully", Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                    PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);
                    ActivityCompat.requestPermissions(LoginActivity.this,new String[]{Manifest.permission.SEND_SMS},1);
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage("+918826707775","NighStar","Registered",pi,null);
                    Log.i("NightStar After", "SMS Sent");

                    Toast.makeText(getApplicationContext(), "SMS Sent", Toast.LENGTH_LONG).show();


                    Log.i("NightStar Before", "Email");
                    Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.fromParts(
                            "mailto","pulkitid@gmail.com", null));
                    intent.setType("text/html");
                    intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
                    startActivity(Intent.createChooser(emailIntent, "Send Email"));
                    Log.i("NightStar After" + "", "Email");
                    Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_LONG).show();

                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Registration Unsuccessfully", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                Log.i("NightStar", "SMS Sent");
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Otherwise defer to system default behavior.
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }

    public void sendSMS(Activity pActivity, String pSMSNumber, String pSMSText ) {
        try {
            Log.i("NightStar sendSMS", "startSMS");
            Uri uri = Uri.parse("smsto:" + pSMSNumber);
            Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
            intent.putExtra("sms_body", pSMSText);
            pActivity.startActivity(intent);
            Log.i("NightStar sendSMS", "endSMS");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

