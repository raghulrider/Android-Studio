package com.raghulrider.parker;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.raghulrider.parker.App.CHANNEL_ID_1;
import static com.raghulrider.parker.App.CHANNEL_ID_2;


public class AreaTwoFragment extends Fragment {
    private DatabaseReference ref;
    Context context;
    private NotificationManagerCompat notificationManager;
    static final String occupied = "occupied", available = "available";
    private String fouthlane, fifthlane, sixthlane;
    private TextView tlane4, tlane5, tlane6;
    private ImageSwitcher ilane4, ilane5, ilane6;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Debug", "Area Two Fragment");
        return inflater.inflate(R.layout.fragment_area_two, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        context = view.getContext();
        tlane4 = view.findViewById(R.id.textView1);
        tlane5 = view.findViewById(R.id.textView2);
        tlane6 = view.findViewById(R.id.textView3);
        ilane4 = view.findViewById(R.id.imageView1);
        ilane5 = view.findViewById(R.id.imageView2);
        ilane6 = view.findViewById(R.id.imageView3);
        notificationManager = NotificationManagerCompat.from(context);
        ref = FirebaseDatabase.getInstance().getReference().child("Area");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                performFunctions(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void performFunctions(DataSnapshot dataSnapshot) {
        Lane area2data = dataSnapshot.getValue(Lane.class);
        fouthlane = area2data.getFourthlane();
        fifthlane = area2data.getFifthlane();
        sixthlane = area2data.getSixthlane();
        if (fouthlane.equals(occupied)) {
            tlane4.setText("Lane 4 : Occupied");
            ilane4.setBackgroundResource(R.drawable.occupied);
        } else if (fouthlane.equals(available)) {
            tlane4.setText("Lane 4 : Available");
            ilane4.setBackgroundResource(R.drawable.available);
        }

        if (fifthlane.equals(occupied)) {
            tlane5.setText("Lane 5 : Occupied");
            ilane5.setBackgroundResource(R.drawable.occupied);
        } else if (fifthlane.equals(available)) {
            tlane5.setText("Lane 5 : Available");
            ilane5.setBackgroundResource(R.drawable.available);
        }

        if (sixthlane.equals(occupied)) {
            tlane6.setText("Lane 6 : Occupied");
            ilane6.setBackgroundResource(R.drawable.occupied);
        } else if (sixthlane.equals(available)) {
            ilane6.setBackgroundResource(R.drawable.available);
            tlane6.setText("Lane 6 : Available");
        }

        if (fouthlane.equals(occupied) && fifthlane.equals(occupied) && sixthlane.equals(occupied)) {
            Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID_2)
                    .setSmallIcon(R.drawable.alert)
                    .setContentTitle("Alert")
                    .setContentText("Area 2 - All lanes are occupied" +
                            "")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_REMINDER)
                    .setColor(getResources().getColor(R.color.red))
                    .build();
            notificationManager.notify(2, notification);
        }
    }
}