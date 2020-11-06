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

public class Beauty1Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty1);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Beauty</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Buat sebagian besar cewek, punya bibir yang menawan adalah impian. Bibir berwarna gelap kadang membuatmu kurang PD keluar rumah tanpa menggunakan lipstick. Tapi jika terlalu sering menggunakan lipstick dan tidak diiringi perawatan untuk bibir, itu bisa membuat bibir kamu semakin gelap, lho, Oatlovers.<br/>" +
                "gimana sih caranya untuk mendapatkan bibir sehat dan menyegarkan yang cerah? Berikut  beberapa tips alami buat kamu nih.<br/>" +
                "1.\tJangan kekurangan air putih. Yap! Benar sekali, air putih itu paling penting. Kalau kekurangan air putih, pasti bibir kamu akan kering, lalu mengelupas. Sangat tidak enak dipandang, bukan?<br/>" +
                "2.\tPakai lip balm sebelum tidur. Agar bibirmu selalu lembab, gunakanlah lip balm saat malam hari sebelum tidur. Jadi dipagi hari, saat akan berangkat ke kantor, bibirmu sudah siap dioles lipstick.<br/>" +
                "3.\tSesekali scrub bibir dengan gula dan madu. Dua bahan yang mudah sekali ditemukan dan semua pasti sudah tau deh, kalo madu dan gula itu punya banyak kegunaan. Salah satunya adalah madu bisa mencerahkan dan melembabkan bibir. Gula disini berperan untuk mengangkat";
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
                Intent intent = new Intent(Beauty1Activity.this, BeautyMainActivity.class);
                startActivity(intent);
                finish();break;
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
