package com.example.dietplan.dietplanfull;

import android.content.Context;
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
import android.widget.Toast;

import com.example.dietplan.dietplanfull.model.EnergyDiary;
import com.example.dietplan.dietplanfull.model.History;
import com.example.dietplan.dietplanfull.model.User;
import com.example.dietplan.dietplanfull.model.UserRequest;
import com.example.dietplan.dietplanfull.networks.ModelManager;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryFruitActivity extends AppCompatActivity {

    int apel =0;
    int pepaya =0;
    int mangga =0;
    int pisang =0;
    int semangka =0;
    int kaloriapel=0;
    int kalorimangga =8;
    int kaloripepaya=12;
    int kaloripisang = 14;
    int kalorisemangka = 15;
    String userId;
    final History request = new History();
    TextView apelTx,manggaTx,pepayaTx,pisangTx,semangkaTx;
    SharedPreferences prefs,typeDiary; String[] idEnergyDiary = new String[6];
    TextView apelKal,manggaKal,pepayaKal,pisangKal,semangkaKal;
    ArrayList<EnergyDiary> v = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_fruit);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Energy Diary</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        initializationSet();
        apelTx.setText(""+apel);
        manggaTx.setText(""+mangga);
        pepayaTx.setText(""+pepaya);
        pisangTx.setText(""+pisang);
        semangkaTx.setText(""+semangka);
        apelKal.setText(""+apel);
        manggaKal.setText(""+mangga);
        pepayaKal.setText(""+pepaya);
        pisangKal.setText(""+pisang);
        semangkaKal.setText(""+semangka);

        ModelManager.Factory.getInstance().getEnergyDiary().enqueue(new Callback<ArrayList<EnergyDiary>>() {
            @Override
            public void onResponse(Call<ArrayList<EnergyDiary>> call, Response<ArrayList<EnergyDiary>> response) {
                if(response.code()==200){
                    v = response.body();
                    for(EnergyDiary energyDiary: v){
                        switch (energyDiary.getNama()){
                            case "Apel" : {
                                kaloriapel=energyDiary.getKalori();
                                idEnergyDiary[0]=energyDiary.getId();
                            }break;
                            case "Mangga" : {
                                kalorimangga=energyDiary.getKalori();
                                idEnergyDiary[1]=energyDiary.getId();
                            }break;
                            case "Pepaya" : {
                                kaloripepaya=energyDiary.getKalori();
                                idEnergyDiary[2]=energyDiary.getId();
                            }break;
                            case "Pisang Ambon" : {
                                kaloripisang=energyDiary.getKalori();
                                idEnergyDiary[3]=energyDiary.getId();
                            }break;
                            case "Semangka" : {
                                kalorisemangka=energyDiary.getKalori();
                                idEnergyDiary[4]=energyDiary.getId();
                            }break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EnergyDiary>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(DiaryFruitActivity.this, EnergyDiaryMainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void initializationSet(){
        prefs = getSharedPreferences("mySession", Context.MODE_PRIVATE);
        typeDiary = getSharedPreferences("mySessionType", Context.MODE_PRIVATE);
        apelTx =(TextView)findViewById(R.id.apel);
        manggaTx =(TextView)findViewById(R.id.mangga);
        pepayaTx =(TextView)findViewById(R.id.pepaya);
        pisangTx =(TextView)findViewById(R.id.pisang);
        semangkaTx =(TextView)findViewById(R.id.semangka);
        apelKal= (TextView) findViewById(R.id.apelKal);
        manggaKal= (TextView) findViewById(R.id.manggaKal);
        pepayaKal= (TextView) findViewById(R.id.pepayaKal);
        pisangKal= (TextView) findViewById(R.id.pisangKal);
        semangkaKal= (TextView) findViewById(R.id.semangkaKal);
    }

    public void gotoFruit(View view){
        Intent intent = new Intent(getBaseContext(), DiaryFruitActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoVegetable(View view){
        Intent intent = new Intent(getBaseContext(), DiaryVegaActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoProtein(View view){
        Intent intent = new Intent(getBaseContext(), DiaryProteinActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoDrink(View view){
        Intent intent = new Intent(getBaseContext(), DiaryDrinkActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoOtherSnack(View view){
        Intent intent = new Intent(getBaseContext(), DiarySnackActivity.class);
        startActivity(intent);
        finish();
    }

    public void gotoOat8(View view){
        Intent intent = new Intent(getBaseContext(), DiaryOatActivity.class);
        startActivity(intent);
        finish();
    }


    public void plusapel(View view){
        apel++;
        String temp = ""+ apel*kaloriapel;
        apelTx.setText(""+apel);
        apelKal.setText(temp);
    }

    public void plusmangga(View view){
        mangga++;
        String temp = ""+ mangga*kalorimangga;
        manggaTx.setText(""+mangga);
        manggaKal.setText(temp);
    }

    public void pluspepaya(View view){
        pepaya++;
        String temp = ""+ pepaya*kaloripepaya;
        pepayaTx.setText(""+pepaya);
        pepayaKal.setText(temp);
    }

    public void pluspisang(View view){
        pisang++;
        String temp=""+pisang*kaloripisang;
        pisangTx.setText(""+pisang);
        pisangKal.setText(temp);
    }

    public void plussemangka(View view){
        semangka++;
        String temp=""+semangka*kalorisemangka;
        semangkaTx.setText(""+semangka);
        semangkaKal.setText(temp);
    }

    public void minapel(View view){
        if(apel>0){
            apel--;
            String temp = ""+ apel*kaloriapel;
            apelTx.setText(""+apel);
            apelKal.setText(temp);
        }
    }

    public void minmangga(View view){
        if(mangga>0){
            mangga--;
            String temp = ""+ mangga*kalorimangga;
            manggaTx.setText(""+mangga);
            manggaKal.setText(temp);
        }
    }

    public void minpepaya(View view){
        if(pepaya>0){
            pepaya--;
            String temp = ""+ pepaya*kaloripepaya;
            pepayaTx.setText(""+pepaya);
            pepayaKal.setText(temp);
        }
    }

    public void minpisang(View view){
        if(pisang>0){
            pisang--;
            String temp=""+pisang*kaloripisang;
            pisangTx.setText(""+pisang);
            pisangKal.setText(temp);
        }
    }

    public void minsemangka(View view){
        if(semangka>0){
            semangka--;
            String temp=""+semangka*kalorisemangka;
            semangkaTx.setText(""+semangka);
            semangkaKal.setText(temp);
        }
    }

    public void addHistory(View view){


        //set tanggal
        Date date = new Date();
        String dates = ""+date;
        String[] split = dates.split(" ");
        final String tanggal = split[1]+ " "+split[2]+ " "+split[5];
        request.setDate(tanggal);

        //se tUserId
        userId = prefs.getString("uid","");
        request.setIdUser(userId);

        String type = typeDiary.getString("type","");
        request.setTypeSnack(type);

        for (int i =0;i<6;i++){
            switch(i){
                case 0:{
                    if(apel>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(apel*kaloriapel);
                        addDB();
                    }
                }break;
                case 1:{
                    if(mangga>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(mangga*kalorimangga);
                        addDB();
                    }
                }break;
                case 2:{
                    if(pepaya>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(pepaya*kaloripepaya);
                        addDB();
                    }
                }break;
                case 3:{
                    if(pisang>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(pisang*kaloripisang);
                        addDB();
                    }
                }break;
                case 4:{
                    if(semangka>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(semangka*kalorisemangka);
                        addDB();
                    }
                }break;
            }



        }
    }

    public void addDB(){
        ModelManager.Factory.getInstance().addEnergyDiary(request).enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                int code = response.code();
                if (code == 200) {
                    ModelManager.Factory.getInstance().getUser(userId).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if(response.code()==200){
                                User oldUser = response.body();
                                UserRequest request = new UserRequest();

                                request.setIdealCalori(oldUser.getIdealCalori());
                                request.setBmi(oldUser.getBmi());
                                request.setActivityLevel(oldUser.getActivityLevel());
                                request.setBirthday(oldUser.getBirthday());
                                request.setEmail(oldUser.getEmail());
                                request.setHeight(oldUser.getHeight());
                                request.setPassword(oldUser.getPassword());
                                request.setPoint(oldUser.getPoint()+5);
                                request.setWeight(oldUser.getWeight());
                                request.setUsername(oldUser.getUsername());

                                Log.i("msg1",""+oldUser.getPoint());
                                ModelManager.Factory.getInstance().addPoint(userId,request).enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        if(response.code()==200){
                                            Toast.makeText(getBaseContext(), "Success Add Your Diary Food", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(getBaseContext(), EnergyDiaryMainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
            }


            @Override
            public void onFailure(Call<History> call, Throwable t) {

            }
        });

    }

}
