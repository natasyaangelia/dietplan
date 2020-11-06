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

public class DiaryDrinkActivity extends AppCompatActivity {

    int mineral =0;
    int madu =0;
    int jeruk =0;
    int lemon =0;
    int kelapa =0;
    int kaloriMineral=0;
    int kaloriJeruk =8;
    int kalorimadu=12;
    int kaloriLemon = 14;
    int kaloriKelapa = 15;
    boolean point = false;
    final History request = new History();
    String userId="";
    SharedPreferences prefs,typeDiary; String[] idEnergyDiary = new String[6];
    ArrayList<EnergyDiary> v = new ArrayList<>();
    TextView mineralTx,jerukTx,maduTx,lemonTx,kelapaTx;
    TextView mineralKal,jerukKal,maduKal,lemonKal,kelapaKal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_drink);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Energy Diary</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        initializationSet();
        mineralTx.setText(""+mineral);
        jerukTx.setText(""+jeruk);
        maduTx.setText(""+madu);
        lemonTx.setText(""+lemon);
        kelapaTx.setText(""+kelapa);
        mineralKal.setText(""+mineral);
        jerukKal.setText(""+jeruk);
        maduKal.setText(""+madu);
        lemonKal.setText(""+lemon);
        kelapaKal.setText(""+kelapa);

        ModelManager.Factory.getInstance().getEnergyDiary().enqueue(new Callback<ArrayList<EnergyDiary>>() {
            @Override
            public void onResponse(Call<ArrayList<EnergyDiary>> call, Response<ArrayList<EnergyDiary>> response) {
                if(response.code()==200){
                    v = response.body();
                    for(EnergyDiary energyDiary: v){
                        switch (energyDiary.getNama()){
                            case "Air Mineral" : {
                                kaloriMineral=energyDiary.getKalori();
                                idEnergyDiary[0]=energyDiary.getId();
                            }break;
                            case "Jus Jeruk" : {
                                kaloriJeruk=energyDiary.getKalori();
                                idEnergyDiary[1]=energyDiary.getId();
                            }break;
                            case "Madu" : {
                                kalorimadu=energyDiary.getKalori();
                                idEnergyDiary[2]=energyDiary.getId();
                            }break;
                            case "Lemon Tea" :{
                                kaloriLemon=energyDiary.getKalori();
                                idEnergyDiary[3]=energyDiary.getId();
                            }break;
                            case "Air Kelapa Muda" : {
                                kaloriKelapa=energyDiary.getKalori();
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
                Intent intent = new Intent(DiaryDrinkActivity.this, EnergyDiaryMainActivity.class);
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
        mineralTx =(TextView)findViewById(R.id.mineral);
        jerukTx =(TextView)findViewById(R.id.jeruk);
        maduTx =(TextView)findViewById(R.id.madu);
        lemonTx =(TextView)findViewById(R.id.lemon);
        kelapaTx =(TextView)findViewById(R.id.kelapa);
        mineralKal= (TextView) findViewById(R.id.mineralKal);
        jerukKal= (TextView) findViewById(R.id.jerukKal);
        maduKal= (TextView) findViewById(R.id.maduKal);
        lemonKal= (TextView) findViewById(R.id.lemonKal);
        kelapaKal= (TextView) findViewById(R.id.kelapaKal);
    }

    public void plusMineral(View view){
        mineral++;
        String temp = ""+ mineral*kaloriMineral;
        mineralTx.setText(""+mineral);
        mineralKal.setText(temp);
    }

    public void plusJeruk(View view){
        jeruk++;
        String temp = ""+ jeruk*kaloriJeruk;
        jerukTx.setText(""+jeruk);
        jerukKal.setText(temp);
    }

    public void plusMadu(View view){
        madu++;
        String temp = ""+ madu*kalorimadu;
        maduTx.setText(""+madu);
        maduKal.setText(temp);
    }

    public void plusLemon(View view){
        lemon++;
        String temp=""+lemon*kaloriLemon;
        lemonTx.setText(""+lemon);
        lemonKal.setText(temp);
    }

    public void plusKelapa(View view){
        kelapa++;
        String temp=""+kelapa*kaloriKelapa;
        kelapaTx.setText(""+kelapa);
        kelapaKal.setText(temp);
    }

    public void minMineral(View view){
        if(mineral>0){
            mineral--;
            String temp = ""+ mineral*kaloriMineral;
            mineralTx.setText(""+mineral);
            mineralKal.setText(temp);
        }
    }

    public void minJeruk(View view){
        if(jeruk>0){
            jeruk--;
            String temp = ""+ jeruk*kaloriJeruk;
            jerukTx.setText(""+jeruk);
            jerukKal.setText(temp);
        }
    }

    public void minMadu(View view){
        if(madu>0){
            madu--;
            String temp = ""+ madu*kalorimadu;
            maduTx.setText(""+madu);
            maduKal.setText(temp);
        }
    }

    public void minLemon(View view){
        if(lemon>0){
            lemon--;
            String temp=""+lemon*kaloriLemon;
            lemonTx.setText(""+lemon);
            lemonKal.setText(temp);
        }
    }

    public void minKelapa(View view){
        if(kelapa>0){
            kelapa--;
            String temp=""+kelapa*kaloriKelapa;
            kelapaTx.setText(""+kelapa);
            kelapaKal.setText(temp);
        }
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
                    if(mineral>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(mineral*kaloriMineral);
                        addDb();
                    }
                }break;
                case 1:{
                    if(jeruk>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(jeruk*kaloriJeruk);
                        addDb();
                    }
                }break;
                case 2:{
                    if(madu>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(madu*kalorimadu);
                        addDb();
                    }
                }break;
                case 3:{
                    if(lemon>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(lemon*kaloriLemon);
                        addDb();
                    }
                }break;
                case 4:{
                    if(kelapa>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(kelapa*kaloriKelapa);
                        addDb();
                    }
                }break;
            }
            Log.i("status","ini after switch");


        }
    }

    public void addDb(){
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
