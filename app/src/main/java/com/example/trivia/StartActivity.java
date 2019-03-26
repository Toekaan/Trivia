package com.example.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    // intent and view to be used later
    Intent intent;
    EditText nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        intent = new Intent(StartActivity.this, MainActivity.class);
        nameView = findViewById(R.id.editText);
    }

    //send name and user to main activity
    public void startClicked(View view) {
        String name = nameView.getText().toString();
        // ensure correct usage
        if (name.equals("") || name.length() < 2) {
            nameView.setText("Enter atleast 3 characters");
        }
        // send user off
        else {
            intent.putExtra("name", name);
            startActivity(intent);
        }
    }
}
