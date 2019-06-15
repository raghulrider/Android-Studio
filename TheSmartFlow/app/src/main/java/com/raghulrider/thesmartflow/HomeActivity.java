package com.raghulrider.thesmartflow;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView textview;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private String userID;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        textview = findViewById(R.id.textview4);
        FloatingActionButton fab = findViewById(R.id.fab);
        ref = FirebaseDatabase.getInstance().getReference().child("Patient");
        ref.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ProfileInformation proinfo = dataSnapshot.getValue(ProfileInformation.class);
                String username = proinfo.getUsername();
                textview.setText("Welcome, " + username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    System.out.println("User logged in");
                }
                else{
                    System.out.println("User not logged in");
                }
            }
        };
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"raghulprasadv@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Appreciating - Reg"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear Developer Raghulrider," + "Thank you for this amazing work");
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent I = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(I);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_profile) {
            Intent profileIntent = new Intent(HomeActivity.this, Profile.class);
            startActivity(profileIntent);
        } else if (id == R.id.nav_history) {
            Intent HistoryIntent = new Intent(HomeActivity.this, HistoryActivity.class);
            startActivity(HistoryIntent);
        } else if (id == R.id.nav_contact) {
            Intent contactIntent = new Intent(HomeActivity.this, ContactActivity.class);
            startActivity(contactIntent);
        }else if(id == R.id.nav_pump){
            Intent pumpIntent = new Intent(HomeActivity.this, InsulinPump.class);
            pumpIntent.putExtra("patientid",  getIntent().getStringExtra("patientid"));
            startActivity(pumpIntent);
        } else if (id == R.id.nav_developer) {
            Intent developerIntent = new Intent(HomeActivity.this, DeveloperActivity.class);
            startActivity(developerIntent);

        } else if (id == R.id.nav_about) {
            Intent aboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(aboutIntent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
