package com.raghulrider.raghulsinfo;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class ContactActivity extends AppCompatActivity {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Contact Me");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent contactIntent = new Intent(ContactActivity.this, HomeActivity.class);
        startActivity(contactIntent);
        finish();
    }
}
