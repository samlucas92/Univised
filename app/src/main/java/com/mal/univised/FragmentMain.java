package com.mal.univised;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentMain extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ImageView imageView;
    TextView tv_label;
    Spinner sp_university , sp_degree;
    Button btn_next;

    String universityType[] = {"abc", "xyz"};

    String degreeType[] = { "Arts", "Science", "Law", "Civil Law",
            "Engineering", "Education" };
    ArrayAdapter<String> adapterDegreeType, adapterUniversityType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);


         imageView = (ImageView) findViewById(R.id.imageView);
         tv_label = (TextView) findViewById(R.id.tv_label);
         sp_university = (Spinner) findViewById(R.id.sp_university);
         sp_degree = (Spinner) findViewById(R.id.sp_degree);
         btn_next = (Button) findViewById(R.id.btn_next);


        adapterUniversityType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, universityType);

        adapterUniversityType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_university.setAdapter(adapterUniversityType);

       adapterDegreeType = new ArrayAdapter<String>(this,
               android.R.layout.simple_spinner_item, degreeType);

        adapterDegreeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         sp_degree.setAdapter(adapterDegreeType);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(FragmentMain.this, FragmentPageActivity.class);
                i.putExtra("data",String.valueOf(sp_degree.getSelectedItem()));
                i.putExtra("data2",String.valueOf(sp_university.getSelectedItem()));
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();
        // Showing selected spinner item
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
