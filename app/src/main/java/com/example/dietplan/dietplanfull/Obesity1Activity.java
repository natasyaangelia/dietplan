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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dietplan.dietplanfull.model.User;
import com.example.dietplan.dietplanfull.model.UserRequest;
import com.example.dietplan.dietplanfull.networks.ModelManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Obesity1Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obesity1);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Obesity</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Ada banyak cara untuk membangun motivasi Anda saat berolahraga. Salah satunya adalah berolahraga dengan teman.<br/>" +
                "Tetapi ada baiknya memilih teman yang bukan sembarang teman. Carilah pasangan berolahraga yang benar-benar memiliki komitmen tinggi untuk berolahraga atau dengan kondisi tubuh lebih fit dari kamu.<br/>" +
                "Lalu, apa saja manfaat berolahraga bersama kawan? Ini dia Oatlovers :<br/>" +
                "<br/><b>1. Menambah motivasi.</b><br/>" +
                "Temanmu sudah menunggumu di ruang gym atau lapangan lari nih! Tentu itu membuatmu semakin termotivasi 'kan?<br/>" +
                "<b>2. Semangat dalam berkompetisi.</b><br/>" +
                "Siapa yang kalah, dia harus membuatkan segelas smoothies kepada yang menang. Tertarik?<br/>" +
                "<b>3. Lebih menyenangkan.</b><br/>" +
                "Tertawa dapat membakar kalori lho. Sambil berolahraga, berarti kalori yang terbakar bisa berlipat ganda nih!<br/>";
        TextView tv = (TextView) findViewById(R.id.textMain);
        tv.setText(Html.fromHtml(s));
        prefs = getSharedPreferences("mySession", Context.MODE_PRIVATE);
        userId = prefs.getString("uid","");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_name:
                addDB();
                break;
            case android.R.id.home:
                Intent intent = new Intent(Obesity1Activity.this, ObesityMainActivity.class);
                startActivity(intent);
                finish();break;
        }
        return true;
    }

    public void addDB(){
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
                                Toast.makeText(getBaseContext(), "Thank you for sharing, you got 5 point", Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sharemenu, menu);
        return true;
    }
}
