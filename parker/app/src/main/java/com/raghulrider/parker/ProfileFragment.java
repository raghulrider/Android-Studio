package com.raghulrider.parker;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ProfileFragment extends Fragment {
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    ListView listView;
    private String userID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Debug", "Profile Fragment");
        return inflater.inflate(R.layout.fragment_profile, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        listView = view.findViewById(R.id.prolistview);
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        FloatingActionButton addbutton = view.findViewById(R.id.addbutton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateIntent = new Intent(getActivity(), UpdateProfile.class);
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
    private void showdata(DataSnapshot dataSnapshot){
        UserInfo uinfo = dataSnapshot.getValue(UserInfo.class);
        ArrayList<String> array = new ArrayList<>();
        array.add("Username : "+ uinfo.getUsername());
        array.add("Email : "+uinfo.getEmail());
        array.add("Car Name : "+uinfo.getCarname());
        array.add("FireBase UserID : "+userID);
        ArrayAdapter arrayAdapter =  new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, array );
        listView.setAdapter(arrayAdapter);
    }
}