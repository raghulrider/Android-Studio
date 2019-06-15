package com.raghulrider.raghulsinfo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");

        final Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        TextView mail = findViewById(R.id.email);
        TextView contact = findViewById(R.id.contact);
        TextView department = findViewById(R.id.department);
        TextView aoi = findViewById(R.id.aoi);
        final TextView datamail = findViewById(R.id.emaildata);
        final TextView datacontact = findViewById(R.id.contactdata);
        final TextView datadepartment = findViewById(R.id.departmentdata);
        final TextView dataaoi = findViewById(R.id.aoidata);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datamail.startAnimation(bounce);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datacontact.startAnimation(bounce);
            }
        });
        department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datadepartment.startAnimation(bounce);
            }
        });
        aoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataaoi.startAnimation(bounce);
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        }
         else if (id == R.id.nav_profile) {
            Intent contactIntent = new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(contactIntent);
            finish();


        } else if (id == R.id.nav_projects) {
            Intent contactIntent = new Intent(HomeActivity.this, ProjectActivity.class);
            startActivity(contactIntent);
            finish();

        } else if (id == R.id.nav_contact) {
            Intent contactIntent = new Intent(HomeActivity.this, ContactActivity.class);
            startActivity(contactIntent);
            finish();

        } else if (id == R.id.nav_alarm) {
            Intent alarmIntent = new Intent(HomeActivity.this, AlarmActivity.class);
            startActivity(alarmIntent);
            finish();

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Why :(")
                .setMessage("Are you sure you wanna exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
