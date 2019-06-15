package com.raghulrider.parker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private TextView help;
    ActionBar actionBar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            Fragment backfragment = null;
            Fragment backfragment1 = null;
            switch (item.getItemId()) {
                case R.id.navigation_area1:
                    help.setVisibility(View.GONE);
                    fragment = new AreaOneFragment();
                    backfragment = new AreaTwoFragment();
                    backfragment1 = new ProfileFragment();
                    break;
                case R.id.navigation_area2:
                    help.setVisibility(View.GONE);
                    fragment = new AreaTwoFragment();
                    backfragment = new ProfileFragment();
                    backfragment1 = new AreaOneFragment();
                    break;
                case R.id.navigation_profile:
                    help.setVisibility(View.GONE);
                    fragment = new ProfileFragment();
                    backfragment = new AreaOneFragment();
                    backfragment1 = new AreaTwoFragment();
                    break;
            }
            return loadFragment(fragment, backfragment, backfragment1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Parker");
        BottomNavigationView navView = findViewById(R.id.nav_view);
        help = findViewById(R.id.helptext);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private boolean loadFragment(Fragment fragment, Fragment backfragment, Fragment backfragment1){
        if(fragment != null){

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .add(R.id.fragment_container, backfragment)
                    .hide(backfragment)
                    .add(R.id.fragment_container, backfragment1)
                    .hide(backfragment1)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.threedotmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.threedotmenu_logout){
            FirebaseAuth.getInstance().signOut();
            Intent I = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(I);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
