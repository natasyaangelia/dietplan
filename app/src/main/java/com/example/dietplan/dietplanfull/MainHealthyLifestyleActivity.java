package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;

public class MainHealthyLifestyleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_healthy_lifestyle);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Healthy Lifestyle</font>"));
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    public void gotoHealthy1(View view) {
        Intent intent = new Intent(getBaseContext(), Healthy1Activity.class);
        finish();
        startActivity(intent);
    }

    public void gotoHealthy2(View view) {
        Intent intent = new Intent(getBaseContext(), Healthy2Activity.class);
        finish();
        startActivity(intent);
    }

    public void gotoHealthy3(View view) {
        Intent intent = new Intent(getBaseContext(), Healthy3Activity.class);
        finish();
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(MainHealthyLifestyleActivity.this, ArticleActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
