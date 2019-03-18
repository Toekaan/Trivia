package com.example.trivia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HighscoresActivity extends AppCompatActivity implements HighscoreListRequest.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);

        // give wait indication to user
        Toast.makeText(this, "Loading Questions", Toast.LENGTH_SHORT).show();

        // request trivia questions
        HighscoreListRequest req = new HighscoreListRequest(this);
        req.getHighscores(this);
    }

    @Override
    public void gotHighscoreList(ArrayList<Highscore> highscoreList) {
        Collections.sort(highscoreList);

        Log.d("GOThighscores", highscoreList.get(0).getName());
        HighscoreListAdapter highscoreAdapter = new HighscoreListAdapter(this, R.layout.highscore_item, highscoreList);
        ListView listView = findViewById(R.id.highscoreList);
        listView.setAdapter(highscoreAdapter);
}

    @Override
    public void gotHighscoreListError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.d("gotHighscoreListError", message);
    }
}
