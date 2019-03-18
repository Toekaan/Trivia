package com.example.trivia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class HighscoreList implements Serializable{
    private ArrayList<Highscore> highscoreList;

    public HighscoreList(ArrayList<Highscore> highscoreList) {
        this.highscoreList = highscoreList;
    }

    public ArrayList<Highscore> getHighscoreList() {
        return highscoreList;
    }

    public void setHighscoreList(ArrayList<Highscore> highscoreList) {
        this.highscoreList = highscoreList;
    }

}
