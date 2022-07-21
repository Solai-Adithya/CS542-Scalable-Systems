Commands to run the server from this directory:
1. export FLASK_ENV=development
2. export FLASK_APP=api-server.py
3. flask run

The APIs can be accessed at 
1. localhost:5000/getVideoStream
2. localhost:5000/getAudioStream
3. localhost:5000/getChatMsg

They return a JSON response like: {"video": "videoStreamFile", "quality": "Poor"}
The quality field is of interest to us.
