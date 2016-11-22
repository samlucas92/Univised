package com.mal.univised;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by samlucas on 21/08/2016.
 */
public class SearchActivity extends ListActivity {
    BLL bll = new BLL(this);
    //Intent intent = new Intent();
    TextView warning,search;
    TableRow tr;
    ArrayList<HashMap<String, String>> leaderList = new ArrayList<HashMap<String, String>>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BLL.getInstance(this.getApplicationContext());
        Bundle bundle = getIntent().getExtras();
        String searchQuery = bundle.getString("searchQuery");
        search = (TextView) findViewById(R.id.searchText);
        search.setText(searchQuery);
        ArrayList<HashMap<String, String>> searchRes =  bll.searchResults(searchQuery);
        createLeader(searchRes);

    }
    public void searchButtonClick(View view)
    {
        search = (TextView)findViewById(R.id.searchText);
        ArrayList<HashMap<String, String>> searchRes =  bll.searchResults(search.getText().toString());
        createLeader(searchRes);
    }
    public void createLeader(ArrayList<HashMap<String, String>> leaderList){
        tr = (TableRow)findViewById(R.id.tableRow1);

        warning = (TextView)findViewById(R.id.warning);
        if(leaderList.size()!=0) {
            SimpleAdapter adapter = new SimpleAdapter( SearchActivity.this,leaderList, R.layout.search_entry, new String[] {
                    "rankId","name","location"}, new int[] {R.id.rankId,R.id.name,R.id.location});
            setListAdapter(adapter);
            tr.setVisibility(View.VISIBLE);
            warning.setVisibility(View.GONE);
        }else{
            tr.setVisibility(View.GONE);
            warning.setVisibility(View.VISIBLE);
        }
    }
    public void resultButtonClick(View view)
    {
        TableRow tr = (TableRow) view;
        TextView id = (TextView)tr.getChildAt(1);
        String idString = id.getText().toString();

        Intent intent = new Intent(this, ViewUniversity.class);
        intent.putExtra("selected",idString);
        startActivity(intent);
    }

}

