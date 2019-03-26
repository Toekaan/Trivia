package com.example.trivia;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question {
    private String category;
    private String type;
    // difficulty will also decide the amount of points gotten from the question
    private String difficulty;
    private String question;
    private String correctAnswer;
    private ArrayList<String> answers;

    public Question(String category, String type, String difficulty, String question, String correctAnswer, ArrayList<String> answers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }
        public String getCategory () {
            return category;
        }

        public String getType () {
            return type;
        }

        public String getDifficulty () {
            return difficulty;
        }

        public String getQuestion () {
            return question;
        }

        public String getCorrectAnswer () {
            return correctAnswer;
        }

        public ArrayList<String> getAnswers () {
            return answers;
        }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

}