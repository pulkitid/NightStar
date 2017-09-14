package com.example.epulmal.myapplication.user;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.epulmal.myapplication.R;

public class MainSplash extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);

       /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainSplash.this,LoginActivity.class);
                MainSplash.this.startActivity(mainIntent);
                MainSplash.this.finish();

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
