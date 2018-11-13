import sys
import signal
import os
import RPi.GPIO as GPIO
import time

"""
This code is the start to begin testing with the raspberry pi and application interaction.
Once working properly the code will make the LED light up everytime the up or down arrow is 
pressed and turn off when it's pressed again.
"""

pin = 21         # The pin connected to the LED
iterations = 10  # The number of times to blink
interval = .25   # The length of time to blink on or off
 
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(pin, GPIO.OUT)
 
# The parameters to "range" are inclusive and exclusive, respectively,
#  so to go from 1 to 10 we have to use 1 and 11 (add 1 to the max)
for x in range(1, iterations+1):
 
    print ("Loop %d: LED on")
    GPIO.output(pin, GPIO.HIGH)
    time.sleep(interval)
 
    print ("Loop %d: LED off")
    GPIO.output(pin, GPIO.LOW)
    time.sleep(interval)

