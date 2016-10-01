package com.mal.univised;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class parseJSON {
    public static String[] ids;
    public static String[] title;
    public static String[] first;
    public static String[] last;
    public static int count = 0;
    public static final String JSON_ARRAY = "reviews";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "title";
    public static final String KEY_FIRST = "first";
    public static final String KEY_LAST = "last";

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

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                title[i] = jo.getString(KEY_NAME);
                first[i] = jo.getString(KEY_FIRST);
                last[i] = jo.getString(KEY_LAST);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}