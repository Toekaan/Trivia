package com.example.trivia;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoreListRequest implements Response.ErrorListener, Response.Listener<JSONArray> {

    private Context context;
    private HighscoreListRequest.Callback activity;

    private ArrayList<Highscore> returnlist = new ArrayList<>();

    String url = "https://ide50-toekaan.legacy.cs50.io:8080/highscores";

    public interface Callback {
        void gotHighscoreList(ArrayList<Highscore> highscoreList);
        void gotHighscoreListError(String message);
    }

    public HighscoreListRequest(Context context) {
            this.context = context;
        }
    public void getHighscores(HighscoreListRequest.Callback activity) {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
        queue.start();
        this.activity = activity;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotHighscoreListError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {
        try {
            // JSONArray scores = new JSONArray(response);
            ArrayList<Highscore> returnlist = new ArrayList<>();
            if (response != null) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject tempObject = (JSONObject) response.get(i);
                    Highscore temp = new Highscore(tempObject.getInt("score"), tempObject.getString("name"));
                    returnlist.add(temp);
                }
            }
            activity.gotHighscoreList(returnlist);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
