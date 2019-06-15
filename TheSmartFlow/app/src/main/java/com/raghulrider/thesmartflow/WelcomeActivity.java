package com.raghulrider.thesmartflow;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class WelcomeActivity extends AppCompatActivity {

    LinearLayout topwelcome,bottomwelcome;
    private static int SPLASH_TIME_OUT = 4000;
    ActionBar actionBar;
    Animation uptodown,downtoup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        actionBar = getSupportActionBar();
        actionBar.hide();
        topwelcome = findViewById(R.id.topwelcome);
        bottomwelcome = findViewById(R.id.bottomwelcome);
        uptodown = AnimationUtils.loadAnimation(this,R.anim.uptodown);
        topwelcome.setAnimation(uptodown);
        downtoup =AnimationUtils.loadAnimation(this,R.anim.downtoup);
        bottomwelcome.setAnimation(downtoup);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
