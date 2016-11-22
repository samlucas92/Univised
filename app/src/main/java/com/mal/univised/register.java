package com.mal.univised;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by samlucas on 01/11/2016.
 */

public class register extends Activity {
    public String response, error,JSON_URL = "";
    TextView first, last, email, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }
    public void RegisterUser(View view){
        first = (TextView)findViewById(R.id.input_firstname);
        last = (TextView)findViewById(R.id.input_lastname);
        email = (TextView)findViewById(R.id.input_email);
        pass = (TextView)findViewById(R.id.input_password);
        JSON_URL = JSON_URL + "http://gamingcentury.com/univisedreg.php?first=" + first.getText().toString() + "&last=" + last.getText().toString() + "&email=" + email.getText().toString() + "&pass=" + pass.getText().toString();
        //Toast.makeText(register.this,JSON_URL, Toast.LENGTH_LONG).show();
        sendRequest();
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
                        Toast.makeText(register.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){

        parseRegister pr = new parseRegister(json);
        pr.parseRegister();
        response=parseRegister.code[0].toString();
        error=parseRegister.error[0].toString();
        if(response.equals("1")){
            Intent intent = new Intent(this,successfulReg.class);
            startActivity(intent);
        }else{
            Toast.makeText(register.this,error, Toast.LENGTH_LONG).show();
        }
    }
}
