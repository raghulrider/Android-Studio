package com.raghulrider.thesmartflow;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfile extends AppCompatActivity {
    ActionBar actionBar;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private String userID;
    EditText uname,age,weight,gender,basal,bolus,diabetes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        mAuth = FirebaseAuth.getInstance();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        uname = findViewById(R.id.usernameupdate);
        age = findViewById(R.id.ageupdate);
        weight = findViewById(R.id.weightupdate);
        gender = findViewById(R.id.genderupdate);
        basal = findViewById(R.id.basalupdate);
        bolus = findViewById(R.id.bolusupdate);
        diabetes = findViewById(R.id.diabetesupdate);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("Patient");
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
    }

    public void updatebtn(View view) {
        DatabaseReference dusername = database.getReference("Patient").child(userID).child("username");
        DatabaseReference dage = database.getReference("Patient").child(userID).child("age");
        DatabaseReference dgender = database.getReference("Patient").child(userID).child("gender");
        DatabaseReference dweight = database.getReference("Patient").child(userID).child("weight");
        DatabaseReference dbasal = database.getReference("Patient").child(userID).child("basal");
        DatabaseReference dbolus = database.getReference("Patient").child(userID).child("bolus");
        DatabaseReference ddiabetestype = database.getReference("Patient").child(userID).child("diabetestype");

        dusername.setValue(uname.getText().toString());
        dage.setValue(age.getText().toString());
        dgender.setValue(gender.getText().toString());
        dweight.setValue(weight.getText().toString());
        dbasal.setValue(basal.getText().toString());
        dbolus.setValue(bolus.getText().toString());
        ddiabetestype.setValue(diabetes.getText().toString());
        finish();
    }
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}
