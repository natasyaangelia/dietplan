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

public class Healthy1Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy1);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Healthy Lifestyle</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Siapa yang tidak tahu buah manis dan asam ini Oatlovers? Nah buah yang bentuknya bulat seperti sawo ini banyak sekali manfaatnya loh. Selain warnanya yang menggiurkan ini, banyak kadar vitamin C pada buah kiwi ini sangat baik bagi kebutuhan kita loh, Oatlovers.<br/>" +
                "Nah Oatlovers, disini akan kita bahas berbagai manfaat yang sangat banyak sekali manfaat buah kiwi yang sangat baik untuk Oatlovers.<br/>" +
                "<br/><br/>" +
                "<b>Mencegah penyakit kardiovaskular</b> <br/>" +
                "Sebuah studi menunjukkan bahwa mengkonsumsi 2-3 buah kiwi per hari membantu mengencerkan darah, mengurangi pembekuan darah dan menurunkan kadar lemak dalam darah sehingga bermanfaat mempertahankan kesehatan jantung.<br/>" +
                "<br/>" +
                "<b>Mencegah Kanker</b> <br/>" +
                "Menurut Rowett Research di Inggris, mengkonsumsi buah Kiwi emas setiap hari akan memberikan hampir dua kali lipat tingkat perbaikan DNA Anda. Ini adalah jenis kerusakan genetik yang dapat bertanggung jawab terhadap kanker.<br/>" +
                "<br/>" +
                "<b>Kesehatan Jantung</b><br/>" +
                "Universitas Oslo di Norwegia telah melaporkan bahwa jika Anda mengkonsumsi dua atau tiga buah Kiwi sehari dapat meningkatkan kesehatan jantung Anda : mengencerkan darah dan mengurangi pembekuan darah. Buah ini juga mampu menurunkan tingkat trigliserida (lemak) dalam darah dengan rata-rata hingga 15%.<br/>" +
                "<br/>" +
                "<b>Kesehatan kulit</b><br/>" +
                "Buah kiwi juga mengandung lebih banyak kalium dibandingkan pisang. Tak hanya itu, vitamin C yang terkandung dalam buah ini juga lebih banyak dari jeruk. Kiwi juga kaya vitamin E dan Alfa Linoleic Acid, asam lemak esensial. Ini semua merupakan vitamin dan mineral penting untuk perawatan kulit .<br/>" +
                "<br/>" +
                "<b>Meningkatkan kekebalan tubuh</b><br/>" +
                "Kandungan antioksidan yang kaya dan Vitamin C pada buah kiwi mampu membantu meningkatkan kekebalan tubuh, sehingga tak mudah terserang flu.<br/>" +
                "<br/>" +
                "<b>Baik untuk pencernaan</b><br/>" +
                "Kiwi hijau mengandung actinidin (enzim alami yang memecah protein). Buah ini juga memiliki kandungan tinggi serat yang mampu membantu memperbaiki buang air agar lebih lancar. Serat dari kiwi juga telah diteliti mampu membantu membersihkan sistem pencernaan. Tak hanya itu, vitamin C yang ada dalam buah kiwi diyakini pula bisa membantu menghilangkan kembung dan membantu memfasilitasi pertumbuhan bakteri yang menguntungkan.<br/>" +
                "<br/>" +
                "<b>Mengatasi Asma</b><br/>" +
                "Penelitian di Italia menemukan bahwa anak-anak yang mengkonsumsi 5-7 porsi buah kiwi dan buah jeruk lainnya selama seminggu, mampu menurunkan gejala batuk, sesak nafas, dan mengi.<br/>" +
                "<br/>" +
                "<b>Sumber nutrisi</b><br/>" +
                "Buah kiwi disebut sebagai 'nutritional all - star' yang berarti bahwa buah ini memiliki kepadatan nutrisi terbaik. Konsumsilah buah kiwi sebagai menu sarapan Anda karena buah kiwi kaya akan magnesium yang mampu mengubah makanan menjadi energi.<br/>";
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
                Intent intent = new Intent(Healthy1Activity.this,MainHealthyLifestyleActivity.class);
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
