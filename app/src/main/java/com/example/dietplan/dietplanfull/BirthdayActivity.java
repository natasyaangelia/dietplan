package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class BirthdayActivity extends AppCompatActivity {

    Spinner s,s1,s2;
    String allDate;
    String username,pass,email,level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birthday);
        getSupportActionBar().hide();

        username = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("pass");
        email = getIntent().getStringExtra("email");
        level = getIntent().getStringExtra("level");

        List<Spanned> list = new ArrayList<>();
        for(int i=0; i<31; i++){
            Spanned tempS = Html.fromHtml("<font style='bold'>"+(i+1)+"</font>");
            list.add(tempS);
        }
        s = (Spinner) findViewById(R.id.spinnerId);
        ArrayAdapter<Spanned> adapter = new ArrayAdapter<Spanned>(this,
                R.layout.spinner_layout, list);
        s.setAdapter(adapter);

        List<Spanned> list1 = new ArrayList<>();
        for(int i=0; i<12; i++){
            Spanned tempS = Html.fromHtml("<font style='bold'>"+(i+1)+"</font>");
            list1.add(tempS);
        }
        s1 = (Spinner) findViewById(R.id.spinnerId1);
        ArrayAdapter<Spanned> adapter1 = new ArrayAdapter<Spanned>(this,
                R.layout.spinner_layout, list1);
        s1.setAdapter(adapter1);

        List<Spanned> list2 = new ArrayList<>();
        for(int i=0; i<100; i++){
            Spanned tempS = Html.fromHtml("<font style='bold'>"+(i+1950)+"</font>");
            list2.add(tempS);
        }
        s2 = (Spinner) findViewById(R.id.spinnerId2);
        ArrayAdapter<Spanned> adapter2 = new ArrayAdapter<Spanned>(this,
                R.layout.spinner_layout, list2);
        s2.setAdapter(adapter2);
    }

    public void back(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void nextHeight(View view) {
        String day = s.getSelectedItem().toString();
        String month = s1.getSelectedItem().toString();
        String year = s2.getSelectedItem().toString();
        allDate =day+" "+month+" "+year;
        Log.i("This Level in Birthday",level);
        Intent intent = new Intent(BirthdayActivity.this, HeightActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("email",email);
        intent.putExtra("pass",pass);
        intent.putExtra("level",level);
        intent.putExtra("birthday",allDate);
        startActivity(intent);
        finish();
    }
}
