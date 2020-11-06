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

public class DiaryVegaActivity extends AppCompatActivity {

    int bayam =0;
    int kentang =0;
    int jagung =0;
    int salad =0;
    int ubi =0;
    int kaloribayam=0;
    int kalorijagung =8;
    int kalorikentang=12;
    int kalorisalad = 14;
    int kaloriubi = 15;
    final History request = new History();
    ArrayList<EnergyDiary> v = new ArrayList<>();
    SharedPreferences prefs,typeDiary; String[] idEnergyDiary = new String[6];
    TextView bayamTx,jagungTx,kentangTx,saladTx,ubiTx;
    TextView bayamKal,jagungKal,kentangKal,saladKal,ubiKal;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_vega);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Energy Diary</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        initializationSet();
        bayamTx.setText(""+bayam);
        jagungTx.setText(""+jagung);
        kentangTx.setText(""+kentang);
        saladTx.setText(""+salad);
        ubiTx.setText(""+ubi);
        bayamKal.setText(""+bayam);
        jagungKal.setText(""+jagung);
        kentangKal.setText(""+kentang);
        saladKal.setText(""+salad);
        ubiKal.setText(""+ubi);
        ModelManager.Factory.getInstance().getEnergyDiary().enqueue(new Callback<ArrayList<EnergyDiary>>() {
            @Override
            public void onResponse(Call<ArrayList<EnergyDiary>> call, Response<ArrayList<EnergyDiary>> response) {
                if(response.code()==200){
                    v = response.body();
                    for(EnergyDiary energyDiary: v){
                        switch (energyDiary.getNama()){
                            case "Bayam" : {
                                kaloribayam=energyDiary.getKalori();
                                idEnergyDiary[0]=energyDiary.getId();
                            }break;
                            case "Jagung" : {
                                kalorijagung=energyDiary.getKalori();
                                idEnergyDiary[1]=energyDiary.getId();
                            }break;
                            case "Kentang Panggang" : {
                                kalorikentang=energyDiary.getKalori();
                                idEnergyDiary[2]=energyDiary.getId();
                            }break;
                            case "Salad" : {
                                kalorisalad=energyDiary.getKalori();
                                idEnergyDiary[3]=energyDiary.getId();
                            }break;
                            case "Ubi Jalar(sedang)" : {
                                kaloriubi=energyDiary.getKalori();
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
                Intent intent = new Intent(DiaryVegaActivity.this, EnergyDiaryMainActivity.class);
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
        bayamTx =(TextView)findViewById(R.id.bayam);
        jagungTx =(TextView)findViewById(R.id.jagung);
        kentangTx =(TextView)findViewById(R.id.kentang);
        saladTx =(TextView)findViewById(R.id.salad);
        ubiTx =(TextView)findViewById(R.id.ubi);
        bayamKal= (TextView) findViewById(R.id.bayamKal);
        jagungKal= (TextView) findViewById(R.id.jagungKal);
        kentangKal= (TextView) findViewById(R.id.kentangKal);
        saladKal= (TextView) findViewById(R.id.saladKal);
        ubiKal= (TextView) findViewById(R.id.ubiKal);
    }

    public void plusbayam(View view){
        bayam++;
        String temp = ""+ bayam*kaloribayam;
        bayamTx.setText(""+bayam);
        bayamKal.setText(temp);
    }

    public void plusjagung(View view){
        jagung++;
        String temp = ""+ jagung*kalorijagung;
        jagungTx.setText(""+jagung);
        jagungKal.setText(temp);
    }

    public void pluskentang(View view){
        kentang++;
        String temp = ""+ kentang*kalorikentang;
        kentangTx.setText(""+kentang);
        kentangKal.setText(temp);
    }

    public void plussalad(View view){
        salad++;
        String temp=""+salad*kalorisalad;
        saladTx.setText(""+salad);
        saladKal.setText(temp);
    }

    public void plusubi(View view){
        ubi++;
        String temp=""+ubi*kaloriubi;
        ubiTx.setText(""+ubi);
        ubiKal.setText(temp);
    }

    public void minbayam(View view){
        if(bayam>0){
            bayam--;
            String temp = ""+ bayam*kaloribayam;
            bayamTx.setText(""+bayam);
            bayamKal.setText(temp);
        }
    }

    public void minjagung(View view){
        if(jagung>0){
            jagung--;
            String temp = ""+ jagung*kalorijagung;
            jagungTx.setText(""+jagung);
            jagungKal.setText(temp);
        }
    }

    public void minkentang(View view){
        if(kentang>0){
            kentang--;
            String temp = ""+ kentang*kalorikentang;
            kentangTx.setText(""+kentang);
            kentangKal.setText(temp);
        }
    }

    public void minsalad(View view){
        if(salad>0){
            salad--;
            String temp=""+salad*kalorisalad;
            saladTx.setText(""+salad);
            saladKal.setText(temp);
        }
    }

    public void minubi(View view){
        if(ubi>0){
            ubi--;
            String temp=""+ubi*kaloriubi;
            ubiTx.setText(""+ubi);
            ubiKal.setText(temp);
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
                    if(bayam>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(bayam*kaloribayam);
                        addDB();
                    }
                }break;
                case 1:{
                    if(jagung>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(jagung*kalorijagung);
                        addDB();
                    }
                }break;
                case 2:{
                    if(kentang>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(kentang*kalorikentang);
                        addDB();
                    }
                }break;
                case 3:{
                    if(salad>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(salad*kalorisalad);
                        addDB();
                    }
                }break;
                case 4:{
                    if(ubi>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(ubi*kaloriubi);
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
