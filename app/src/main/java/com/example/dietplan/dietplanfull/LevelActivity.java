package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LevelActivity extends AppCompatActivity {

    String username,pass,email;
    Button lv1,lv2,lv3,lv4;
    int level=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        getSupportActionBar().hide();
        username = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("pass");
        email = getIntent().getStringExtra("email");
        lv1 = (Button) findViewById(R.id.option1);
        lv2 = (Button) findViewById(R.id.option2);
        lv3 = (Button) findViewById(R.id.option3);
        lv4 = (Button) findViewById(R.id.option4);
    }

    public void back(View view){
        Intent intent = new Intent(getBaseContext(), SignupActivity.class);
        startActivity(intent);
        finish();
    }

    public void level1(View view) {
        level =1;
        lv1.setBackgroundDrawable(getResources().getDrawable(R.drawable.level1a));
        lv2.setBackgroundDrawable(getResources().getDrawable(R.drawable.level2));
        lv3.setBackgroundDrawable(getResources().getDrawable(R.drawable.level3));
        lv4.setBackgroundDrawable(getResources().getDrawable(R.drawable.level4));
        Log.i("isi Level1 ",""+level);
    }

    public void level2(View view) {
        level =2;
        Log.i("isi Level2 ",""+level);
        lv1.setBackgroundDrawable(getResources().getDrawable(R.drawable.level1));
        lv2.setBackgroundDrawable(getResources().getDrawable(R.drawable.level2a));
        lv3.setBackgroundDrawable(getResources().getDrawable(R.drawable.level3));
        lv4.setBackgroundDrawable(getResources().getDrawable(R.drawable.level4));
    }

    public void level3(View view) {
        level =3;
        Log.i("isi Level3 ",""+level);
        lv1.setBackgroundDrawable(getResources().getDrawable(R.drawable.level1));
        lv2.setBackgroundDrawable(getResources().getDrawable(R.drawable.level2));
        lv3.setBackgroundDrawable(getResources().getDrawable(R.drawable.level3a));
        lv4.setBackgroundDrawable(getResources().getDrawable(R.drawable.level4));
    }

    public void level4(View view) {
        level =4;
        Log.i("isi Level4 ",""+level);
        lv1.setBackgroundDrawable(getResources().getDrawable(R.drawable.level1));
        lv2.setBackgroundDrawable(getResources().getDrawable(R.drawable.level2));
        lv3.setBackgroundDrawable(getResources().getDrawable(R.drawable.level3));
        lv4.setBackgroundDrawable(getResources().getDrawable(R.drawable.level4a));
    }

    public void nextBirthday(View view) {

        if(level != 0){
            Log.i("isi Level ",""+level);
            Intent intent = new Intent(LevelActivity.this, BirthdayActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("email",email);
            intent.putExtra("pass",pass);
            intent.putExtra("level",""+level);
            startActivity(intent);
            finish();
        }
    }
}
