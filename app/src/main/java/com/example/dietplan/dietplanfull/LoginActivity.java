package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dietplan.dietplanfull.model.User;
import com.example.dietplan.dietplanfull.model.UserRequest;
import com.example.dietplan.dietplanfull.networks.ModelManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText mUsername,mPass;
    SharedPreferences session;
    String username,pass;
    final UserRequest request = new UserRequest();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        MultiDex.install(this);
        initializationControl();

    }

    public void initializationControl(){
        session = getSharedPreferences("mySession",MODE_PRIVATE);
        mUsername = (EditText) findViewById(R.id.emailLoginEditText);
        mPass = (EditText) findViewById(R.id.passwordLoginEditText);
    }

    public void processSignup(View view) {

        final Thread t = new Thread(){
            @Override
            public void run() {
                username = mUsername.getText().toString();
                pass = mPass.getText().toString();
                request.setUsername(username);
                request.setPassword(pass);

                if(mUsername.getText().length()==0){
                    mUsername.setError("Field cannot be left blank.");
                }
                else if(mPass.getText().length()==0){
                    mPass.setError("Field cannot be left blank.");
                }
                else {
                    login();
                }
            }
        };

        t.start();
    }

    public void loginSosmed(View view){
        username = "indahrendi";
        pass = "indahrendi";
        request.setUsername(username);
        request.setPassword(pass);
        login();
    }

    public void back(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void login(){
        ModelManager.Factory.getInstance().login(request).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int code = response.code();
                Log.i("TAG", "lalala "+code);
                if (code == 200) {
                    SharedPreferences.Editor editor = session.edit();
                    editor.putString("username", username);
                    editor.putString("uid", response.body().getUid());
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, HomeMainActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("uid", response.body().getUid());
                    startActivity(intent);
                    finish();
                } else {
                    // error
                    try {
                        Log.i("TAG", response.errorBody().string());
                        Toast.makeText(getBaseContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(getBaseContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
