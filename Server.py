import bluetooth*

server_socket = BluetoothSocket(RFCOMM)
server_socket.bind(("",PORT_ANY))
server_socket.listen(1)

port = server_sock.getsockname()[1]

#identical UUID for connection to client
uuid = "a1bb5f8d-406d-4119-9cc8-f6c8395514ae"

client_socket, address = server_socket.accept()

data = client_socket.recv(1024)

print "recieved[%s]" % data

client_socket.close()
server_socket.close()



if __name__ == '__main__':
    main()



