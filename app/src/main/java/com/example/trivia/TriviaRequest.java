package com.example.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InterruptedIOException;
import java.util.ArrayList;

import javax.security.auth.callback.Callback;

public class TriviaRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private Callback activity;

    // indicates the current question number
    public int count = 0;

    // list of all the questions, requested on startup.
    private ArrayList<Question> questions = new ArrayList<>();

    String url = "https://opentdb.com/api.php?amount=10&type=multiple";

    public interface Callback {
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }

    public TriviaRequest(Context context) {
        this.context = context;
    }

    /** get all questions loaded on startup **/
    public void getQuestions(Callback activity) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest request = new JsonObjectRequest(url, null, this, this);
        queue.add(request);
        this.activity = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();
        activity.gotQuestionsError(error.getMessage());
    }

    // get all questions into a list
    @Override
    public void onResponse(JSONObject response) {
        try {
            // JSON results to be extracted into question objects
            JSONArray results = response.getJSONArray("results");

            // loop through JSON data, extract into question objects
            for (int i = 0; i < results.length(); i++) {
                // temporary data holder
                JSONObject temp = results.getJSONObject(i);

                // convert the incorrect answers from json to arraylist
                JSONArray incorrectJSON = temp.getJSONArray("incorrect_answers");
                ArrayList<String> answerList = new ArrayList<>();
                for (int y = 0 ; y < incorrectJSON.length(); y++) {
                    answerList.add(incorrectJSON.getString(y));
                }
                // add correct answer to the list, so later on we can randomly fill the buttons with this list
                //answerList.add(temp.getString("correct_answer"));

                // initialize question object via constructor
                Question question = new Question(
                        temp.getString("category"),
                        temp.getString("type"),
                        temp.getString("difficulty"),
                        temp.getString("question"),
                        temp.getString("correct_answer"),
                        answerList);

                // add question to questions list
                questions.add(question);
                count++;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        activity.gotQuestions(questions);
    }
}
