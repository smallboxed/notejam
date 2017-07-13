# notejam
a really really really basic note taking api. (seriously...its super basic.)

steps to run:
1) clone or download contents to local folder
2) In either terminal (mac or linux) or cmd panel (windows) cd into downloaded directory
3) run "java -jar notejam.jar" to start server
4) api location is localhost:8080/api/notes. 

GET to api/notes will return all available notes
GET to api/notes?query={enter query here} will return a result set based on query
GET to api/notes/{id} will return only the note with that id

POST text string to api/notes will create a new note.

notes are persisted in a local version of sqlite db.  
java version should be 1.7 or higher. 




