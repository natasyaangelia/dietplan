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

public class Healthy2Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy2);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Healthy Lifestyle</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Selalu ada berbagai cara untuk melakukan pola hidup sehat, salah satunya dengan mengatur konsumsi makanan sehari-hari. Tidak pandai memasak seharusnya bukan alasan untuk tidak menjalani pola makan yang sehat. Dengan menyiapkan makanan sendiri di rumah bukan hanya kebersihannya lebih terjaga, kita juga bisa mengatur sendiri menu yang sehat lho, Oatlovers!" +
                "Walaupun menghabiskan waktu dan tenaga tetapi banyak manfaat yang bisa kita dapatkan ketika kita memasak sendiri. Dengan mempersiapkan sendiri bahan masakan di rumah kita bisa lebih menjamin kesehatan dan kesegaran bahan-bahan yang ada ketimbang mengkonsumsi makanan dari luar. Berikut ini beberapa manfaat yang bisa Oatlovers dapatkan jika memasak sendiri di rumah.<br/><br/>" +
                "<br/><b>1. Sehat dan Higienis</b><br/>" +
                "Hal baik utama yang kamu dapatkan ketika memilih untuk memasak sendiri di rumah adalah tingkat kesehatan dan kehigienisan yang tinggi. Berhubung Oatlovers sendiri yang menjadi kokinya maka kamu bisa memastikan dan memilih bahan-bahan makanan yang sehat dan layak dikonsumsi. Kadar bumbu yang ditambahkan pada makanan pun dapat kamu perkirakan sendiri berbeda jika Oatlovers mengkonsumsi makanan dari luar.<br/>" +
                "<br/><b>2. Meminimalkan Resiko Penyakit</b><br/>" +
                "Ketika memasak sendiri penggunaan garam, gula, MSG, saus, dan bumbu penyedap lainnya dapat dikurangi pemakaiannya untuk mencegah risiko terkena diabetes, penyakit jantung, stroke, darah tinggi, dan obesitas berlebih. Tentunya masakan menjadi lebih sehat bukan? Jika kamu memiliki alergi, menyiapkan hidangan di rumah dapat mencegahmu mengonsumsi bahan makanan tertentu yang dapat mengaktifkan gejala alergi secara tak sengaja. <br/>" +
                "<br/><b>3. Pemahaman Nutrisi yang Baik</b><br/>" +
                "Setelah mencoba beberapa kali dan memiliki pengalaman memasak yang cukup lama, pemahaman kamu akan nutrisi tentunya dapat semakin meningkat. Oatlovers juga dapat semakin mahir mengolah makanan mentah dan minuman menjadi sajian yang tak hanya lezat, tetapi juga bergizi dan sehat untuk dikonsumsi. Hal ini akan sangat membantu terutama dalam mempertahankan semangat gaya hidup sehat.<br/>" +
                "<br/><b>4. Hemat Biaya</b><br/>" +
                "<br/>Memasak sendiri tentu lebih murah ketimbang biaya yang dibutuhkan jika kamu makan di luar. Uang yang kamu keluarkan untuk membeli bahan makanan selama seminggu bisa jadi setara dengan harga makanan di restoran favorit kamu lho, Oatlovers.<br/>" +
                "Itulah beberapa manfaat yang bisa kamu dapatkan jika memasak sendiri. Selain sehat tentunya dapat menjadi salah satu trik jitu untuk menghemat pengeluaran kamu, Oatlovers! J<br/>";
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
                Intent intent = new Intent(Healthy2Activity.this,MainHealthyLifestyleActivity.class);
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
