package com.example.dietplan.dietplanfull;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Healthy3Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy3);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Healthy Lifestyle</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Siapa  yang pagi ini ‘nggak sarapan? Jangan sampai tidak sarapan ya, Oatlovers. Karena sarapan itu sangat penting ‘lho. Biasakan sarapan sebelum memulai aktivitas harian kamu. Kalau kamu ‘nggak sarapan, bisa-bisa kamu jadi kurang fokus saat belajar atau bekerja. Menurut Guru Besar Fakultas Ekologi Manusia Institut Pertanian Bogor (IPB), Prof Hardinsyah, mengatakan sarapan sehat sebelum jam 9 pagi itu penting sebagai sumber energi sebelum memulai kegiatan kamu.<br/><br/>" +
                "Pastikan sarapanmu sehat, yaitu mencakup kandungan protein, mineral, lemak, juga karbohidrat. Dengan sarapan yang sehat, nutrisi dasar yang tubuh kamu butuhkan akan terpenuhi. Selain itu, kamu bisa lebih baik dalam merespon sesuatu, lebih konsentrasi, kemampuan berpikirmu akan meningkat, dan metabolisme tubuhmu akan lebih baik. Sarapan juga dapat  membangkitkan suasana hati yang baik. Dengan suasana hati yang baik, tentu kamu akan lebih bersemangat dalam melakukan berbagai kegiatan.<br/><br/>" +
                "Sarapan pagi itu dapat membantu tubuhmu untuk mengatur kadar gula darah. Terlalu sering melewatkan sarapan bisa menjadi dampak yang tidak baik untuk kesehatan karena berpotensi mengacaukan kerja hormon pengatur kadar gula darah tersebut, bahkan kolestrol jahat yang tinggi bisa mengancam tubuhmu.<br/><br/>" +
                "Jika kamu sarapan dipagi hari, maka saat siang hari kamu tidak akan terlalu banyak makan dan tubuhmu tidak mudah lelah karena energi tubuhmu cukup. Apalagi buat kamu yang sedang diet, sarapan itu wajib banget. Kamu akan lebih bisa mengontrol keinginan makan atau nyemil jika kamu sarapan. Sarapanmu harus benar-benar cukup, karena yang kamu makan dipagi hari itu tidak akan tertimbun menjadi lemak, tapi jadi energi. Tapi kamu harus memperhatikan jenis makanan yang dikonsumsi, yaitu makanan yang mengandung nutrisi tinggi dan rendah lemak.<br/><br/>" +
                "Rutin sarapan itu langkah awal untuk menjalani gaya hidup sehat. Makanan di pagi hari seperti sinyal yang kamu berikan ke otak untuk memberitahu bahwa kamu siap beraktivitas. Sekalipun kamu bangun kesiangan, tetap sempatkan waktu untuk sarapan ya, Oatlovers.<br/><br/>";
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
                Intent intent = new Intent(Healthy3Activity.this,MainHealthyLifestyleActivity.class);
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
