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
import android.widget.TextView;
import android.widget.Toast;

import com.example.dietplan.dietplanfull.model.User;
import com.example.dietplan.dietplanfull.model.UserRequest;
import com.example.dietplan.dietplanfull.networks.ModelManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Beauty3Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty3);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Beauty</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Siapa disini yang tidak suka makan buah dan sayur? Hari gini masih gak suka makan buah dan sayur? Rugi dong, Oatlovers! Buah dan sayur itu makanan yang amat sangat sehat loh, Oatlovers juga pasti sudah tau manfaat dari kebiasaan mengkonsumsi sayur dan buah. Salah satunya adalah mencegah kanker, apalagi untuk wanita yang rentan terkena kanker payudara, beberapa sayur dan buah sangat bermanfaat untuk mencegah kanker payudara. #OATFact #EverydayIsHealthy<br/>" +
                "<b>1. Kulit Buah Manggis</b><br/>" +
                "Buah manggis memiliki kandungan mineral yang tinggi dan sangat berguna bagi kesehatan dan kecantikan kulit. Kulit buah manggis memiliki kandungan senyawa xanthone sebanyak 40% , senyawa ini berguna untuk memperbaiki jaringan sel kulit yang rusak.<br/>" +
                "Buah manggis juga adalah jenis antioksidan yang mampu menangkal radikal bebas di dalam tubuh. Karena memiliki kandungan antioksidan yang tinggi, buah manggis mampu mengobati kanker payudara. Manfaat lain dari senyawa xanthone yang terdapat di bagian kulit adalah untuk menurunkan berat badan.<br/>" +
                "<b>2. Brokoli</b><br/>" +
                "Senyawa didalam brokoli yang disebut sulforaphane memberikan dampak positif terhadap pencegahan kanker. Sebuah penelitian telah membuktikan bahwa senyawa ini sangat bermanfaat untuk menghilangkan sel kanker payudara.<br/>" +
                "<b>3. Buah Sirsak</b><br/>" +
                "Sirsak bisa disebut buah kesehatan karena seluruh bagian dari buah sirsak mulai dari bunga, daun, kulit, akar dan buahnya sendiri sangat bermanfaat bagi kesehatan. Di awal tahun 90-an, sebuah penelitian menemukan adanya 34 jenis senyawa cytotoxic di daun buah sirsak. Senyawa-senyawa tersebut memiliki manfaat untuk membunuh sel-sel tubuh yang mengalami pertumbuhan tidak normal atau kanker.<br/>" +
                "Daun sirsak mengandung zat annonaceous acetogenins yang 10.000 kali lebih kuat dalam membunuh sel-sel kanker payudara daripada pengobatan medis. Zat annonaceous acetogenins ini dapat membunuh banyak jenis kanker lainnya seperti kanker usus, paru-paru, payudara dan pankreas tanpa merusak sel-sel tubuh yang sehat.<br/>" +
                "<b>4. Pare</b><br/>" +
                "Sayuran ini terkenal dengan rasa pahitnya. Namun, ternyata pare memiliki khasiat yang sangat baik dalam pencegahan kanker payudara. Menurut penelitian, ekstrak pare diketahui mampu mengurangi dan membunuh pertumbuhan sel-sel kanker payudara.<br/>" +
                "<b>5. Tomat</b><br/>" +
                "Tomat memiliki kandungan lypcopene yang mampu melindungi tubuh dari pertumbuhan sel kanker payudara, kanker serviks, serta penyakit jantung. Leboih jauh, zat tersebut ternyata mampu membuat kamu awet muda serta beberapa penelitian membuktikan bahwa lypcopene dapat melindungi kulit dari efek buruk sinar UV.<br/>" +
                "Buah – buahan dan sayuran yang disebutkan bukan makanan yang sulit didapatkan kan Oatlovers? Jadi sudah tidak ada alasan lagi untuk sehat dan demi kebaikan diri sendiri, mencegah salah satu penyakit mengerikan. Lebih baik mencegah daripada mengobati kan, Oatlovers?<br/>";
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
                Intent intent = new Intent(Beauty3Activity.this, BeautyMainActivity.class);
                startActivity(intent);
                finish();
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
}
