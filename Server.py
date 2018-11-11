import bluetooth*

hostMACAddress = '00:1f:e1:dd:08:3d' # The MAC address of a Bluetooth adapter on the server. The server might have multiple Bluetooth adapters.

port = 3 # 3 is an arbitrary choice. However, it must match the port used by the client.
size = 1024

server_socket = BluetoothSocket(RFCOMM)
server_socket.bind(("",PORT_ANY))
server_socket.listen(1)

port = server_sock.getsockname()[1]

#identical UUID for connection to client
uuid = "a1bb5f8d-406d-4119-9cc8-f6c8395514ae"

client_socket, address = server_socket.accept()

data = client_socket.recv(size)

print "recieved[%s]" % data

client_socket.close()
server_socket.close()



if __name__ == '__main__':
    main()



