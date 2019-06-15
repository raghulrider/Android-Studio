package com.raghulrider.parker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    public EditText pin,mail,username,carname;
    ActionBar actionBar;
    private UserInfo uinfo;
    private FirebaseAuth mAuth;
    private DatabaseReference ref;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        pin = findViewById(R.id.password);
        mail = findViewById(R.id.email);
        username = findViewById(R.id.username);
        carname =  findViewById(R.id.carname);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Register Account");
        uinfo = new UserInfo();
    }

    public void registerBtn(View view) {
        final String email = mail.getText().toString();
        final String password = pin.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Please Enter Email Id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Parker created Successfully",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = user.getUid();
                            ref = FirebaseDatabase.getInstance().getReference().child("Users");
                            uinfo.setEmail(email);
                            uinfo.setUsername(username.getText().toString());
                            uinfo.setCarname(carname.getText().toString());
                            ref.child(userID).setValue(uinfo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Parker Data Update Success",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Failed creating Parker", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}