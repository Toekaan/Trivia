package com.example.trivia;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** highscore helper class. POSTs highscores to server**/
public class Highscores implements Response.Listener<String>, Response.ErrorListener{

    private Context context;
    private Highscores.Callback activity;

    String url = "https://ide50-toekaan.legacy.cs50.io:8080/highscores";

    public  interface Callback {
        void sendHighscores();
        void sendHighscoresError(String message);
    }

    public Highscores(Context context) {
        this.context = context;
    }





    /** post highscore to highscore list**/
    public void postHighscore(Highscores.Callback activity, final Highscore highscore) {

        // help with POST request source: (https://gist.github.com/mombrea/7250835)

        StringRequest request = new StringRequest(Request.Method.POST, url, this, this){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parameters = new HashMap<>();
                parameters.put("name", highscore.getName());
                parameters.put("score", Integer.toString(highscore.getScore()));
                return parameters;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
        queue.start();
        this.activity = activity;
        activity.sendHighscores();

        /*JSONObject highscoreJSON = new JSONObject();
        try {
            highscoreJSON.put("name", highscore.getName());
            highscoreJSON.put("score", Integer.toString(highscore.getScore()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            Log.d("postHighscore", highscoreJSON.getString("score"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, highscoreJSON, this, this);
        */

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.sendHighscoresError(error.getMessage());
        // activity.gotHighscoresError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        Log.d("Response", response);

    }

}
