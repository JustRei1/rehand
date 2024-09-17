package com.example.rehand;

import android.bluetooth.BluetoothClass;
import android.view.View;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DeviceViewHolder extends RecyclerView.ViewHolder{

    TextView device_name;;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    private Context context;
    public DeviceViewHolder(@NonNull View itemView, Context context){
        super(itemView);
        this.context = context;

        // device items

        device_name = itemView.findViewById(R.id.device_name);
    }

//    Add Functions as such below!

}
