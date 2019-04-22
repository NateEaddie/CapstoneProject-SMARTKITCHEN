#!/usr/bin/env python

from bluetooth import*
import os
import glob
import RPi.GPIO as GPIO
from time import sleep
import time
import socket
import subprocess as sp

    
#GPIO.setwarnings(False)
GPIO.setmode(GPIO.BCM)

# init list with pin numbers
pinList = [26, 19, 13, 6, 12, 16, 20, 21]

# loop through pins and set mode and state to 'low'
for i in pinList: 
    GPIO.setup(i, GPIO.OUT) 
    GPIO.output(i, GPIO.HIGH)

# time to sleep between operations in the main loop
SleepTimeL = 1


os.system("hciconfig hci0 piscan")

# The MAC address of a Bluetooth adapter on the server. The server might have multiple Bluetooth adapters.
hostMACAddress = '00:1f:e1:dd:08:3d' 
server_socket = BluetoothSocket(RFCOMM)
server_socket.bind(("",PORT_ANY))


#listens for data to be sent to the socket
server_socket.listen(1)
port = server_socket.getsockname()[1]

#identical UUID for connection to client

uuid = "a1bb5f8d-406d-4119-9cc8-f6c8395514ae"

#port = bluetooth.PORT_ANY #arbitrary choice. However, it must match the port used by the client.
size = 1024

#Set up for the client server connection
client_socket = None
stdoutdata = sp.check_output(["hcitool", "con"])
client_socket, address = server_socket.accept()

if client_socket == None:
    print ("No client")

print ("Connection established with: " , address)


#Loop attempts connection; if not connection, breaks
while(1):
    try:
        data = client_socket.recv(size).encode()
    except:
         break
    

    print ("recieved[%s] \n" % data)
    
    #Recieve data from client; sends signals to correct pins based on user input
    if data == "1":
        print("recieved 1\n")
        #LED_ON()
        GPIO.output(26, GPIO.LOW)
        GPIO.output(13, GPIO.LOW)
        GPIO.output(12, GPIO.LOW)
        GPIO.output(20, GPIO.LOW)
        time.sleep(SleepTimeL);
        GPIO.output(26, GPIO.HIGH)
        GPIO.output(13, GPIO.HIGH)
        GPIO.output(12, GPIO.HIGH)
        GPIO.output(20, GPIO.HIGH)
    elif data == "0":
        print("recieved 0\n")
        #LED_OFF()
        GPIO.output(19, GPIO.LOW)
        GPIO.output(6, GPIO.LOW)
        GPIO.output(16, GPIO.LOW)
        GPIO.output(21, GPIO.LOW)
        time.sleep(SleepTimeL)
        GPIO.output(19, GPIO.HIGH)
        GPIO.output(6, GPIO.HIGH)
        GPIO.output(16, GPIO.HIGH)
        GPIO.output(21, GPIO.HIGH)
    else:
        print("nothin\n")

#Close server and client sockets
client_socket.close()
server_socket.close()

#Clears the GPIO pins to allow reconnection
GPIO.cleanup()
#shell command to restart the Sever should the connection be broken 
os.execv('/home/pi/Desktop/TNW/testsever/Sever.py',  [''])