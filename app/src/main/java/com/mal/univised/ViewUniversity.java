package com.mal.univised;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ViewUniversity extends AppCompatActivity{
    BLL bll = new BLL(this);
    TextView uniName, uniScore, warning, count;
    TableRow tr;
    public String uni = "";

    public String JSON_URL = "http://gamingcentury.com/univisedservice.php?";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_university);
        BLL.getInstance(this.getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        String searchQuery = bundle.getString("selected");
        String uni = "uni=" + searchQuery + "&num=150&format=json";
        JSON_URL = JSON_URL + uni;
        ArrayList<HashMap<String, String>> searchRes = bll.getUniByID(searchQuery);
        uniName = (TextView) findViewById(R.id.universityName);
        uniName.setText(searchRes.get(0).get("name"));
        count = (TextView) findViewById(R.id.reviewCount);
        listView = (ListView) findViewById(R.id.list);
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
                        Toast.makeText(ViewUniversity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){

        parseJSON pj = new parseJSON(json);
        pj.parseJSON();
        count.setText(parseJSON.count + " Reviews");
        CustomList cl = new CustomList(this, parseJSON.ids,parseJSON.title,parseJSON.first, parseJSON.last);
        listView.setAdapter(cl);
    }

}
