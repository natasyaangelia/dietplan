package com.example.dietplan.dietplanfull;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;

import com.example.dietplan.dietplanfull.adapter.HistoryAdapter;
import com.example.dietplan.dietplanfull.model.History;
import com.example.dietplan.dietplanfull.networks.ModelManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView mRview;
    ArrayList<History> data = new ArrayList<>();
    SharedPreferences prefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        prefs = getSharedPreferences("mySession", Context.MODE_PRIVATE);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>History</font>"));
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);


        mRview = (RecyclerView) findViewById(R.id.listHistories);

        ModelManager.Factory.getInstance().getAllHistories().enqueue(new Callback<ArrayList<History>>() {
            @Override
            public void onResponse(Call<ArrayList<History>> call, Response<ArrayList<History>> response) {
                if(response.code()==200)
                {
                    ArrayList<History> userHistory = new ArrayList<History>();
                    data = response.body();
                    final String userId = prefs.getString("uid","");
                    for(History hist : data){
                        if(userId.equalsIgnoreCase(hist.getIdUser())){
                            userHistory.add(hist);
                        }
                    }
                    Log.i("error","success "+response.code());
                    HistoryAdapter adapterHistories = new HistoryAdapter(userHistory);
                    mRview.setAdapter(adapterHistories);
                }
                else
                {
                    Log.i("error",""+response.code());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<History>> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
        LinearLayoutManager managerFriend = new LinearLayoutManager(this);
        mRview.setLayoutManager(managerFriend);
        mRview.setHasFixedSize(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(HistoryActivity.this, HomeMainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
