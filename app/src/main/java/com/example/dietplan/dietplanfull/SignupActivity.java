package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignupActivity extends AppCompatActivity {

    EditText mUsername, mEmail, mPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        mUsername = (EditText) findViewById(R.id.username);
        mEmail = (EditText) findViewById(R.id.emailLoginEditText);
        mPass = (EditText) findViewById(R.id.passwordLoginEditText);



    }

    public void nextLevel(View view) {

        String username = mUsername.getText().toString();
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if (mUsername.getText().length() == 0) {
            mUsername.setError("Field cannot be left blank.");
        } else if (mEmail.getText().length() == 0) {
            mEmail.setError("Field cannot be left blank.");
        } else if (mPass.getText().length() == 0) {
            mPass.setError("Field cannot be left blank.");
        } else {
            Intent intent = new Intent(SignupActivity.this, LevelActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
            intent.putExtra("pass",pass);
            startActivity(intent);
            finish();
        }
    }

    public void signupSosmed(View view){
        Intent intent = new Intent(SignupActivity.this, LevelActivity.class);
        intent.putExtra("username", "indahrendi");
        intent.putExtra("email", "indahrendi@gmail.com");
        intent.putExtra("pass","indahrendi");
        startActivity(intent);
        finish();
    }

    public void back(View view){
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
