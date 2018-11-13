''''source: https://people.csail.mit.edu/albert/bluez-intro/x290.html'''
import bluetooth                #PyBluez


server_sock=bluetooth.BluetoothSocket( bluetooth.RFCOMM ) #server socket RFCOMM

port = bluetooth.get_available_port( bluetooth.RFCOMM ) #find the avalible ports
server_sock.bind(("",port))                             #binding the socket to the port found
server_sock.listen(1)                                   #allowing the server to listen to the client
print ("listening on port %d" % port)

uuid = "1e0ca4ea-299d-4335-93eb-27fcfe7fa848"           #id
bluetooth.advertise_service( server_sock, "FooBar Service", uuid )  #advertising sever

client_sock,address = server_sock.accept()  #accepting connection
print ("Accepted connection from ",address)

data = client_sock.recv(1024)   #resieving data of size 1024
print ("received [%s]" % data)

client_sock.close()
server_sock.close()