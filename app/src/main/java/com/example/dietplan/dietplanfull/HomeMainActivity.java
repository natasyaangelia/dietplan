package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dietplan.dietplanfull.fragment.HomeFragment;

public class HomeMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String username;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Home </font>"));
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        username = getIntent().getStringExtra("username");
        id = getIntent().getStringExtra("uid");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_content,  new HomeFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_main, menu);
        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.home) {

        } else if (id == R.id.energyDiary) {
            Intent intent = new Intent(getBaseContext(), EnergyDiaryMainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.energyrecipe) {
            Intent intent = new Intent(getBaseContext(), RecipeActivity.class);
            finish();
            startActivity(intent);
        } else if (id == R.id.gallery) {
            Intent intent = new Intent(getBaseContext(), Gallery1Activity.class);
            finish();
            startActivity(intent);
        } else if (id == R.id.article) {
            Intent intent = new Intent(getBaseContext(), ArticleActivity.class);
            finish();
            startActivity(intent);
        } else if (id == R.id.energyPoint) {
            Intent intent = new Intent(getBaseContext(), EnergyPointActivity.class);
            startActivity(intent);
        }else if (id == R.id.ask) {
            Intent intent = new Intent(getBaseContext(), AskAnExpertActivity.class);
            startActivity(intent);
        }else if (id == R.id.history) {
            Intent intent = new Intent(getBaseContext(), HistoryActivity.class);
            finish();
            startActivity(intent);
        }

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Home </font>"));
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
