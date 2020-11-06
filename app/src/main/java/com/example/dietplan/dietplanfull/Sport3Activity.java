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

public class Sport3Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport3);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Sport</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Bangun di pagi hari sangat membantu energi positif kepada diri kita loh, Oatlovers. Tentunya dengan pergi keluar rumah lalu menghirup udara yang sejuk bisa meningkatkan daya ingat. Dimana udara masih segar dan belum tercemari oleh asap polusi kendaraan. Kamu bisa cobain lari atau jogging di pagi hari nih Oatlovers. Seperti kita ketahui, joging atau lari sangat banyak manfaatnya untuk tubuh kita loh…<br/><br/>" +
                "Aktivitas lari pagi ini semestinya sudah menjadi salah satu rutinitas kita. Beragam manfaat lari pagi bagi kesehatan memang sudah bukan rahasia lagi. Ternyata ada segudang manfaat lari pagi bagi kesehatan, ayo kita mulai hidup sehat dengan rutin lari pagi. Oatlovers harus tahu bahwa lari pagi ada aturannya, jangan lupa lakukan pemansan terlebih dulu ya. Jangan sampai berlebih karena jika dilakukan berlebih akan mengakibatkan pengeroposan tulang sejak dini. Berolahragalah secara rutin dan dipadukan  dengan makanan yang sehat seperti makanan 4 sehat 5 sempurna.<br/><br/>" +
                "Jangan lupa untuk perbanyak minum air putih, agar tidak dehidrasi dan membantu menjaga kesimbangan cairan dalam tubuh sehingga ketika selesai lari pagi akan terasa lebih baik untuk meminum air putih, agar membantu mengeluarkan racun dalam tubuh setelah lari dan berkeringat.<br/><br/>";
        TextView tv = (TextView) findViewById(R.id.textMain);
        tv.setText(Html.fromHtml(s));
        prefs = getSharedPreferences("mySession", Context.MODE_PRIVATE);
        userId = prefs.getString("uid","");
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_name:
                addDB();
                break;
            case android.R.id.home:
                Intent intent = new Intent(Sport3Activity.this, SportMainActivity.class);
                startActivity(intent);
                finish();
                break;
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
