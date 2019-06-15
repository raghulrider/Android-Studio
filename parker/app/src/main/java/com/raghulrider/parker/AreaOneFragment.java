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
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.raghulrider.parker.App.CHANNEL_ID_1;

public class AreaOneFragment extends Fragment {
    private DatabaseReference ref;
    Context context;
    private NotificationManagerCompat notificationManager;
    static final String occupied ="occupied", available = "available";
    private String firstlane = "default",secondlane = "default",thirdlane = "default";
    private TextView tlane1,tlane2,tlane3;
    private ImageSwitcher ilane1,ilane2,ilane3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("Debug", "Area One Fragment");
        return inflater.inflate(R.layout.fragment_area_one, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = view.getContext();
        tlane1 = view.findViewById(R.id.textView1);
        tlane2 = view.findViewById(R.id.textView2);
        tlane3 = view.findViewById(R.id.textView3);
        ilane1 = view.findViewById(R.id.imageView1);
        ilane2 = view.findViewById(R.id.imageView2);
        ilane3 = view.findViewById(R.id.imageView3);
        registerForContextMenu(ilane1);
        registerForContextMenu(tlane1);
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
    private void performFunctions(DataSnapshot dataSnapshot){
        Lane area1data = dataSnapshot.getValue(Lane.class);
        firstlane = area1data.getFirstlane();
        secondlane = area1data.getSecondlane();
        thirdlane = area1data.getThirdlane();
        if(firstlane.equals(occupied)){
            tlane1.setText("Lane 1 : Occupied");
            ilane1.setBackgroundResource(R.drawable.occupied);
        } else if(firstlane.equals(available)){
            tlane1.setText("Lane 1 : Available");
            ilane1.setBackgroundResource(R.drawable.available);
        }

        if(secondlane.equals(occupied)){
            tlane2.setText("Lane 2 : Occupied");
            ilane2.setBackgroundResource(R.drawable.occupied);
        } else if(secondlane.equals(available)){
            tlane2.setText("Lane 2 : Available");
            ilane2.setBackgroundResource(R.drawable.available);
        }

        if(thirdlane.equals(occupied)){
            tlane3.setText("Lane 3 : Occupied");
            ilane3.setBackgroundResource(R.drawable.occupied);
        } else if(thirdlane.equals(available)){
            ilane3.setBackgroundResource(R.drawable.available);
            tlane3.setText("Lane 3 : Available");
        }

        if(firstlane.equals(occupied) && secondlane.equals(occupied) && thirdlane.equals(occupied)){
            Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID_1)
                    .setSmallIcon(R.drawable.alert)
                    .setContentTitle("Alert")
                    .setContentText("Area 1 - All lanes are occupied")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_REMINDER)
                    .setBadgeIconType(R.drawable.alert)
                    .setColor(getResources().getColor(R.color.red))
                    .build();
            notificationManager.notify(1 , notification);
        }

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.setnotification) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.notifyoption, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.add:
                Toast.makeText(getContext(), "Please Update Username", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
