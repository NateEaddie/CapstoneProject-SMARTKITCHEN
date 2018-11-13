from bluetooth import*
import os
import glob

os.system("hciconfig hci0 piscan")

hostMACAddress = '00:1f:e1:dd:08:3d' # The MAC address of a Bluetooth adapter on the server. The server might have multiple Bluetooth adapters.
server_socket = BluetoothSocket(RFCOMM)
server_socket.bind(("",PORT_ANY))


#listens for data to be sent to the socket
server_socket.listen(1)
port = server_socket.getsockname()[1]


#identical UUID for connection to client

uuid = "a1bb5f8d-406d-4119-9cc8-f6c8395514ae"
"""
uuid = "2F234454-CF6D-4A0F-ADF2-F4911BA9FFA6"
"""
#port = bluetooth.PORT_ANY #arbitrary choice. However, it must match the port used by the client.
size = 1024






#"""

advertise_service(server_socket, "Our Server", service_id = uuid,
                            service_classes = [uuid, SERIAL_PORT_CLASS],
                            profiles = [SERIAL_PORT_PROFILE])

#"""

client_socket = None



print("here n")

client_socket, address = server_socket.accept()

print ("here1")
if client_socket == None:
    print ("No client")

print ("Connection established with: " , address)

"""
data = client_socket.recv(size)

print ("recieved[%s] \n" % data)
"""
client_socket.close()
server_socket.close()


"""
if __name__ == '__main__':
    main()
"""


