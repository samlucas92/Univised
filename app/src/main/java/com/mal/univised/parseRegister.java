package com.mal.univised;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by samlucas on 02/11/2016.
 */

public class parseRegister {

    public static String [] code;
    public static String [] error;
    public static String [] id;
    public static String [] first;
    public static String [] last;

    public static final String JSON_REG = "response";
    public static final String KEY_RESPONSE = "code";
    public static final String KEY_ERROR = "error";

    public static final String KEY_ID = "id";
    public static final String KEY_FIRST = "first";
    public static final String KEY_LAST = "last";

    private JSONArray log = null;
    private JSONArray user = null;

    private String json;

    public parseRegister(String json){
        this.json = json;
    }

    protected void parseRegister(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            log = jsonObject.getJSONArray(JSON_REG);

            code = new String[log.length()];
            error = new String[log.length()];

            for(int i=0;i<log.length();i++){
                JSONObject jo = log.getJSONObject(i);
                code[i] = jo.getString(KEY_RESPONSE);
                error[i] = jo.getString(KEY_ERROR);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void parseLogin(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            user = jsonObject.getJSONArray(JSON_REG);

            code = new String[user.length()];
            error = new String[user.length()];
            id = new String[user.length()];
            first = new String[user.length()];
            last = new String[user.length()];

            for(int i=0;i<user.length();i++){
                JSONObject jo = user.getJSONObject(i);
                code[i] = jo.getString(KEY_RESPONSE);
                error[i] = jo.getString(KEY_ERROR);
                id[i]=jo.getString(KEY_ID);
                first[i]=jo.getString(KEY_FIRST);
                last[i]=jo.getString(KEY_LAST);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
