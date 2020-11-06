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

public class DiaryOatActivity extends AppCompatActivity {

    int almond =0;
    int raisin =0;
    int kacangHijau =0;
    int hicalRaisin =0;
    int honey =0;
    int kalorialmond=0;
    int kalorikacangHijau =8;
    int kaloriraisin=12;
    int kalorihicalRaisin = 14;
    int kalorihoney = 15;
    String userId;
    final History request = new History();
    SharedPreferences prefs,typeDiary; String[] idEnergyDiary = new String[6];
    TextView almondTx,kacangHijauTx,raisinTx,hicalRaisinTx,honeyTx;
    TextView almondKal,kacangHijauKal,raisinKal,hicalRaisinKal,honeyKal;
    ArrayList<EnergyDiary> v = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_oat);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Energy Diary</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        initializationSet();
        almondTx.setText(""+almond);
        kacangHijauTx.setText(""+kacangHijau);
        raisinTx.setText(""+raisin);
        hicalRaisinTx.setText(""+hicalRaisin);
        honeyTx.setText(""+honey);
        almondKal.setText(""+almond);
        kacangHijauKal.setText(""+kacangHijau);
        raisinKal.setText(""+raisin);
        hicalRaisinKal.setText(""+hicalRaisin);
        honeyKal.setText(""+honey);
        ModelManager.Factory.getInstance().getEnergyDiary().enqueue(new Callback<ArrayList<EnergyDiary>>() {
            @Override
            public void onResponse(Call<ArrayList<EnergyDiary>> call, Response<ArrayList<EnergyDiary>> response) {
                if(response.code()==200){
                    v = response.body();
                    for(EnergyDiary energyDiary: v){
                        switch (energyDiary.getNama()){
                            case "Oat 8 Almond" : {
                                kalorialmond=energyDiary.getKalori();
                                idEnergyDiary[0]=energyDiary.getId();
                            }break;
                            case "Oat 8 Kacang Hijau" : {
                                kalorikacangHijau=energyDiary.getKalori();
                                idEnergyDiary[1]=energyDiary.getId();
                            }break;
                            case "Oatbits Raisin" : {
                                kaloriraisin=energyDiary.getKalori();
                                idEnergyDiary[2]=energyDiary.getId();
                            }break;
                            case "Oatbits Hical Raisin" : {
                                kalorihicalRaisin=energyDiary.getKalori();
                                idEnergyDiary[3]=energyDiary.getId();
                            }break;
                            case "Oatbits Hical Honey" : {
                                kalorihoney=energyDiary.getKalori();
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
                Intent intent = new Intent(DiaryOatActivity.this, EnergyDiaryMainActivity.class);
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
        almondTx =(TextView)findViewById(R.id.almond);
        kacangHijauTx =(TextView)findViewById(R.id.kacangHijau);
        raisinTx =(TextView)findViewById(R.id.raisin);
        hicalRaisinTx =(TextView)findViewById(R.id.hicalRaisin);
        honeyTx =(TextView)findViewById(R.id.honey);
        almondKal= (TextView) findViewById(R.id.almondKal);
        kacangHijauKal= (TextView) findViewById(R.id.kacangHijauKal);
        raisinKal= (TextView) findViewById(R.id.raisinKal);
        hicalRaisinKal= (TextView) findViewById(R.id.hicalRaisinKal);
        honeyKal= (TextView) findViewById(R.id.honeyKal);
    }

    public void plusalmond(View view){
        almond++;
        String temp = ""+ almond*kalorialmond;
        almondTx.setText(""+almond);
        almondKal.setText(temp);
    }

    public void pluskacangHijau(View view){
        kacangHijau++;
        String temp = ""+ kacangHijau*kalorikacangHijau;
        kacangHijauTx.setText(""+kacangHijau);
        kacangHijauKal.setText(temp);
    }

    public void plusraisin(View view){
        raisin++;
        String temp = ""+ raisin*kaloriraisin;
        raisinTx.setText(""+raisin);
        raisinKal.setText(temp);
    }

    public void plushicalRaisin(View view){
        hicalRaisin++;
        String temp=""+hicalRaisin*kalorihicalRaisin;
        hicalRaisinTx.setText(""+hicalRaisin);
        hicalRaisinKal.setText(temp);
    }

    public void plushoney(View view){
        honey++;
        String temp=""+honey*kalorihoney;
        honeyTx.setText(""+honey);
        honeyKal.setText(temp);
    }

    public void minalmond(View view){
        if(almond>0){
            almond--;
            String temp = ""+ almond*kalorialmond;
            almondTx.setText(""+almond);
            almondKal.setText(temp);
        }
    }

    public void minkacangHijau(View view){
        if(kacangHijau>0){
            kacangHijau--;
            String temp = ""+ kacangHijau*kalorikacangHijau;
            kacangHijauTx.setText(""+kacangHijau);
            kacangHijauKal.setText(temp);
        }
    }

    public void minraisin(View view){
        if(raisin>0){
            raisin--;
            String temp = ""+ raisin*kaloriraisin;
            raisinTx.setText(""+raisin);
            raisinKal.setText(temp);
        }
    }

    public void minhicalRaisin(View view){
        if(hicalRaisin>0){
            hicalRaisin--;
            String temp=""+hicalRaisin*kalorihicalRaisin;
            hicalRaisinTx.setText(""+hicalRaisin);
            hicalRaisinKal.setText(temp);
        }
    }

    public void minhoney(View view){
        if(honey>0){
            honey--;
            String temp=""+honey*kalorihoney;
            honeyTx.setText(""+honey);
            honeyKal.setText(temp);
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
                    if(almond>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(almond*kalorialmond);
                        addDB();
                    }
                }break;
                case 1:{
                    if(kacangHijau>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(kacangHijau*kalorikacangHijau);
                        addDB();
                    }
                }break;
                case 2:{
                    if(raisin>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(raisin*kaloriraisin);
                        addDB();
                    }
                }break;
                case 3:{
                    if(hicalRaisin>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(hicalRaisin*kalorihicalRaisin);
                        addDB();
                    }
                }break;
                case 4:{
                    if(honey>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(honey*kalorihoney);
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
