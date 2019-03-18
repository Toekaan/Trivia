package com.example.trivia;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trivia.Highscore;
import com.example.trivia.HighscoreList;
import com.example.trivia.R;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;

public class HighscoreListAdapter extends ArrayAdapter<Highscore> {

    private ArrayList<Highscore> highscores;

    public HighscoreListAdapter(Context context, int resource, ArrayList<Highscore> highscores) {
        super(context, resource);
        this.highscores = highscores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate layout
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore_item, parent, false);
        }

        // initialize views to be changed
        TextView number = convertView.findViewById(R.id.number);
        TextView name = convertView.findViewById(R.id.name);
        TextView score = convertView.findViewById(R.id.score);

        // get correct information for item
        Highscore convertScore = highscores.get(position);
        number.setText(Integer.toString(position));
        name.setText(convertScore.getName());
        score.setText(Integer.toString(convertScore.getScore()));
        return convertView;
    }

    // override getItem becuase of the way the adapter is implemented
    @Override
    public Highscore getItem(int position) {
        return highscores.get(position);
    }

    @Override
    public int getCount() {
        return highscores.size();
    }
}
