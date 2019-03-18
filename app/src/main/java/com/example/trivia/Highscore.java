package com.example.trivia;

import java.io.Serializable;

public class Highscore implements Serializable, Comparable<Highscore> {
    private int score;
    private String name;

    // we can add things like 'type' later on, maybe

    public Highscore(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Highscore o) {
        return o.score - this.score;
    }
}
