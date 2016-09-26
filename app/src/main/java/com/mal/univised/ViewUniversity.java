package com.mal.univised;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewUniversity extends ListActivity {
    BLL bll = new BLL(this);
    TextView uniName, uniScore, warning;
    TableRow tr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_university);
        BLL.getInstance(this.getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        String searchQuery = bundle.getString("selected");
        ArrayList<HashMap<String, String>> searchRes = bll.getUniByID(searchQuery);
        uniName = (TextView) findViewById(R.id.universityName);
        uniName.setText(searchRes.get(0).get("name"));
        createReviews();
    }

    public void createReviews() {
        tr = (TableRow) findViewById(R.id.reviewRows);
        Bundle bundle = getIntent().getExtras();
        String searchQuery = bundle.getString("selected");

        getJson gj = new getJson();
        System.out.println("About to execute");
        gj.execute();
        warning = (TextView) findViewById(R.id.warning);
/*        if (reviewList.size() != 0) {
            ListAdapter adapter = new SimpleAdapter(ViewUniversity.this, reviewList, R.layout.review_entry, new String[]{
                    "rankId", "name"}, new int[]{R.id.reviewID, R.id.reviewTitle});
            setListAdapter(adapter);
            tr.setVisibility(View.VISIBLE);
            warning.setVisibility(View.GONE);
        } else {
            tr.setVisibility(View.GONE);
            warning.setVisibility(View.VISIBLE);
        }*/
    }


    private class getJson extends AsyncTask<String, Void, Void> {
        TextView t;
        String title, tester;
        @Override
        protected void onPreExecute() {
            // NOTE: You can call UI Element here.
            t = (TextView) findViewById(R.id.test);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... urls) {
            try{
                HttpClient hc = new DefaultHttpClient();
                HttpPost hp = new HttpPost("http://www.gamingcentury.com/univisedservice.php");
                ArrayList<NameValuePair> ar = new ArrayList<NameValuePair>();
                ar.add(new BasicNameValuePair("uni","1"));
                ar.add(new BasicNameValuePair("num","10"));
                ar.add(new BasicNameValuePair("format","json"));

                hp.setEntity(new UrlEncodedFormEntity(ar));
                HttpResponse hr = hc.execute(hp);

                InputStream is = hr.getEntity().getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line;
                while((line = br.readLine()) != null){
                    sb.append(line + "\n");
                }
                is.close();
                String result = sb.toString();
                tester = result;
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("reviews");

                for(int i = 0; i<jsonArray.length(); i++){
                    JSONObject jo = jsonArray.getJSONObject(i);
                    JSONObject jsonObject1 = jo.getJSONObject("reviews");
                    title = jsonObject1.getString("title");
                }
            }catch(Exception e){

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void unused) {
            t.setText(title);
            System.out.println("Title = " + title);
            System.out.println("Res test = " + tester);
            System.out.println("HERE");
        }
    }
}
