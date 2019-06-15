package com.raghulrider.thesmartflow;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText patientID,pin,basal,bolus,mail,username;
    private Patient patient;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    ActionBar actionBar;
    private FirebaseAuth mAuth;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        patientID = findViewById(R.id.patientid);
        pin = findViewById(R.id.password);
        bolus = findViewById(R.id.bolus);
        mail = findViewById(R.id.email);
        basal = findViewById(R.id.basal);
        username = findViewById(R.id.usrername);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Register Account");

        patient = new Patient();
        ref = database.getReference().child("Patient");
    }

    public void registerBtn(View view) {
        final String email = mail.getText().toString();
        final String password = pin.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), "Enter Eamil Id", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Patient Created Successfully",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            userID = user.getUid();
                            patient.setPatientID(patientID.getText().toString());
                            patient.setEmail(mail.getText().toString());
                            patient.setBolus(bolus.getText().toString());
                            patient.setBasal(basal.getText().toString());
                            patient.setUsername(username.getText().toString());
                            ref.child(userID).setValue(patient).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        DatabaseReference age = database.getReference("Patient").child(userID).child("age");
                                        DatabaseReference weight = database.getReference("Patient").child(userID).child("weight");
                                        DatabaseReference gender = database.getReference("Patient").child(userID).child("gender");
                                        DatabaseReference battlevel = database.getReference("Patient").child(userID).child("batterylevel");
                                        DatabaseReference reslevel = database.getReference("Patient").child(userID).child("reservoirlevel");
                                        DatabaseReference diabetestype = database.getReference("Patient").child(userID).child("diabetestype");
                                        DatabaseReference data = database.getReference("Patient").child(userID).child("injected").child("data");
                                        age.setValue("null");
                                        weight.setValue("null");
                                        gender.setValue("null");
                                        battlevel.setValue("null");
                                        reslevel.setValue("null");
                                        diabetestype.setValue("null");
                                        data.setValue("null");

                                        Toast.makeText(RegisterActivity.this, "Patient Created Successfully", Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Failed creating Patient", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Patient Creation Failed",
                                    Toast.LENGTH_SHORT).show();
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
