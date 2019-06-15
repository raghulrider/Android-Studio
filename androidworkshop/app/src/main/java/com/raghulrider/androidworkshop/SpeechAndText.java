package com.raghulrider.androidworkshop;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class SpeechAndText extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_and_text);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        BottomNavigationView navView = findViewById(R.id.nav_view1);
        navView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_stt:
                fragment = new SttFragment();
                Log.d("Debug","Navigation STT");
                break;
            case R.id.navigation_tts:
                fragment  = new TtsFragment();
                Log.d("Debug","Navigation TTS");
                break;
        }
        return loadFragment(fragment);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
