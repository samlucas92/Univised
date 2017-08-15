package com.mal.univised;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class FinishActivity extends AppCompatActivity{
    private String test;

    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        ArrayList<String> res = new ArrayList<>();
        Intent intent = getIntent();
        res = intent.getStringArrayListExtra("results");
        Toast.makeText(FinishActivity.this,res.get(0).toString(), Toast.LENGTH_LONG).show();

        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinishActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
