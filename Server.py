import bluetooth*

hostMACAddress = '00:1f:e1:dd:08:3d' # The MAC address of a Bluetooth adapter on the server. The server might have multiple Bluetooth adapters.

port = 3 # 3 is an arbitrary choice. However, it must match the port used by the client.
size = 1024

server_socket = BluetoothSocket(RFCOMM)
server_socket.bind(("",port)) #the quote can likely be replaced with the hostMACAdress
server_socket.listen(1)

client_socket, address = server_socket.accept()

data = client_socket.recv(size)

print "recieved[%s]" % data

client_socket.close()
server_socket.close()



