package com.example.trivia;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements TriviaRequest.Callback, Highscores.Callback {

    ArrayList<Question> questionList = new ArrayList<>();

    // keeps track of:
    int answer = 0; // user answer
    int count = 0; // current question
    int score = 0; // user score
    int correctAnswer; // the correct answer~
    String name = "Jandries"; // user should be able to fill in their own name

    // maximum amount of questions (might change later to be dynamic, if menu is ever added)
    int max = 1; // start counting from 0 aka 10.

    // initialize buttons to be used throughout mainactivity
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize answer buttons
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        // give wait indication to user


        // request trivia questions
        if (savedInstanceState == null) {
            Toast.makeText(this, "Loading Questions", Toast.LENGTH_SHORT).show();
            TriviaRequest req = new TriviaRequest(this);
            req.getQuestions(this);
        }
    }

    @Override
    public void gotQuestions (ArrayList<Question> questions) {
        questionList = questions;
        Log.d("TAG", questions.get(0).getCategory());

        // put information about first question into views
        newQuestion();
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    // changes the answer on button click
    public void answer1Clicked(View view) {
        answer = 1;

        // indicate selected answer to user
        answer1.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        answer2.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer3.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer4.setBackgroundColor(getResources().getColor(R.color.colorDefault));
    }
    public void answer2Clicked(View view) {
        answer = 2;

        answer1.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer2.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        answer3.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer4.setBackgroundColor(getResources().getColor(R.color.colorDefault));
    }
    public void answer3Clicked(View view) {
        answer = 3;

        answer1.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer2.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer3.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        answer4.setBackgroundColor(getResources().getColor(R.color.colorDefault));
    }
    public void answer4Clicked(View view) {
        answer = 4;

        answer1.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer2.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer3.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer4.setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    // go to next question
    public void submitClicked(View view) {
        // give user indication of what the right answer was
        if (correctAnswer == 1) {
            Helpers.animateButton(answer1);
        }
        else if (correctAnswer == 2) {
            Helpers.animateButton(answer2);
        }
        else if (correctAnswer == 3) {
            Helpers.animateButton(answer3);
        }
        else if (correctAnswer == 4) {
            Helpers.animateButton(answer4);
        }

        // check if user should be awarded any points
        Log.d("ANSWERS:", "USER: " + Integer.toString(answer) + " CORRECT: " + Integer.toString(correctAnswer));
        if (correctAnswer == answer) {
            score = score + getPoints();
            Log.d("SCORE", "Should've gone up...");
        }

        // wait for animation to finish
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                count = count + 1;
                // is the game over or not?
                if (max == count) {
                    Log.d("MAX == COUNT", "yes.");
                    // POST highscore to server first
                    Highscore highscore = new Highscore(score, name);
                    Highscores request = new Highscores(MainActivity.this);
                    request.postHighscore(MainActivity.this, highscore);

                    score = 0;
                    count = 0;
                }
                // next question!
                newQuestion();
            }
        }, 1500);
    }

    /** helper function to get the information of the current question into the views **/
    public void newQuestion() {
        // switch buttons back to default color.
        answer1.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer2.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer3.setBackgroundColor(getResources().getColor(R.color.colorDefault));
        answer4.setBackgroundColor(getResources().getColor(R.color.colorDefault));

        // get current question out of question list
        Question current = questionList.get(count);

        // initialize all necessary views
        TextView category = findViewById(R.id.category);
        TextView question = findViewById(R.id.question);
        TextView difficulty = findViewById(R.id.difficulty);
        TextView scoreText = findViewById(R.id.score);

        category.setText("Category: " + current.getCategory());
        question.setText(Html.fromHtml(current.getQuestion()));
        difficulty.setText(current.getDifficulty().toUpperCase() + "(" + Integer.toString(getPoints()) + " points)");

        scoreText.setText("Score: " +Integer.toString(score));

        // randomly fill in buttons with answers
        correctAnswer = Randomizer.generate(1,4);
        try {
            if (correctAnswer == 1) {
                answer1.setText(Html.fromHtml(current.getCorrectAnswer()));
                answer2.setText(Html.fromHtml(current.getAnswers().get(0)));
                answer3.setText(Html.fromHtml(current.getAnswers().get(1)));
                answer4.setText(Html.fromHtml(current.getAnswers().get(2)));
            } else if (correctAnswer == 2) {
                answer1.setText(Html.fromHtml(current.getAnswers().get(0)));
                answer2.setText(Html.fromHtml(current.getCorrectAnswer()));
                answer3.setText(Html.fromHtml(current.getAnswers().get(1)));
                answer4.setText(Html.fromHtml(current.getAnswers().get(2)));
            } else if (correctAnswer == 3) {
                answer1.setText(Html.fromHtml(current.getAnswers().get(0)));
                answer2.setText(Html.fromHtml(current.getAnswers().get(1)));
                answer3.setText(Html.fromHtml(current.getCorrectAnswer()));
                answer4.setText(Html.fromHtml(current.getAnswers().get(2)));
            } else if (correctAnswer == 4) {
                answer1.setText(Html.fromHtml(current.getAnswers().get(0)));
                answer2.setText(Html.fromHtml(current.getAnswers().get(1)));
                answer3.setText(Html.fromHtml(current.getAnswers().get(2)));
                answer4.setText(Html.fromHtml(current.getCorrectAnswer()));
            }
        }
        // only two answers?
        catch (IndexOutOfBoundsException e) {
            answer1.setText(current.getAnswers().get(0));
        }
    }

    /** helper function to get the amount of points based on difficulty of current question **/
    public int getPoints() {
        int points = 0;
        Question current = questionList.get(count);
        String difficulty = current.getDifficulty();
        if (difficulty.equals("easy")) {
            points = 1;
        }
        else if (difficulty.equals("medium")) {
            points = 2;
        }
        else if (difficulty.equals("hard")) {
            points = 3;
        }
        return points;
    }

    @Override
    public void sendHighscores() {
        Log.d("SEND POST", "we here.");

        // go to highscore activity
        Intent intent = new Intent(MainActivity.this, HighscoresActivity.class);
        startActivity(intent);
    }

    @Override
    public void sendHighscoresError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // put value just so savedinstanceState
        outState.putInt("answer", answer);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);=

        answer = savedInstanceState.getInt("answer");
        newQuestion();
    }

}
