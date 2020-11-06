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

public class Sport2Activity extends AppCompatActivity {

    SharedPreferences prefs;
    String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport2);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Sport</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        String s = "Sudah pernah mencoba yoga Oatlovers? Seperti yang udah kita ketahui bersama, yoga memang sangat banyak manfaatnya. Apa saja sih? Simak ya, Oatlovers!<br/>" +
                "<b>1. Mencegah Sakit Pinggang</b><br/>" +
                "Salah satu manfaat senam yoga adalah bisa mencegah sakit pinggang, jika anda mengikuti kelas yoga ini maka akan ada latihan melemaskan otot sehingga tidak kaku. Dengan cara ini maka pinggang anda pun akan terbebas dari rasa sakit, oleh sebab itu usahakan untuk secara rutin mengikuti kelas yoga ini.<br/>" +
                "<b>2. Dapat Menurunkan Berat Badan</b><br/>" +
                "Bagi Oatlovers yang sedang mengikuti program diet, maka bisa mengikuti kelas yoga ini secara teratur. Karena sering mengikuti program senam ini, maka lemak yang ada dalam tubuh pun akan terbakar dengan sendirinya sehingga tubuh akan langsing.<br/>" +
                "<b>3. Mencegah Penyakit Tulang</b><br/>" +
                "Manfaat senam yoga yang ketiga adalah dapat mencegah penyakit osteoporosis, penyakit tulang ini bisa menyebabkan badan bungkuk. Oleh karena itulah, anda harus segera mencegahnya dengan cara ikut senam yoga ini. Biasanya akan ada gerakan gerakan yang bermanfaat untuk tulang sehingga tetap sehat.<br/>" +
                "<b>4. Melenturkan tubuh</b><br/>" +
                "Karena sering mengikuti yoga, maka tubuh anda pun akan lentur dengan sendirinya sehingga tidak kaku atau pun sakit. Kelas yoga ini mungkin tidak setiap hari, namun bisa diimbangi dengan berolahraga di rumah sehingga tubuh tetap sehat.<br/>" +
                "Sumber www.tren.co.id<br/>";
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
                Intent intent = new Intent(Sport2Activity.this, SportMainActivity.class);
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
