package com.raghulrider.parker;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateProfile extends AppCompatActivity {
    ActionBar actionBar;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String userID;
    Button login;
    EditText username, carname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Update Info");
        mAuth = FirebaseAuth.getInstance();
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        username = findViewById(R.id.usernameupdate);
        carname = findViewById(R.id.carupdate);
        login = findViewById(R.id.ahlogin);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users");
    }

    public void updatebtn(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        DatabaseReference dusername = database.getReference("Users").child(userID).child("username");
        DatabaseReference dcarname = database.getReference("Users").child(userID).child("carname");
        final String uname = username.getText().toString();
        final String cname = carname.getText().toString();
        if(TextUtils.isEmpty(uname)){
            Toast.makeText(getApplicationContext(), "Please Update Username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cname)) {
            Toast.makeText(getApplicationContext(), "Please Update Car Name ", Toast.LENGTH_SHORT).show();
            return;
        }
        dusername.setValue(uname);
        dcarname.setValue(cname);
        finish();
    }


    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
