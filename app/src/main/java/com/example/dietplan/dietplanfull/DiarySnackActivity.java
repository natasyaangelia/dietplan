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

public class DiarySnackActivity extends AppCompatActivity {

    int bubur =0;
    int gadogado =0;
    int coklat =0;
    int siomay =0;
    int yogurt =0;
    int kaloribubur=0;
    int kaloricoklat =8;
    int kalorigadogado=12;
    int kalorisiomay = 14;
    int kaloriyogurt = 15;
    String userId;
    final History request = new History();
    SharedPreferences prefs,typeDiary; String[] idEnergyDiary = new String[6];
    ArrayList<EnergyDiary> v = new ArrayList<>();
    TextView buburTx,coklatTx,gadogadoTx,siomayTx,yogurtTx;
    TextView buburKal,coklatKal,gadogadoKal,siomayKal,yogurtKal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_snack);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>EnergyDiary</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        initializationSet();
        buburTx.setText(""+bubur);
        coklatTx.setText(""+coklat);
        gadogadoTx.setText(""+gadogado);
        siomayTx.setText(""+siomay);
        yogurtTx.setText(""+yogurt);
        buburKal.setText(""+bubur);
        coklatKal.setText(""+coklat);
        gadogadoKal.setText(""+gadogado);
        siomayKal.setText(""+siomay);
        yogurtKal.setText(""+yogurt);
        ModelManager.Factory.getInstance().getEnergyDiary().enqueue(new Callback<ArrayList<EnergyDiary>>() {
            @Override
            public void onResponse(Call<ArrayList<EnergyDiary>> call, Response<ArrayList<EnergyDiary>> response) {
                if(response.code()==200){
                    v = response.body();
                    for(EnergyDiary energyDiary: v){
                        switch (energyDiary.getNama()){
                            case "Bubur Kacang Hijau" : {
                                kaloribubur=energyDiary.getKalori();
                                idEnergyDiary[0]=energyDiary.getId();
                            }break;
                            case "Dark Chocolate" : {
                                kaloricoklat=energyDiary.getKalori();
                                idEnergyDiary[1]=energyDiary.getId();
                            }break;
                            case "Gado-gado" : {
                                kalorigadogado=energyDiary.getKalori();
                                idEnergyDiary[2]=energyDiary.getId();
                            }break;
                            case "Siomay" : {
                                kalorisiomay=energyDiary.getKalori();
                                idEnergyDiary[3]=energyDiary.getId();
                            }break;
                            case "Yogurt Buah" : {
                                kaloriyogurt=energyDiary.getKalori();
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
                Intent intent = new Intent(DiarySnackActivity.this, EnergyDiaryMainActivity.class);
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
        buburTx =(TextView)findViewById(R.id.bubur);
        coklatTx =(TextView)findViewById(R.id.coklat);
        gadogadoTx =(TextView)findViewById(R.id.gadogado);
        siomayTx =(TextView)findViewById(R.id.siomay);
        yogurtTx =(TextView)findViewById(R.id.yogurt);
        buburKal= (TextView) findViewById(R.id.buburKal);
        coklatKal= (TextView) findViewById(R.id.coklatKal);
        gadogadoKal= (TextView) findViewById(R.id.gadogadoKal);
        siomayKal= (TextView) findViewById(R.id.siomayKal);
        yogurtKal= (TextView) findViewById(R.id.yogurtKal);
    }

    public void plusbubur(View view){
        bubur++;
        String temp = ""+ bubur*kaloribubur;
        buburTx.setText(""+bubur);
        buburKal.setText(temp);
    }

    public void pluscoklat(View view){
        coklat++;
        String temp = ""+ coklat*kaloricoklat;
        coklatTx.setText(""+coklat);
        coklatKal.setText(temp);
    }

    public void plusgadogado(View view){
        gadogado++;
        String temp = ""+ gadogado*kalorigadogado;
        gadogadoTx.setText(""+gadogado);
        gadogadoKal.setText(temp);
    }

    public void plussiomay(View view){
        siomay++;
        String temp=""+siomay*kalorisiomay;
        siomayTx.setText(""+siomay);
        siomayKal.setText(temp);
    }

    public void plusyogurt(View view){
        yogurt++;
        String temp=""+yogurt*kaloriyogurt;
        yogurtTx.setText(""+yogurt);
        yogurtKal.setText(temp);
    }

    public void minbubur(View view){
        if(bubur>0){
            bubur--;
            String temp = ""+ bubur*kaloribubur;
            buburTx.setText(""+bubur);
            buburKal.setText(temp);
        }
    }

    public void mincoklat(View view){
        if(coklat>0){
            coklat--;
            String temp = ""+ coklat*kaloricoklat;
            coklatTx.setText(""+coklat);
            coklatKal.setText(temp);
        }
    }

    public void mingadogado(View view){
        if(gadogado>0){
            gadogado--;
            String temp = ""+ gadogado*kalorigadogado;
            gadogadoTx.setText(""+gadogado);
            gadogadoKal.setText(temp);
        }
    }

    public void minsiomay(View view){
        if(siomay>0){
            siomay--;
            String temp=""+siomay*kalorisiomay;
            siomayTx.setText(""+siomay);
            siomayKal.setText(temp);
        }
    }

    public void minyogurt(View view){
        if(yogurt>0){
            yogurt--;
            String temp=""+yogurt*kaloriyogurt;
            yogurtTx.setText(""+yogurt);
            yogurtKal.setText(temp);
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
                    if(bubur>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(bubur*kaloribubur);
                        addDB();
                    }
                }break;
                case 1:{
                    if(coklat>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(coklat*kaloricoklat);
                        addDB();
                    }
                }break;
                case 2:{
                    if(gadogado>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(gadogado*kalorigadogado);
                        addDB();
                    }
                }break;
                case 3:{
                    if(siomay>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(siomay*kalorisiomay);
                        addDB();
                    }
                }break;
                case 4:{
                    if(yogurt>0){
                        request.setIdEnergyDiary(idEnergyDiary[i]);
                        request.setEnergy(yogurt*kaloriyogurt);
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
