package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Energy Recipe</font>"));
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(RecipeActivity.this, HomeMainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void gotoRecipe1(View view) {
        Intent intent = new Intent(getBaseContext(), Recipe1Activity.class);
        startActivity(intent);
    }

    public void gotoRecipe2(View view) {
        Intent intent = new Intent(getBaseContext(), Recipe2Activity.class);
        startActivity(intent);
    }

    public void gotoRecipe3(View view) {
        Intent intent = new Intent(getBaseContext(), Recipe3Activity.class);
        startActivity(intent);
    }

    public void gotoRecipe4(View view) {
        Intent intent = new Intent(getBaseContext(), Recipe4Activity.class);
        startActivity(intent);
    }
}
