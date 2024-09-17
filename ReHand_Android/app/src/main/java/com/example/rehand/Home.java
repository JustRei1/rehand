package com.example.rehand;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Set;

import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import org.w3c.dom.Text;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class Home extends Fragment {

    // Initialize variables
    BluetoothAdapter btAdapter;
    Button connect_btn;
    TextView connect_prompt;
    Intent btEnable;
    RecyclerView recyclerView;
    int requestCodeEnable;
    ConstraintLayout homeheader;
    RelativeLayout homebody;
    private boolean swiped;
    Button temp_swipe;
    public static Handler handler;

    private final static int ERROR_READ = 0; //Used in BT handler to identify message updates
    BluetoothDevice arduinoBTmodule = null;
    UUID arduinoUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Default UUID
    @RequiresApi(api=Build.VERSION_CODES.M)

    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View actView = inflater.inflate(R.layout.fragment_home, container, false);

        // Instances of BT Manager and Adapter needed to work with BT in android
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btEnable = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        // Android UI elements
        connect_btn = actView.findViewById(R.id.connect_btn);
        connect_prompt = actView.findViewById(R.id.connect_prompt);

        // Default value for Enabling Bluetooth
        requestCodeEnable = 1;

        // Create an observable from RxAndroid

        final Observable<String> connectToBTObservable = Observable.create(emitter -> {
            Log.d("BTLogs", "Calling connectThread class");

            ConnectThread connectThread = new ConnectThread(arduinoBTmodule, arduinoUUID, handler);
            connectThread.run();
            //Check if socket connected
            if(connectThread.getMmSocket().isConnected()){
                Log.d("BTLogs", "Calling ConnectedThread Class");
                ConnectedThread connectedThread = new ConnectedThread(connectThread.getMmSocket());
                connectedThread.run();
                if(connectedThread.getValueRead() != null){
                    emitter.onNext(connectedThread.getValueRead());
                }
                //Stream one value only so we close it
                connectedThread.cancel();
            }

            connectThread.cancel();
            emitter.onComplete();
        });

        // Connect to Device/Open Bluetooth Button
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check for Bluetooth perms
                checkBluetoothPermissions();

                if(arduinoBTmodule != null) {
                    Log.i("BTLogs", "Connected to Galaxy Buds");
                    connectToBTObservable.observeOn(AndroidSchedulers.mainThread()).
                            subscribeOn(Schedulers.io()).
                            subscribe(valueRead -> {

                                connect_prompt.setText(valueRead);

                            });
                }else{
                    Log.i("BTLogs", "Not Connected to Galaxy Buds");
                }
            }
        });

        // Get Home header
        homeheader = actView.findViewById(R.id.home_header);

        // Get home body
        homebody = actView.findViewById(R.id.home_body);

        temp_swipe = actView.findViewById(R.id.tempswipe);
        temp_swipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!swiped) {
                    // Visibility of view
                    homeheader.setVisibility(View.INVISIBLE);

                    // Create and start the ObjectAnimator
                    ObjectAnimator animator = ObjectAnimator.ofFloat(homebody, "translationY", 0, -(homeheader.getHeight()));
                    animator.setDuration(500);
                    animator.start();

                    Log.i("Title", "Swiped Up");
                } else {
                    // Similar approach for swiping down
                    ObjectAnimator animator = ObjectAnimator.ofFloat(homebody, "translationY", -(homeheader.getHeight()), 0);
                    animator.setDuration(500);
                    animator.start();

                    homeheader.setVisibility(View.VISIBLE);

                    Log.i("Title", "Swiped Down");
                }
                swiped = !swiped;
            }
        });

        // A handler used to update the interface in case of errors when connecting to BT device
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg){
                switch(msg.what){
                    case ERROR_READ:
                        String arduinoMsg = msg.obj.toString(); //Read msg from Arduino
                        connect_prompt.setText(arduinoMsg);
                        break;
                }
            }
        };

