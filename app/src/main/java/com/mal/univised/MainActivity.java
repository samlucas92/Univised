package com.mal.univised;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView search,welcome,username;
    Button logout,login;
    public String user = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        welcome = (TextView) findViewById(R.id.welcome);
        username = (TextView)findViewById(R.id.username);
        logout= (Button)findViewById(R.id.logout);
        login = (Button) findViewById(R.id.login);
        if (extras != null) {
            int isLogged = extras.getInt("logged_in",0);
            if (isLogged == 1) {
                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                user = prefs.getString("first","") + " " + prefs.getString("last","");

                username.setText(user);
                login.setVisibility(View.GONE);
                logout.setVisibility(View.VISIBLE);
                welcome.setVisibility(View.VISIBLE);
                username.setVisibility(View.VISIBLE);
            } else {
                // Do something else
            }
        }
        BLL bll = new BLL(this);
        //SQLiteDatabase db = bll.getWritableDatabase();
        //bll.onCreate(db);
        //bll.onUpgrade(db,4,5);
    }
    public void searchButtonClick(View view)
    {
        // Do something in response to the button
        Intent intent = new Intent(this, SearchActivity.class);
        search = (TextView)findViewById(R.id.searchText);
        intent.putExtra("searchQuery",search.getText().toString());
        startActivity(intent);
    }
    public void revButtonClick(View view){
        Intent intent = new Intent(this,FragmentMain.class);
        startActivity(intent);
    }
    public void loginClick(View view){
        Intent intent = new Intent(this,login.class);
        startActivity(intent);
    }
    public void logoutClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("logged_in",0);
        startActivity(intent);
    }
    public void favClick(View view){
        Intent intent = new Intent(this,ViewUniversity.class);

        startActivity(intent);
    }
    public void setClick(View view){
        Intent intent = new Intent(this,ViewUniversity.class);

        startActivity(intent);
    }





}
