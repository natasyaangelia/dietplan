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

public class DiaryProteinActivity extends AppCompatActivity {

    int almondProtein =0;
    int telor =0;
    int kacangRebus =0;
    int tahu =0;
    int tempe =0;
    int kalorialmondProtein=0;
    int kalorikacangRebus =8;
    int kaloritelor=12;
    int kaloritahu = 14;
    int kaloritempe = 15;
    String userId;
    final History request = new History();
    ArrayList<EnergyDiary> v = new ArrayList<>();
    SharedPreferences prefs,typeDiary; String[] idEnergyDiary = new String[6];
    TextView almondProteinTx,kacangRebusTx,telorTx,tahuTx,tempeTx;
    TextView almondProteinKal,kacangRebusKal,telorKal,tahuKal,tempeKal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_protein);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>EnergyDiary</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        initializationSet();
        almondProteinTx.setText(""+almondProtein);
        kacangRebusTx.setText(""+kacangRebus);
        telorTx.setText(""+telor);
        tahuTx.setText(""+tahu);
        tempeTx.setText(""+tempe);
        almondProteinKal.setText(""+almondProtein);
        kacangRebusKal.setText(""+kacangRebus);
        telorKal.setText(""+telor);
        tahuKal.setText(""+tahu);
        tempeKal.setText(""+tempe);
        ModelManager.Factory.getInstance().getEnergyDiary().enqueue(new Callback<ArrayList<EnergyDiary>>() {
            @Override
            public void onResponse(Call<ArrayList<EnergyDiary>> call, Response<ArrayList<EnergyDiary>> response) {
                if(response.code()==200){
                    v = response.body();
                    for(EnergyDiary energyDiary: v){
                        switch (energyDiary.getNama()){
                            case "Kacang Almond" : {
                                kalorialmondProtein=energyDiary.getKalori();
                                idEnergyDiary[0]=energyDiary.getId();
                            }break;
                            case "Kacang Rebus" : {
                                kalorikacangRebus=energyDiary.getKalori();
                                idEnergyDiary[1]=energyDiary.getId();
                            }break;
                            case "Telur Rebus" : {
                                kaloritelor=energyDiary.getKalori();
                                idEnergyDiary[2]=energyDiary.getId();
                            }break;
                            case "Tahu" : {
                                kaloritahu=energyDiary.getKalori();
                                idEnergyDiary[3]=energyDiary.getId();
                            }break;
                            case "Tempe" : {
                                kaloritempe=energyDiary.getKalori();
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
                Intent intent = new Intent(DiaryProteinActivity.this, EnergyDiaryMainActivity.class);
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
        almondProteinTx =(TextView)findViewById(R.id.almondProtein);
        kacangRebusTx =(TextView)findViewById(R.id.kacangRebus);
        telorTx =(TextView)findViewById(R.id.telor);
        tahuTx =(TextView)findViewById(R.id.tahu);
        tempeTx =(TextView)findViewById(R.id.tempe);
        almondProteinKal= (TextView) findViewById(R.id.almondProteinKal);
        kacangRebusKal= (TextView) findViewById(R.id.kacangRebusKal);
        telorKal= (TextView) findViewById(R.id.telorKal);
        tahuKal= (TextView) findViewById(R.id.tahuKal);
        tempeKal= (TextView) findViewById(R.id.tempeKal);
    }

    public void plusalmondProtein(View view){
        almondProtein++;
        String temp = ""+ almondProtein*kalorialmondProtein;
        almondProteinTx.setText(""+almondProtein);
        almondProteinKal.setText(temp);
    }

    public void pluskacangRebus(View view){
        kacangRebus++;
        String temp = ""+ kacangRebus*kalorikacangRebus;
        kacangRebusTx.setText(""+kacangRebus);
        kacangRebusKal.setText(temp);
    }

    public void plustelor(View view){
        telor++;
        String temp = ""+ telor*kaloritelor;
        telorTx.setText(""+telor);
        telorKal.setText(temp);
    }

    public void plustahu(View view){
        tahu++;
        String temp=""+tahu*kaloritahu;
        tahuTx.setText(""+tahu);
        tahuKal.setText(temp);
    }

    public void plustempe(View view){
        tempe++;
        String temp=""+tempe*kaloritempe;
        tempeTx.setText(""+tempe);
        tempeKal.setText(temp);
    }

    public void minalmondProtein(View view){
        if(almondProtein>0){
            almondProtein--;
            String temp = ""+ almondProtein*kalorialmondProtein;
            almondProteinTx.setText(""+almondProtein);
            almondProteinKal.setText(temp);
        }
    }

    public void minkacangRebus(View view){
        if(kacangRebus>0){
            kacangRebus--;
            String temp = ""+ kacangRebus*kalorikacangRebus;
            kacangRebusTx.setText(""+kacangRebus);
            kacangRebusKal.setText(temp);
        }
    }

    public void mintelor(View view){
        if(telor>0){
            telor--;
            String temp = ""+ telor*kaloritelor;
            telorTx.setText(""+telor);
            telorKal.setText(temp);
        }
    }

    public void mintahu(View view){
        if(tahu>0){
            tahu--;
            String temp=""+tahu*kaloritahu;
            tahuTx.setText(""+tahu);
            tahuKal.setText(temp);
        }
    }

    public void mintempe(View view){
        if(tempe>0){
            tempe--;
            String temp=""+tempe*kaloritempe;
            tempeTx.setText(""+tempe);
            tempeKal.setText(temp);
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
                    if(almondProtein>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(almondProtein*kalorialmondProtein);
                        addDB();
                    }
                }break;
                case 1:{
                    if(kacangRebus>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(kacangRebus*kalorikacangRebus);
                        addDB();
                    }
                }break;
                case 2:{
                    if(telor>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(telor*kaloritelor);
                        addDB();
                    }
                }break;
                case 3:{
                    if(tahu>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(tahu*kaloritahu);
                        addDB();
                    }
                }break;
                case 4:{
                    if(tempe>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(tempe*kaloritempe);
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
