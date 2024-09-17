package com.example.rehand;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.util.Log;
import android.Manifest;

import com.google.android.material.navigation.NavigationView;

import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView naView = findViewById(R.id.side_menu);
        naView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home())
                    .commit();
            naView.setCheckedItem(R.id.home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home()).commit();
            Log.i("Tite", "Going to Home");
        } else if (itemId == R.id.data) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserData()).commit();
            Log.i("Tite", "Going to Data");
        } else if (itemId == R.id.info) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Info()).commit();
            Log.i("Tite", "Going to Info");
        } else if (itemId == R.id.requests) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Request()).commit();
            Log.i("Tite", "Going to Requests");
        } else if (itemId == R.id.operators) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ManageOps()).commit();
            Log.i("Tite", "Going to Operators");
        } else if (itemId == R.id.logout) {
            // Handle logout logic here
            Toast.makeText(this, "Logout!", Toast.LENGTH_LONG).show();
            Log.i("Tite", "Logged Out");
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}