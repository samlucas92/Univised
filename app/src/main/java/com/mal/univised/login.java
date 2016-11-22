package com.mal.univised;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by samlucas on 01/11/2016.
 */

public class login extends Activity {

    public String response, error,uid,first,last,JSON_URL = "";
    TextView email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void loginClick(View view){
        email = (TextView)findViewById(R.id.input_email);
        pass = (TextView)findViewById(R.id.input_password);
        JSON_URL = JSON_URL + "http://gamingcentury.com/univisedlog.php?email=" + email.getText().toString() + "&pass=" + pass.getText().toString();
        sendRequest();
    }
    public void regClick(View view){
        Intent intent = new Intent(this,register.class);
        startActivity(intent);
    }

    private void sendRequest(){
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        parseRegister pr = new parseRegister(json);
        pr.parseLogin();
        response=parseRegister.code[0].toString();
        error=parseRegister.error[0].toString();
        uid = parseRegister.id[0].toString();
        first = parseRegister.first[0].toString();
        last = parseRegister.last[0].toString();
        if(response.equals("1")){
            Intent intent = new Intent(this,MainActivity.class);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("uid",uid);
            editor.putString("first",first);
            editor.putString("last",last);
            editor.commit();
            intent.putExtra("logged_in", 1);
            startActivity(intent);
        }else{
            Toast.makeText(login.this,error, Toast.LENGTH_LONG).show();
        }
    }
}
