package com.keerthi.simpragma;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.keerthi.simpragma.Activities.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 2000; //in milliseconds i.e 2sec
    ImageView ivlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ivlogo = (ImageView) findViewById(R.id.activity_splash_logo);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. The timer is configured
             * right now we are showing Company logo and app name
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
