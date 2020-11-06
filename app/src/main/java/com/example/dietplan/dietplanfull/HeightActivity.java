package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HeightActivity extends AppCompatActivity {

    Spinner s;
    EditText s1;
    String username,pass,email,level,birthday;
    String tinggi,ukuran;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height);
        getSupportActionBar().hide();
        username = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("pass");
        email = getIntent().getStringExtra("email");
        level = getIntent().getStringExtra("level");
        birthday = getIntent().getStringExtra("birthday");

        List<String> list = new ArrayList<>();
        list.add("cm");
        list.add("feet");
        s = (Spinner) findViewById(R.id.spinnerId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, list);
        s.setAdapter(adapter);


        s1 = (EditText) findViewById(R.id.spinnerId1);



    }

    public void nextWeight(View view) {

        tinggi = s1.getText().toString();
        ukuran = s.getSelectedItem().toString();
        Log.i("This Level in height",level);
        if(tinggi.equalsIgnoreCase("")){
            Toast.makeText(getBaseContext(), "Please input your height", Toast.LENGTH_LONG).show();

        }else{
            Intent intent = new Intent(HeightActivity.this, WeightActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("email",email);
            intent.putExtra("pass",pass);
            intent.putExtra("level",level);
            intent.putExtra("birthday",birthday);
            intent.putExtra("tinggi",tinggi);
            startActivity(intent);
            finish();
        }
    }

    public void back(View view){
        Intent intent = new Intent(getBaseContext(), LevelActivity.class);
        startActivity(intent);
        finish();
    }

}
