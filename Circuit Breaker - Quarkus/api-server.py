from flask import Flask
import random
import json
app = Flask(__name__)

@app.route('/')
def index():
  return 'Server Works!'
  
@app.route('/greet')
def say_hello():
  return 'Hello from Server'

@app.route('/getVideoStream')
def getVideoStream():
    prob = random.random()
    if(prob > 0.8):
        quality = 'Very Good'
    elif(prob > 0.4):
        quality =  'Good'
    else:
        quality = 'poor'
        
    #return json.dumps({'video': 'videoStreamFile', 'quality': quality})
    return quality


@app.route('/getAudioStream')
def getAudioStream():
    prob = random.random()
    if(prob > 0.6):
        quality = 'Very Good'
    elif(prob > 0.2):
        quality =  'Good'
    else:
        quality = 'poor'
        
    #return json.dumps({'audio': 'audioStreamFilePlace', 'quality': quality})
    return quality


@app.route('/getChatMsg')
def getChatMsg():
    prob = random.random()
    if(prob > 0.2):
        quality = 'Very Good'
    elif(prob > 0.01):
        quality =  'Good'
    else:
        quality = 'poor'
        
    #return json.dumps({'chat': 'chatMessagesFile', 'quality': quality})
    return quality
