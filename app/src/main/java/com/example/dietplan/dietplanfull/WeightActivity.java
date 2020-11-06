package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dietplan.dietplanfull.model.User;
import com.example.dietplan.dietplanfull.model.UserRequest;
import com.example.dietplan.dietplanfull.networks.ModelManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeightActivity extends AppCompatActivity {

    Spinner s;
    EditText s1;
    float AMD=0;float TK=0;
    String username,pass,email,level,birthday,tinggi;
    String weight,ukuran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        getSupportActionBar().hide();
        username = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("pass");
        email = getIntent().getStringExtra("email");
        level = getIntent().getStringExtra("level");
        birthday = getIntent().getStringExtra("birthday");
        tinggi = getIntent().getStringExtra("tinggi");

        Log.i("isi", username);
        Log.i("isi", pass);
        Log.i("isi", email);
        Log.i("isi", level);
        Log.i("isi", birthday);
        Log.i("isi", tinggi);
        List<String> list = new ArrayList<>();
        list.add("kg");
        list.add("lbs");
        s = (Spinner) findViewById(R.id.spinnerId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_layout, list);
        s.setAdapter(adapter);

        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            list1.add("" + (i + 1));
        }
        s1 = (EditText) findViewById(R.id.spinnerId1);
    }

    public void back(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void startPage(View view) {

        weight = s1.getText().toString();
        if(weight.equalsIgnoreCase(""))
        {
            Toast.makeText(getBaseContext(), "Please input your Weight", Toast.LENGTH_LONG).show();
        }else{
            Log.i("isi",""+weight);
            Log.i("Tag","in to startPage");
            UserRequest request = new UserRequest();
            request.setEmail(email);
            request.setPassword(pass);
            request.setUsername(username);
            int tingkat = Integer.parseInt(level);
            request.setActivityLevel(tingkat);
            request.setBirthday(birthday);
            int height = Integer.parseInt(tinggi);
            request.setHeight(height);
            int berat = Integer.parseInt(weight);
            request.setWeight(berat);
            request.setPoint(0);

            Log.i("Tag","in to startPage1");
            String[] split = birthday.split(" ");
            int year= Integer.parseInt(split[2]);
            Date date = new Date();
            String years = ""+date;
            String[] split1 = years.split(" ");
            int age = Integer.parseInt(split1[5])-year;
            float mHeight= (float) ((float) height*0.01);
            float BMI = (float)berat /(mHeight* mHeight);
            request.setBmi((int)BMI);
            if(age <=3){
                AMD = (float) ((61.0 *(float) berat)+51);
            }else if(age>3 && age<=10){
                AMD = (float) ((22.5 *(float) berat)+499);
            }else if(age>10 && age<=18){
                AMD = (float) ((12.2 *(float) berat)+749);
            }else if(age>18 && age<=30){
                AMD = (float) ((14.7 *(float) berat)+496);
            }else if(age>30 && age<=60){
                AMD = (float) ((8.7 *(float) berat)+829);
            }else{
                AMD = (float) ((10.5 *(float) berat)+596);
            }


            switch (tingkat){
                case 1 : TK= (float) (1.56 * AMD);break;
                case 2 : TK= (float) (1.56 * AMD);break;
                case 3 : TK= (float) (1.76 * AMD);break;
                case 4 : TK= (float) (2.10 * AMD);break;
            }

            request.setIdealCalori((int)TK);

            request.setIdealSnackCalori(0);
            Log.i("Tag","in to startPage3");
            ModelManager.Factory.getInstance().signUp(request).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    int code = response.code();

                    if (code == 200) {
                        User body = response.body();
                        Intent intent = new Intent(WeightActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // error
                        try {
                            Log.i("TAG", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }


    }

}
