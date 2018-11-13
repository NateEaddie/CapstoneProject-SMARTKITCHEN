package com.thenightswatch.smartkitchentableremote;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

// 10.235.56.164

public class remote_control extends AppCompatActivity {

    private static final UUID myUUID = UUID.fromString("a1bb5f8d-406d-4119-9cc8-f6c8395514ae");
    int ENABLE_BT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_control);

//        connectToPi(myUUID);

        Button optionsButton = findViewById(R.id.button);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptionsMenu();
            }
        });

        Button logoutButton = findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        Button bluetoothButton = findViewById(R.id.bluetooth_button);
        bluetoothButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Attempting to connect to Pi...",
                        Toast.LENGTH_SHORT).show();
                connectToPi(myUUID);
            }
        });
    }

    /**
     * Called when the user taps the Options button
     */
    public void openOptionsMenu() {
        Intent intent = new Intent(remote_control.this, OptionsMenu.class);
        startActivity(intent);
    }

    /**
     * Called when the user taps the Options button
     */
    public void logout() {
        Intent intent = new Intent(remote_control.this, login_screen.class);
        startActivity(intent);
    }

    public void connectToPi(UUID uuid) {

        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "This device doesn't support Bluetooth.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                int resultCode = 0;
                startActivityForResult(enableIntent, ENABLE_BT_REQUEST_CODE);
                Toast.makeText(getApplicationContext(), "Enabling Bluetooth!", Toast.LENGTH_LONG).show();
            }
            String macAddress = "B8:27:EB:5A:DA:B6";
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);
            BluetoothSocket tmp = null;
            BluetoothSocket socket = null;

            // Get a BluetoothSocket for a connection with a given device
            try{
                tmp = device.createRfcommSocketToServiceRecord(myUUID);
                Method m = device.getClass().getMethod("createRfcommSocket", new Class[] {int.class});
                tmp = (BluetoothSocket) m.invoke(device, 1);
            }
            catch (IOException ie){
                ie.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            socket = tmp;
            try{
                socket.connect();
            }
            catch(IOException ie){
                ie.printStackTrace();
            }


//            if (bluetoothAdapter.startDiscovery()){
//                Toast.makeText(getApplicationContext(), "Discovering other Bluetooth devices...",
//                        Toast.LENGTH_SHORT).show();
//            }
//            else {
//                Toast.makeText(getApplicationContext(), "Something went wrong! Discovery has failed to start.",
//                        Toast.LENGTH_SHORT).show();
//
//            final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//                @Override
//                public void onReceive(Context context, Intent intent) {
//                    String action = intent.getAction();
//                    // When discovery finds a device
//                    if (BluetoothDevice.ACTION_FOUND.equals(action)){
//                        // Get the BluetoothDevice object from the Intent
//                        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//
//                        // Client has found Raspberry Pi Server
//                        if (device.getAddress().equals("B8:27:EB:5A:DA:B6")){
//                            try{
//                                BluetoothSocket socket = device.createRfcommSocketToServiceRecord(myUUID);
//                            }
//                            catch(IOException ie){
//                                ie.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            };
//
//            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//            registerReceiver(broadcastReceiver, filter);
//        }
    }
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Check what request we’re responding to//
        if (requestCode == ENABLE_BT_REQUEST_CODE) {

            //If the request was successful…//
            if (resultCode == AppCompatActivity.RESULT_OK) {

                //...then display the following toast.//
                Toast.makeText(getApplicationContext(), "Bluetooth has been enabled.",
                        Toast.LENGTH_SHORT).show();
            }

            //If the request was unsuccessful...//
            if(resultCode == RESULT_CANCELED){

                //...then display this alternative toast.//
                Toast.makeText(getApplicationContext(), "An error occurred while attempting to enable Bluetooth",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}

//        Button bluetoothButton = findViewById(R.id.bluetooth_button);
//        final int ENABLE_BT_REQUEST_CODE = 1;
//        bluetoothButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//                if (bluetoothAdapter == null){
//                    // Display a toast notifying the user that their device doesn't support Bluetooth //
//                    Toast.makeText(getApplicationContext(), "This device doesn't support Bluetooth.", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    // If BluetoothAdapter doesn't return null, then the device does support Bluetooth //
//                    if(!bluetoothAdapter.isEnabled()){
//                        // Create an intent with the ACTION_REQUEST_ENABLE action, which we'll use to display our system Activity //
//                        Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                        int resultCode = 0;
//
//                        // Pass this intent to startActivityForResult(). ENABLE_BT_REQUEST_CODE is a locally defined integer that
//                        // must be > 0, for example, private static final int ENABLE_BT_REQUEST_CODE = 1 //
//                        startActivityForResult(enableIntent, ENABLE_BT_REQUEST_CODE);
//                        Toast.makeText(getApplicationContext(), "Enabling Bluetooth!", Toast.LENGTH_LONG).show();
////                        onActivityResult(ENABLE_BT_REQUEST_CODE, resultCode, enableIntent);
//
//
//                        // Issuing a discovery request
//                        if (bluetoothAdapter.startDiscovery()){
//                            // If discovery has started, then display the following Toast... //
//                            Toast.makeText(getApplicationContext(), "Discovering other Bluetooth devices...",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            // If discovery hasn't started, then display this alternative Toast //
//                            Toast.makeText(getApplicationContext(), "Something went wrong! Discovery has failed to start.",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // Register for the ACTION_FOUND broadcast //
//                        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//
//                        // Create a BroadcastReceiver
//                        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){
//                            public void onReceive(Context context, Intent intent){
//                                String action = intent.getAction();
//
//                                // Whenever a remote Bluetooth device is found... //
//                                if (BluetoothDevice.ACTION_FOUND.equals(action)){
//
//                                    // ...retrieve the BluetoothDevice object and its EXTRA_DEVICE field, which contains
//                                    // information about the device's characteristics and capabilities //
//                                    BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//
//                                    // You'll usually want to display information about any devices you discover, so here I'm adding each
//                                    // device's name and address to an ArrayAdapter, which I'd eventually incorporate into a ListView
//                                    // bluetoothAdapter.add(device.getName() + "\n" + device.getAddress());
//
//                                    if (bluetoothDevice.getName().equals("raspberrypi")){
//                                        try{
//                                            BluetoothSocket socket = bluetoothDevice.createRfcommSocketToServiceRecord(myUUID);
//                                            socket.connect();
//                                        }
//                                        catch(IOException ie){
//                                            ie.printStackTrace();
//                                        }
//                                    }
//
//                                }
//                            }
//                        };
//
//
//                        registerReceiver(broadcastReceiver, filter);
//                    }
//                }
//            }
//        });
//

//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//        this.unregisterReceiver(broadcastReceiver);
//    }