//        Device list using recycler view
//        recyclerView = actView.findViewById(R.id.device_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return actView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == requestCodeEnable) {
            if (resultCode == MainActivity.RESULT_OK) {
                Toast.makeText(getContext(), "Bluetooth Turned On.",
                        Toast.LENGTH_LONG).show();
            } else if (resultCode == MainActivity.RESULT_CANCELED) {
                Toast.makeText(getContext(), "Access Denied!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void checkBluetoothPermissions() {
        Log.i("BTLogs", "Checking perms");
        String[] permissions = {android.Manifest.permission.BLUETOOTH_CONNECT, android.Manifest.permission.BLUETOOTH_SCAN};
        if (!hasAllPermissions(getContext(), permissions)) {
            Log.w("BTLogs", "Requesting...");
            requestPermissions(getContext(), permissions, PERMISSION_REQUEST_CODE);
        } else {
            // Permissions are already granted, proceed with Bluetooth operations
            Log.w("BTLogs", "Access Granted already!");
            enableBluetooth();
        }
    }

    private boolean hasAllPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void requestPermissions(Context context, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions((Activity) context, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                // Permission denied
                Toast.makeText(getContext(), "Bluetooth permission is required to continue.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("CheckResult")
    private void enableBluetooth() {

        Log.i("BTLogs", "Proceeding to enabling bluetooth function");
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        // Turn on BT
        if (btAdapter != null && !btAdapter.isEnabled()) {
            startActivityForResult(btEnable, requestCodeEnable);

        }else{
            Log.i("BTLogs", "Device not discoverable");
        }

    }

    private void connectToDevice() {

        Log.i("BTLogs", "Attempting Connection...");

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices == null || pairedDevices.size() == 0) {
            Log.i("BTLogs", "No paired devices found");
            Toast.makeText(getContext(), "No Paired Devices Found!",
                    Toast.LENGTH_LONG).show();
            return;
        }

        for (BluetoothDevice device : pairedDevices) {

            Log.i("BTLogs", device.getName());

            if (device.getName().contains("Galaxy") || device.getName().contains("Buds FE")) {
                BluetoothGatt gatt = device.connectGatt(getContext(), false, bluetoothGattCallback);

                Toast.makeText(getContext(), "Trying to connect to " + device.getName(),
                        Toast.LENGTH_LONG).show();

                arduinoUUID = device.getUuids()[0].getUuid();
                arduinoBTmodule = device;

                // Wait for a short period before connecting
                try {
                    Thread.sleep(5000); // Wait for 5 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                connect_btn.setEnabled(true);
//                gatt.connect();

                Log.d("BTLogs", "Connected to device: " + device.getName());
                return;
            }else{
                Toast.makeText(getContext(), "Could not find Rehand Device",
                        Toast.LENGTH_LONG).show();
            }

        }

        Log.e("BTLogs", "No matching device found");
    }

    private final BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {

        int reconnectFlag = 1;
        @SuppressLint("MissingPermission")
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                Log.d("BTLogs", "Trying to connect to device: " + gatt.getDevice().getName());
                gatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                Log.d("BTLogs", "Disconnected from device: " + gatt.getDevice().getName());
                if(reconnectFlag != 3){
                    Log.i("BTLogs", "Attempting Reconnection... Try: " + reconnectFlag);
                    Toast.makeText(getContext(), "Attempting to Reconnect... Try: " + reconnectFlag,
                            Toast.LENGTH_LONG).show();
                    reconnect(gatt); //Attempt reconnection
                    reconnectFlag += 1;
                }else{
                    Toast.makeText(getContext(), "Connection Timed Out",
                            Toast.LENGTH_LONG).show();
                }
            }
        }

        @SuppressLint("MissingPermission")
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            Log.d("BTLogs", "Services discovered for device: " + gatt.getDevice().getName());
        }
    };

    @SuppressLint("MissingPermission")
    private boolean reconnect(BluetoothGatt gatt) {
        int connectRetries = 0;
        while (connectRetries < 2 && !gatt.connect()) {
            try {
                Thread.sleep(5000); // Wait for 5 seconds before retrying
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gatt.connect();
            connectRetries++;
        }
        return gatt.connect();
    }
}