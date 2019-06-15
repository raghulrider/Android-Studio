package com.raghulrider.thesmartflow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class InsulinPump extends AppCompatActivity {
    private static final String TAG = "Insulin pump Data";

    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private String userID;
    private Animation blink;
    private ListView mlistview;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insulin_pump);

        mAuth = FirebaseAuth.getInstance();
        mlistview = findViewById(R.id.listview6);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("Patient");
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Insulin Pump");
        blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blinking);
        ref.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showdata(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showdata(DataSnapshot dataSnapshot) {
        PumpInformation pinfo = dataSnapshot.getValue(PumpInformation.class);
        ArrayList<String> array = new ArrayList<>();
        array.add("Batttery Level: "+pinfo.getBatterylevel());
        array.add("Reservoir Level: "+pinfo.getReservoirlevel());
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
        mlistview.setAdapter(adapter);
        mlistview.setVisibility(View.VISIBLE);
        mlistview.startAnimation(blink);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
    }
