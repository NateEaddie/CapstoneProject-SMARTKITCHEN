''''source: https://people.csail.mit.edu/albert/bluez-intro/x290.html'''



import sys
import bluetooth


uuid = "1e0ca4ea-299d-4335-93eb-27fcfe7fa848"

#This code here searches for servers with the matching uuid and stores them in service_matches (a vector)
service_matches = bluetooth.find_service( uuid = uuid )

#if the vector lenght is 0, no servers found
if len(service_matches) == 0:
    print ("couldn't find the FooBar service")
    sys.exit(0)



first_match = service_matches[0]
port = first_match["port"]
name = first_match["name"]
host = first_match["host"]

print ("connecting to \"%s\" on %s" % (name, host))

sock=bluetooth.BluetoothSocket( bluetooth.RFCOMM )  #declaring a socket obj of RFCOMM

sock.connect((host, port))      #connect to host port
sock.send("hello!!")            #send data
sock.close()                    #close socket
