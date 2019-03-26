# Trivia

In this application, the user will be able to play a game of trivia. The user can enter a name which will be recorded for the highscore.
At the end of the game the users score will be send to a server (along with their name) and a list of all the highscores will be viewed.

Trivia:
(!) known bug: 
-	when switching layouts (land/portrait) the questionList instance variable is somehow overridden. 
This did not happen before. Because of this, switching views mid trivia session gives an IndexOutOfBounds error now.
The landscape layout still works though, and can be tested by clicking "Start Trivia" already in landscape layout.

the newQuestion method used to work fine for switching layouts, I don't know why the questionList resets.

Feutures: 
+ Multiple (4) choice Trivia
+ (sort of) Functional Landscape Layout.
+ User can enter highscore name on start.
+ Custom Highscores Adapter
+ Randomizes position of answer buttons
+ Animations indicate correct/wrong answer (see 


# Intro Activity screenshots: 

![Intro Activity Portrait](/docs/portrait1.jpeg "Intro Activity Portrait")

![Intro Activity Landscape](/docs/landscape1.jpeg "Intro Activity Landscape")

# Trivia (Main) Activity screenshots: 

![Trivia Activity Portrait](/docs/portrait2_color.jpeg "Trivia Activity Portrait")

![Trivia Activity Landscape](/docs/landscape2.jpeg "Trivia Activity Landscape")

# Highscore Activity screenshots: 

![Highscore Activity Portrait](/docs/portrait3.jpeg "Highscore Activity Portrait")


