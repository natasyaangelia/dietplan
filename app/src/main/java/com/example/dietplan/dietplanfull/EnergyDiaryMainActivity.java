package com.example.dietplan.dietplanfull;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dietplan.dietplanfull.model.EnergyDiary;
import com.example.dietplan.dietplanfull.model.History;
import com.example.dietplan.dietplanfull.networks.ModelManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnergyDiaryMainActivity extends AppCompatActivity {
    String idUser;
    TextView diaryMorning, diaryAfternoon, diaryEvening;
    SimpleDateFormat df;
    ArrayList<History> history = new ArrayList<>();
    ArrayList<History> historyToday = new ArrayList<>();
    String a="";

    ArrayList<EnergyDiary> energyDiaryMorning = new ArrayList<>();
    ArrayList<EnergyDiary> energyDiaryAfternoon = new ArrayList<>();
    ArrayList<EnergyDiary> energyDiaryEvening = new ArrayList<>();
    SharedPreferences session,sessionType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy_diary_main);
        session = getSharedPreferences("mySession",MODE_PRIVATE);
        sessionType = getSharedPreferences("mySessionType",MODE_PRIVATE);
        idUser = session.getString("uid","");
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Energy Diary</font>"));
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        diaryMorning = (TextView) findViewById(R.id.txtHistoryMorning);
        diaryAfternoon = (TextView) findViewById(R.id.txtHistoryAfternoon);
        diaryEvening = (TextView) findViewById(R.id.txtHistoryEvening);

        df = new SimpleDateFormat("MMM dd yyyy");

        ModelManager.Factory.getInstance().getAllHistories().enqueue(new Callback<ArrayList<History>>() {
            @Override
            public void onResponse(Call<ArrayList<History>> call, Response<ArrayList<History>> response) {
                history = response.body();

                if(response.code()==200) {
                    for(int i=0;i<history.size();i++){
                        if(history.get(i).getIdUser().equals(idUser)){
                            if(history.get(i).getDate().equals(df.format(new Date()))){
                                historyToday.add(history.get(i));
                            }
                        }
                    }

                    for(int i=0;i<historyToday.size();i++){
//                        Log.i("HISTORY TODAY",""+historyToday.get(i).getTypeSnack());
                            if(historyToday.get(i).getTypeSnack().equals("morning")){
                                a = historyToday.get(i).getIdEnergyDiary();
                                ModelManager.Factory.getInstance().getEnergyDiaryId(a).enqueue(new Callback<EnergyDiary>() {
                                    @Override
                                    public void onResponse(Call<EnergyDiary> call, Response<EnergyDiary> response) {
                                        EnergyDiary temp = response.body();
                                        energyDiaryMorning.add(temp);
                                        String s =( "<b>"+temp.getNama()+"</b> <br/>     "+temp.getKategori()+" - "+temp.getKalori()+"kkal<br/><br/>");
                                        diaryMorning.append(Html.fromHtml(s));
//                                        Log.i("HISTORY MORNING",""+temp.getNama()+" - "+temp.getKategori()+" - "+temp.getKalori()+"kkal\n");

                                        if(response.code()==200) {
                                        }
                                        else {Log.i("error",""+response.code());}
                                    }

                                    @Override
                                    public void onFailure(Call<EnergyDiary> call, Throwable t) {
                                        Log.i("error",t.toString());
                                    }
                                });

                            }else if(historyToday.get(i).getTypeSnack().equals("afternoon")){
                                a = historyToday.get(i).getIdEnergyDiary();
                                ModelManager.Factory.getInstance().getEnergyDiaryId(a).enqueue(new Callback<EnergyDiary>() {
                                    @Override
                                    public void onResponse(Call<EnergyDiary> call, Response<EnergyDiary> response) {
                                        EnergyDiary temp = response.body();
                                        energyDiaryAfternoon.add(temp);

                                        diaryAfternoon.append(""+temp.getNama()+" - "+temp.getKategori()+" - "+temp.getKalori()+"kkal\n");
//                                        Log.i("HISTORY AFTERNOON",""+temp.getNama()+" - "+temp.getKategori()+" - "+temp.getKalori()+"kkal\n");
                                        if(response.code()==200) {

                                        }
                                        else {Log.i("error",""+response.code());}
                                    }

                                    @Override
                                    public void onFailure(Call<EnergyDiary> call, Throwable t) {
                                        Log.i("error",t.toString());
                                    }
                                });
                            }else{
                                a = historyToday.get(i).getIdEnergyDiary();
                                ModelManager.Factory.getInstance().getEnergyDiaryId(a).enqueue(new Callback<EnergyDiary>() {
                                    @Override
                                    public void onResponse(Call<EnergyDiary> call, Response<EnergyDiary> response) {
                                        EnergyDiary temp = response.body();
                                        energyDiaryEvening.add(temp);
                                        diaryEvening.append(""+temp.getNama()+" - "+temp.getKategori()+" - "+temp.getKalori()+"kkal\n");
//                                        Log.i("HISTORY EVENING",""+temp.getNama()+" - "+temp.getKategori()+" - "+temp.getKalori()+"kkal\n");
                                        if(response.code()==200) {

                                        }
                                        else {Log.i("error",""+response.code());}
                                    }

                                    @Override
                                    public void onFailure(Call<EnergyDiary> call, Throwable t) {
                                        Log.i("error",t.toString());
                                    }
                                });
                            }

                        }

                    }
                else {Log.i("error",""+response.code());}
            }

            @Override
            public void onFailure(Call<ArrayList<History>> call, Throwable t) {
                Log.i("error",t.toString());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(EnergyDiaryMainActivity.this, HomeMainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void gotoSnack1(View view) {
        SharedPreferences.Editor editor = sessionType.edit();
        editor.putString("type", "morning");
        editor.commit();
        Intent intent = new Intent(getBaseContext(), DiaryFruitActivity.class);
        intent.putExtra("morning",true);
        finish();
        startActivity(intent);
    }
    public void gotoSnack2(View view) {
        SharedPreferences.Editor editor = sessionType.edit();
        editor.putString("type", "afternoon");
        editor.commit();
        Intent intent = new Intent(getBaseContext(), DiaryFruitActivity.class);
        intent.putExtra("afternoon",true);
        finish();
        startActivity(intent);
    }
    public void gotoSnack3(View view) {
        SharedPreferences.Editor editor = sessionType.edit();
        editor.putString("type", "night");
        editor.commit();
        Intent intent = new Intent(getBaseContext(), DiaryFruitActivity.class);
        intent.putExtra("evening",true);
        finish();
        startActivity(intent);
    }
}
