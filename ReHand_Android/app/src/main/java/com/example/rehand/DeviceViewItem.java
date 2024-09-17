package com.example.rehand;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;
public class DeviceViewItem {
    private String Device_name;

    public DeviceViewItem(){

    }

    public DeviceViewItem(String device_name){
        Device_name = device_name;
    }

    public String getDeviceName() {
        return Device_name;
    }

    public void setDeviceName(String device_name) {
        Device_name = device_name;
    }

}
