package com.example.rehand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RV_DeviceAdapter extends RecyclerView.Adapter<DeviceViewHolder> {

    Context context;
    List<DeviceViewItem> items;

    public RV_DeviceAdapter(Context context, List<DeviceViewItem> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.find_device_item,parent,false);
        return new DeviceViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {
//        holder.fname.setText(items.get(position).getFname());
//        holder.lname.setText(items.get(position).getLname());
//        holder.imageView.setImageResource(items.get(position).getImage());
//        holder.email.setText(items.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
