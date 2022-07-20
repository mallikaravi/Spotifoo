# SpotifooPlayer

## Create JAR file
- cd <project> i.e. spotifoo
- javac -d bin -cp src src/com/novare/spotifoo/*.*
- cd bin 
- jar -cvfm  ../Spotifoo.jar ../META-INF/MANIFEST.MF com/
- cd..
- java -jar Spotifoo.jar