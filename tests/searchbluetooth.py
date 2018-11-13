"""
import bluetooth

username_of_phone = "Daddy"
bluetooth_address = None

nearby_devices = bluetooth.discover_devices()

for bdaddr in nearby_devices:
    if username_of_phone == bluetooth.lookup_name( bdaddr ):
        bluetooth_address = bdaddr
        break

if bluetooth_address is not None:
    print ("found target bluetooth device with address ", bluetooth_address)
else:
    print ("could not find target bluetooth device nearby")

"""


"""
    A simple Python script to receive messages from a client over
    Bluetooth using Python sockets (with Python 3.3 or above).
    """

import socket

hostMACAddress = 'xx:xx:xx:xx:xx:xx' # The MAC     address of a Bluetooth adapter on the server
port = 3
backlog = 1
size = 1024
s=socket.socket(socket.AF_BLUETOOTH,socket.SOCK_STREAM,socket.BTPROTO_RFCOMM)
s.bind((hostMACAddress,port))
s.listen(backlog)
try:
    client, address = s.accept()
    while 1:
        data = client.recv(size)
        if data:
            print("data")
            client.send(data)
except:
    print("Closing socket")
    client.close()
    s.close()
