
import serial
import sys
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
PAHT_CRED = '/home/pi/cred.json'
URL_DB = 'https://trackfm-41a3e-default-rtdb.firebaseio.com/'


cred = credentials.Certificate(PAHT_CRED)
firebase_admin.initialize_app( cred, { 'databaseURL' : URL_DB })
ref = db.reference('/datos')
ser= serial.Serial('/dev/ttyUSB0', 9600)
ser.flushInput()
while (True) :
        try:
                print('START !')
                lineB= ser.readline()
                line= lineB.decode('utf-8').strip()
                print(line)
                voltaje = (int(line)/336)*40
                amp =(int(line)/336)*120
                poter=(int(line) /2)
                ref.push({ 'potencia': line , 'potenciare': poter, 'voltaje': voltaje , 'corriente': amp })

        except KeyboardInterrupt:
                break

