package com.example.dietplan.dietplanfull;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AskAnExpertActivity extends AppCompatActivity {

    EditText mEmail, mQuestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_an_expert);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Ask An Expert</font>"));
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        mQuestion = (EditText) findViewById(R.id.txtEditEmail);
        mEmail = (EditText) findViewById(R.id.txtEditYourQuestion);
    }

    @SuppressLint("LongLogTag")
    public void processSubmit(View view) {
        String question = mQuestion.getText().toString();
        String email = mEmail.getText().toString();

        if (mEmail.getText().length() == 0) {
            mEmail.setError("Field cannot be left blank.");
        } else if (mQuestion.getText().length() == 0) {
            mQuestion.setError("Field cannot be left blank.");
        } else {
            Log.i("Send email", "");

            String[] TO = {"stephanie_aqeilhera@yahoo.com"};
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.setType("text/plain");

            emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Question Diet Plan ");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "From : "+email+",Question : "+question);

            try {
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(AskAnExpertActivity.this,
                        "There is no email client installed.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(AskAnExpertActivity.this, HomeMainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
