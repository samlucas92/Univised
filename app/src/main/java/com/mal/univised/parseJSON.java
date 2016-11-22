package com.mal.univised;

import android.provider.Settings;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class parseJSON {
    public static String[] ids;
    public static String[] title;
    public static String[] first;
    public static String[] last;
    public static String[] body;
    public static String[] rating;

    public static int count = 0;
    public static final String JSON_ARRAY = "reviews";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "title";
    public static final String KEY_FIRST = "first";
    public static final String KEY_LAST = "last";
    public static final String KEY_BODY = "col_ad";
    public static final String KEY_RATING = "overall";


    private JSONArray users = null;

    private String json;

    public parseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);
            count = users.length();
            ids = new String[users.length()];
            title = new String[users.length()];
            first = new String[users.length()];
            last = new String[users.length()];
            body = new String[users.length()];
            rating = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                title[i] = jo.getString(KEY_NAME);
                first[i] = jo.getString(KEY_FIRST);
                last[i] = jo.getString(KEY_LAST);
                body[i] = jo.getString(KEY_BODY);
                rating[i] = jo.getString(KEY_RATING);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}