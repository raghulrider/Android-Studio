package com.raghulrider.thesmartflow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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


public class Profile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private String userID;
    private ListView plistview;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        plistview = findViewById(R.id.prolistview);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("Patient");
        actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        FloatingActionButton addbutton = findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(Profile.this, UpdateProfile.class);
                startActivity(updateIntent);
            }
        });

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
        ProfileInformation proinfo = dataSnapshot.getValue(ProfileInformation.class);
        ArrayList<String> array1 = new ArrayList<>();
        array1.add("PatientID: "+ proinfo.getPatientID());
        array1.add("Username: "+proinfo.getUsername());
        array1.add("Email: "+proinfo.getEmail());
        array1.add("Age: "+proinfo.getAge());
        array1.add("Weight: "+proinfo.getWeight() + " Kg");
        array1.add("Gender: "+proinfo.getGender());
        array1.add("Diabetes Type: "+proinfo.getDiabetestype());
        array1.add("Basal: "+proinfo.getBasal() + " Units");
        array1.add("Bolus: "+proinfo.getBolus()+ " Units");
        ArrayAdapter arrayadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array1);
        plistview.setAdapter(arrayadapter);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    }

;