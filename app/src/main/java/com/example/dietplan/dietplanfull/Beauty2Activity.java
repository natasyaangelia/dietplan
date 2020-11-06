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

public class Beauty2Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty2);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Beauty</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Tahukah Oatlovers kalau kacang hijau punya banyak manfaatnya loh yang mungkin selama ini tidak kamu ketahui? Palawija tropis yang dikenal tinggi akan protein, serat, dan mineral ini bisa menyuburkan rambut, meningkatkan kesuburan, dan menjadikan kulit cantik. Benar, ternyata kacang hijau bisa dijadikan bahan alami untuk merawat kecantikan loh, Oatlovers. Kacang hijau dapat berfungsi sebagai astringent, menyejukkan kulit dan meredakan peradangan. Kai Bao Ben Cao, buku pengobatan tradisional China yang sudah berumur ratusan tahun bahkan menyebutkan kalau kacang hijau dapat melepaskan panas dan racun dari kulit nih Oatlovers, karena itu baik untuk detoksifikasi.<br/><br/>" +
                "Kacang hijau memiliki fungsi anti-oksidan dan anti-inflamasi. Karena itu dapat mengatasi berbagai masalah kulit seperti jerawat, ruam-ruam, dan iritasi. Kandungan dalam kacang hijau bahkan disebut-sebut lebih ampuh dalam melawan pigmentasi dan mengobati bercak-bercak pada kulit dibandingkan dengan vitamin C dan vitamin E. Kacang hijau juga bagus untuk mengurangi minyak berlebih pada wajah nih Oatlovers…<br/><br/>" +
                "Untuk Oatlovers yang berjerawat, Oatlovers bisa mencoba resep masker kacang hijau sederhana dari Viva Woman berikut. Masker kacang hijau dapat dapat meredakan peradangan dan menjadikan jerawat lebih cepat kering.<br/><br/>" +
                "Scrub ini akan membersihkan sel-sel kulit mati secara alami, menyingkirkan racun dari permukaan kulit, dan memberikan efek dingin, terutama jika kulit kamu baru terpapar sinar ultra violet. Jika ingin hasil yang lebih maksimal, gunakan juga kacang hijau untuk perawatan dari dalam. Oatlovers juga bisa meminum air rebusan kacang hijau. Menurut Pingming Health air kacang hijau bisa membantu penyembuhan jerawat dari dalam dengan membersihkan darah dan menurunkan panas tubuh. Jerawat akan lebih cepat sembuh, begitu juga dengan bercak-bercak berwarna gelap yang membekas di kulit wajah.<br/><br/>" +
                "Nah, itulah manfaat-manfaat kacang hijau untuk kecantikan Oatlovers. Jika kulit Oatlovers termasuk yang mudah iritasi, pastikan untuk melakukan tes sensitivitas terlebih dahulu dengan mencoba produk masker di belakang telinga. Selamat mencoba!<br/><br/>";
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
                Intent intent = new Intent(Beauty2Activity.this, BeautyMainActivity.class);
                startActivity(intent);
                finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sharemenu, menu);
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
}
