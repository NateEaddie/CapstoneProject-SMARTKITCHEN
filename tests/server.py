"""source: https://people.csail.mit.edu/albert/bluez-intro/x290.html"""

import bluetooth                #PyBluez



hostMACAddress = '00:1f:e1:dd:08:3d'                    #The MAC address of a Bluetooth adapter on the server. The server might have multiple Bluetooth adapters.
uuid = "a1bb5f8d-406d-4119-9cc8-f6c8395514ae"           #uuid
size = 1024                                             #Size used to control the size of data resieved

server_socket = BluetoothSocket(RFCOMM)
server_socket.bind(("",PORT_ANY))
server_sock.listen(1)                                   #allowing the server to listen to the client
print ("listening on port %d" % port)


advertise_service(server_socket, "Our Server", service_id = uuid, service_classes = [uuid, SERIAL_PORT_CLASS], profiles = [SERIAL_PORT_PROFILE])



client_socket = None
client_socket, address = server_socket.accept() #accepting connection
if client_socket == None:
    print ("No client")

print ("Accepted connection from ",address)


#TWO POSSIBLE INPUTS: Up & Down

#Going to need to accept data and react accodingly
#data = client_sock.recv(size)   #resieving data of size 1024
#print ("received [%s]" % data)


client_sock.close()
server_sock.close()
