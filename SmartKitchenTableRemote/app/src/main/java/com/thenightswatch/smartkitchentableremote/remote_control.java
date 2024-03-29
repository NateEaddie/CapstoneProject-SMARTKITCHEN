package com.thenightswatch.smartkitchentableremote;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

// 10.235.56.164

public class remote_control extends AppCompatActivity {

    private static final UUID myUUID = UUID.fromString("a1bb5f8d-406d-4119-9cc8-f6c8395514ae");
    int ENABLE_BT_REQUEST_CODE = 1;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Vibrator vib;
    boolean connected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_remote_control);

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

        vib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);

        ImageButton upButton = findViewById(R.id.upButton);
        upButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (connected) {
                    try {
                        write("1");
                        vib.vibrate(50);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please connect your device to Bluetooth using the 'Connect to Bluetooth' button",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageButton downButton = findViewById(R.id.downButton);
        downButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (connected) {
                    try {
                        write("0");
                        vib.vibrate(50);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please connect your device to Bluetooth using the 'Connect to Bluetooth' button.",
                            Toast.LENGTH_SHORT).show();
                }
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
     * Called to send data to Pi over Bluetooth
//     */
    public void write(String i) throws IOException{
        outputStream.write(i.getBytes());
    }

    /**
     * I believe it reads data from Bluetooth
     */
    public void run(){
        final int BUFFER_SIZE = 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytes = 0;
        int b = BUFFER_SIZE;

        while (true){
            try{
                bytes = inputStream.read(buffer, bytes, BUFFER_SIZE - bytes);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
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
            Toast.makeText(getApplicationContext(), "This device does not support Bluetooth.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (!bluetoothAdapter.isEnabled()) {
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableIntent, ENABLE_BT_REQUEST_CODE);
                Toast.makeText(getApplicationContext(), "Enabling Bluetooth!", Toast.LENGTH_LONG).show();
            }
            String macAddress = "B8:27:EB:5A:DA:B6";
            BluetoothDevice device = bluetoothAdapter.getRemoteDevice(macAddress);
            BluetoothSocket tmp = null;
            BluetoothSocket socket = null;

            // Get a BluetoothSocket for a connection with a given device
            try{
                tmp = device.createRfcommSocketToServiceRecord(uuid);
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
                if (socket != null) {
                    socket.connect();
                    outputStream = socket.getOutputStream();
                    inputStream = socket.getInputStream();
                    connected = true;
                }
            }
            catch(IOException ie){
                ie.printStackTrace();
                try {
                    socket.close();
                    connected = false;
                    Toast.makeText(getApplicationContext(), "Bluetooth connection lost. Please attempt to reestablish before continuing.", Toast.LENGTH_LONG).show();
                } catch (IOException closeException) {
                    closeException.printStackTrace();
                }
            }
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
                Toast.makeText(getApplicationContext(), "An error occurred while attempting to enable Bluetooth.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
