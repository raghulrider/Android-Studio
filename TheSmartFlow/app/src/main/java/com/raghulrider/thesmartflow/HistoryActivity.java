package com.raghulrider.thesmartflow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class HistoryActivity extends AppCompatActivity {
    Button databtn,updatebtn;
    Databasehelper mydb;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private String userID;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("Patient").child(userID);
        actionBar =getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        updatebtn = findViewById(R.id.updatebtn);
        databtn = findViewById(R.id.databtn);
        mydb = new Databasehelper(this);
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.child("injected").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        History insulininfo = dataSnapshot.getValue(History.class);
                        String content = insulininfo.getData();
                        String[] piece = content.split("-");
                        boolean isInserted = mydb.insertData(piece[0],piece[1],piece[2],piece[3]);
                        if( isInserted == true){
                            Log.i("Success","Inserted to database success");
                        }
                        else
                        {
                            Log.i("Failure","Inserted to database Filed");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        databtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getALLData();
                if(res.getCount() ==0){
                    showMessage("ERROR","NOTHING FOUND");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Shot: "+res.getString(0)+"\n\n");
                    buffer.append("Date: "+res.getString(1)+"\n\n");
                    buffer.append("Time: "+res.getString(2)+"\n\n");
                    buffer.append("Type: "+res.getString(3)+"\n\n");
                    buffer.append("Units: "+res.getString(4)+"\n\n\n\n");
                }
                showMessage("Insulin History", buffer.toString());
            }
        });

    }
    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

}

