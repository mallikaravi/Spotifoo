`		`Spotifoo Player

# Table of Contents
<!--ts-->
   * [About](#about)
   * [Project Overview](#project-overview)
      * [Play Song](#play-song)
      * [Filter By Artist](#filter-by-artist)
      * [Filter By Album](#filter-by-album)
      * [Filter By Genre](#filter-by-genre)
      * [Search](#search)
   * [Implementation And Design](#implementation-and-design)
      * [Technologies](#technologies)
      * [Design and Flow diagram](#design-and-flow-diagram)
        * [Architecture and Design](#architecture-and-design)
        * [Class Diagram](#class-diagram)
        * [Sequence Diagram](#sequence-diagram)
        * [Model Diagram](#model-diagram)  
        * [Menus flow charts](#menus-flow-charts)  
        * [Screens](#screens)
  * [Build and Run](#build-and-run)
<!--te-->

# About
Spotifoo is command line interface application, that allows you to stream music instead of downloading it from an unknown source. The user can listen to the songs according to his choice and he can able to choose the song based on his interest. We can only navigate by typing a number from an option list and then press enter to execute the command. Typing 0 and pressing enter in any part of the application will send you back to the main menu. This application is providing features to users like display all the songs, by category (album, artist and genre) and searching the songs by name.
# Project Overview
The application is a command line interface (terminal) application that does not have a graphical user interface. The user only navigates by typing a number from an option list and then press enter to execute the command. Typing 0 and pressing enter in any part of the application will send you back to the main menu.
## Play Song
When pressing option #1 on the main menu, the app must show a list of songs by reading the text file located inside the asset folder. When the user chooses a specific song, the music file should open in the default music player installed in your computer. In addition, it should also open the corresponding album art. Or show a placeholder image if the album art is not found.

**Note:** Only open the album art, if the app can play the song.
## Filter By Artist
When pressing option #2 on the main menu, the app will replace the main menu options with a list of artists available based on the .txt file.  Typing the number corresponding to an artist will in turn, show a list of songs that belong to this artist.
## Filter By Album
When pressing option #3 on the main menu, the app will replace the main menu options with a list of albums available based on the .txt file. 
## Filter By Genre
When pressing option #4 on the main menu, the app should show a list of genres. The only four genres available are: 

1. Metal
2. Pop
3. Reggaeton
4. Rock
## Search
When pressing option #5 on the main menu, the app should allow to perform a search based solely on the name of the songs.  The app must prompt the user to input some text. Then the app must display a list of songs that matches the whole text or part of the text inside the name of a song.<br>
**Example:** The assets folder mentioned in the section “what we provide” has these songs:

- Welcome to the Jungle (Guns and Roses)
- Welcome to the family (Avenged Sevenfold)

If the user types “welcome” it should return both results and any other that matches the text.  But if the user types “welcome to the jungle” it should only return the song from Guns and Roses. 
## Sample data
Spotifoo has permission from top artists around the world, to share their songs with us. Thus, we created a folder called **assets** with 2 sub folders. One for the music files with a bunch of .mp3 files and another for the album art in .png files.

We also provide a file called data.txt with the information about the song name, artist, album, genre, file name, and album image. Your app must read this file to display the list of songs and other features requested in the mandatory requirements listed below.

**Note:** The folder and text file, contain some invalid data on purpose. Do not try to edit the text inside data.txt or add missing files. Instead, write code to validate the errors.

# Implementation and Design 
## Technologies
The following technologies are used in designing and developing the Spotifoo application.

1. Java – Coding language for implementing the Spotifoo application
2. Eclipse - IDE Purpose 
3. Visual Paradigm - UML Diagram tool
4. Microsoft word – Document preparation
## Design and Flow diagram
### Architecture and design 
Simple MVC architecture shall be followed in designing the Spotifoo application, see below. Here Song, Artist, Album and Genre shall be acted as module, the controller shall be followed by a *Singleton* design pattern, it reads the songs data from the file which is provided by the customer and prepare the model object. This functionality shall be done only once and maintained model objects in the form of database cache using collections API.

Next, the view module is mainly responsible for preparing the menus by using controller cache data based on the *User* choice.

![](design/architecture.png)
### Class diagram
This section shall be explained about the relationship between different classes.

**Model**: All the model classes shall be followed java bean’s structure and implemented Comparable* interface in order maintain the order while caching the data into collections API.

**Controller**: The *SpotifooController.java* is controller class which follows the singleton design pattern to maintain the single instance, it reads the customer provided songs data file in fixed format (i.e., comma separated values, see below) and caches the songs data in-memory in structured format for lateral usage.

***Example***: <song\_name>,<artist\_name>,<album\_name>,<genre\_name>,<file\_name>,<image>

*”Andy you are a star,The Killers,Hot fuss,rock,andy-you-are-a-star.mp3,hot-fuss.png”*

**View:** The *SpotifooView.java* is responsible for building the user interface and displayed in the terminal/console based on the user inputs and performs the validation checks. 

1. Title enumeration shall be used for constants purpose and holds the menus title.
2. Icon enumeration shall be holding the *Unicode characters* for icons which shall be shown along with the warning/errors message in the terminal.
3. *SpotifooApp.java* is a main class for the application and it invokes the start method of *SpotifooView* class.

![](design/classdiagram.png)


### Sequence diagram
The sequence diagram explains about the logic interaction between the objections in the system and execution flow.

![](design/sequencediagram.png)

### Model diagram 
The below picture explains about the module relationship and the below four models are introduced in Spotifoo application.

1. Songs.java – Responsible for holding the songs metadata like.

| Field Name   |      Description      |
|--------------|:--------------------- |
|Name          |Name of the song.      |
|File name     |Song file name with mp3 extension|
|Image         |Image name of the song |
|Artist        |Artist name of the song|
|Genre         |Genre name of the song |
|Album         |Album name of the song |

2. Album.java – Maintains the Album metadata like,

| Field Name   |      Description      |
|--------------|:--------------------- |
|Name|Name of the album|
|Songs|List of songs which are belongs to this album|

3. Artist.java – Responsible for holding the song artist metadata.

| Field Name   |      Description      |
|--------------|:--------------------- |
|Name|Name of the artist|
|Songs|List of songs which are belongs to this Artist|

4. Genre.java - Responsible for holding the song Genre metadata.

| Field Name   |      Description      |
|--------------|:--------------------- |
|Name|Name of the genre|
|Songs|List of songs which are belongs to this Genre|

![](design/modeldiagram.png)

### Menus flow charts 
This section helps to identify the different menus mandatory steps and explains the execution flow.
#### *Main*
The main menu shall be shown to the user initially, it will provide few options as described in below picture.

![](design/mainmenuflowchart.png)

Here user has an option to choose the numbers between 1-5, once user enters the value in specified range that specific functionality shall be executed and moved into the next menu. 

**Error**: Any input that entered the user other than 1-5 that shall be treated as an Invalid case and wait for the further user input.

#### *Songs*
The songs menu shall be shown to the user in different scenarios, i.e., from *Main Menu, Artists, Albums, Genre* and *Search* see below picture.

![](design/songsmenuflowchart.png)

For example, if user has chosen the option “1” from *Main Menu* then all the songs shall be displayed into songs menu.

Here user has an option to choose any of the song between [1-N], assume that there is more than 1 song and Option “0” take back to main menu.

Now, the user selected song shall be played in system default media player and song image is also opened in system default image viewer. Note that if the song is valid and it doesn’t contain any image then default image is opened. 

**Error**: Any input that entered the user other than “0” and songs range, that shall be treated as an Invalid case and wait for the further user input.


#### *Artist*
The artist menu shall be shown in the main menu. For Example, if the user enters an option of “2”, then all the artists shall be displayed in the terminal.

![](design/artistmenuflowchart.png)

Here the user has an option to choose any of the artist between [1-N], assume that there is more than 1 artist and Option “0” take back to main menu.

Now, the user selected artist shall go the songs menu Ref [Songs](#songs)

**Error**: Any input that entered the user other than “0” and album range, that shall be treated as an Invalid case and wait for the further user input.

#### *Album*
The album menu shall be shown in the main menu. For Example, if the user enters an option of “3”, then all the albums shall be displayed in the terminal.

![](design/albummenuflowchart.png)

Here the user has an option to choose any of the album between [1-N], assume that there is more than 1 artist and Option “0” take back to main menu.

Now, the user selected album shall go the songs menu Ref [Songs](#songs)

**Error**: Any input that entered the user other than “0” and album range, that shall be treated as an Invalid case and wait for the further user input.

#### *Genre*
The Genre menu shall be shown in the main menu. For Example, if the user enters an option of “4”, then all the Genre shall be displayed in the terminal.

![](design/genremenuflowchart.png)


Here the user has an option to choose any of the Genre between [1-N], assume that there is more than 1 genre and Option “0” take back to main menu.

Now, the user selected genre shall go the songs menu Ref [Songs](#songs)

**Error**: Any input that entered the user other than “0” and genre range, that shall be treated as an Invalid case and wait for the further user input.

#### *Search*
The search option shall be shown in the main menu. For Example, if the user enters an option of “5”, then the user shall be asked to search for a song.

![](design/searchmenuflowchart.png)

Here the user shall search for a song by its word and the corresponding songs that matches the word shall be displayed in the songs menu Ref [Songs](#songs).

**Error**: Any input that entered the user other than song name, shall be treated as an query and the user exits from the application.

### Screens
#### *Main Menu*
This is the Main Menu of the Spotifoo Music App. When the user starts the application,the following screen is displayed in the terminal.
![](design/screenshotofmainmenu.png)

**Error**: This is the error message of the application. When the user enters invalid option, he gets the following output.

![](design/screenshotoferrormessage.png)


#### *Songs Menu*
This is the output of the Songs Menu.  If user has chosen the option “1” from *Main Menu* then all the songs shall be displayed into songs menu

![](design/screenshotofsongsmenu.png)

**Error**: This is the error message of the songs menu. When the user enters invalid option, he gets the following output.

![](design/screenshotoferrormessage.png)

#### *Artist Menu*
This is the output of the Artist Menu.  If user has chosen the option “2” from *Main Menu* then all the   artist shall be displayed into artist menu.

![](design/screenshotofartistmenu.png)

**Error**: This is the error message of the artist menu. When the user enters invalid option, he gets the following output.

![](design/screenshotoferrormessage.png)

#### *Album Menu*
This is the output of the Album Menu. If user has chosen the option “3” from *Main Menu* then all  the albums shall be displayed into album menu.

![](design/screenshotofalbummenu.png)

**Error**: This is the error message of the album menu. When the user enters invalid option, he gets the following output.

![](design/screenshotoferrormessage.png)

#### *Genre Menu*
This is the output of the Genre Menu.  If user has chosen the option “4” from *Main Menu* then all the genre shall be displayed into genre menu.

![](design/screenshotofgenremenu.png)

**Error**: This is the error message of the genre menu. When the user enters invalid option, he gets the following output.

![](design/screenshotoferrormessage.png)

#### *Search Menu*
This is the output of the Search menu.  If user has chosen the option “5” from *Main Menu* then   user shall search for a song by its word and the corresponding songs that matches the word shall be displayed in the songs menu.

![](design/screenshotofsearch.png)

# Build and Run
Git clone (download the soruce code)
```bash
$ git clone https://github.com/mallikaravi/Spotifoo.git
$ cd Spootifoo
```
Build and Generate the JAR file (compile the soruce code)
```bash
$ javac -d bin -cp src src/com/novare/spotifoo/*.*
$ cd bin
$ jar -cvfm ../Spotifoo.jar ../META-INF/MANIFEST.MF com/
$ cd..
```
Run the Spootifoo appliction
```bash
$ java -jar Spotifoo.jar
```
