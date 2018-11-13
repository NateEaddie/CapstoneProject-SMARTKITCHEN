import ...;

BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
if (bluetoothAdapter == null){
    // Display a toast notifying the user that their device doesn't support Bluetooth //
    Toast.makeText(getApplicationContext(),
    "This device doesn't support Bluetooth.",
    Toast.LENGTH_SHORT).show();
    }
else{
    // If BluetoothAdapter doesn't return null, then the device does support Bluetooth //
    if(!bluetoothAdapter.isEnabled()){
        // Create an intent with the ACTION_REQUEST_ENABLE action, which we'll use to display our system Activtiy //
       intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

       // Pass this intent to startActivityForResult(). ENABLE_BT_REQUEST_CODE is a locally defined integer that
       // must be > 0, for example, private static final int ENABLE_BT_REQUEST_CODE = 1 //
       startActivityForResult(enableIntent, ENABLE_BT_REQUEST_CODE);
       Toast.makeText(getApplicationContext(), "Enabling Bluetooth!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        // Check to what request we're responding //
        if (requestCode == ENABLE_BT_REQUEST_CODE){
            // If the request was successful... //
            if (resultCode == Activity.RESULT_OK){
                // ...then display the following toast. //
                Toast.makeText(getApplicationContext(), "Bluetooth has been enabled.",
                Toast.LENGTH_SHORT).show();
            }
            // If the request was unsuccessful... //
            if (resultCode == RESULT_CANCELLED){
                // ...then display this alternative toast. //
                Toast.makeText(getApplicationContext(), "An error occurred while attempting to enable Bluetooth.",
                Toast.Length_SHORT).show();
            }
        }
    }

    // Retrieving the List of Paired Devices
    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevice();

    // If there's 1 or more paired devices... //
    if (pairedDevices.size() > 0){
        // ...then loop through these devices //
        for (BluetoothDevice device : pairedDevices){
            // Retrieve each devices's public identifier and MAC address. Add each device's name and
            // address to an ArrayAdapter, ready to incorporate into a ListView
            mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
        }
    }

    // Entering Discoverable Mode
    public static final int REQUEST_DISCOVERABLE_CODE = 2;
    ...
    ...
    Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

    // Specify how long the device will be discoverable for, in secodns. //
    discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 400);
    startActivity(discoveryIntent);

    // Issuing a Discovery Request
    if (bluetoothAdapter.startDiscovery()){
        // If discovery is started, then display the following toast... //
        Toast.makeText(getApplicationContext(), "Discovering other Bluetooth devices...",
        Toast.LENGTH_SHORT).show();
        }
    else{
        // If discovery hasn't started, then display this alternative Toast //
        Toast.makeText(getApplicationContext(), "Something went wrong! Discovery has failed to start.",
        Toast.LENGTH_SHORT).show();
        }

    // Ensure app gets notified whenever a new device is discovered by registering BroadcastReceiver
    // for ACTION_FOUND intent //

    // Register for the ACTION_FOUND broadcast //
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
    registerReceiver(broadcastReceiver, filter);

    //Create a BroadcastReceiver for ACTION_FOUND //
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){
        public void onReceive(Context context, Intent intent){
            String action = intent.getAction();

            // Whenever a remote Bluetooth device is found... //
            if (BluetoothDevice.ACTION_FOUND.equals(action)){

                // ...retrieve the BluetoothDevice object and its EXTRA_DEVICE field, which contains
                // information about the devices characteristics and abilities
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                // You'll usually want to display information about any devices you discover, so here
                // I'm adding each device's name and address to an ArrayAdapter, which I'd eventually
                // incorporate into ListView //
                adapter.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
            }
        }
    }

    // Always call cancelDiscovery before attempting to connect to a remote device
    @Override
    protected void onDestroy(){
        super.onDestroy();
        ...
        ...
        // Cut down on unnecessary system overhead, by unregistering the ACTION_FOUND receiver
        this.unregisterReceiver(broadcastReceiver);
        }

    // My unique UUID: a1bb5f8d-406d-4119-9cc8-f6c8395514ae
    private final static UUID uuid = UUID.fromString("a1bb5f8d-406d-4119-9cc8-f6c8395514ae");
    // Call create RfcommSocketToServiceRecord(UUID) method
    // UUID passed here must match the UUID that the server device used to open its BluetoothServerSocket
    // BluetoothSocket socket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);

    // Once server device and client device each have a connected BluetoothSocket, app is ready to start
    // communicating with remote device
    // Rough guideline to to tranfer data between two remote devices
    // 1) Call getInputStream and getOutputStream on the BluetoothSocket
    // 2) Use the read() method to start listening for incoming data
    // 3) Send data to a remote device by calling the thread's write() method and passing it the bytes
    //    you want to send
    // Both read() and write() methods are blocking calls, meaning you should always run them from a seperate thread
}























