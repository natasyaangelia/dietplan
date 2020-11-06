package com.example.dietplan.dietplanfull;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dietplan.dietplanfull.model.User;
import com.example.dietplan.dietplanfull.networks.ModelManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnergyPointActivity extends AppCompatActivity {
    TextView point;
    String id;
    int temp;
    User body;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_energy_point);
        prefs = getSharedPreferences("mySession", Context.MODE_PRIVATE);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#724003'>Energy Point</font>"));
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.bgbmi));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        upArrow.setColorFilter(getResources().getColor(R.color.brownText), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

//        username = getIntent().getStringExtra("username");
        point = (TextView) findViewById(R.id.txtPoint);

        btn1 = (Button) findViewById(R.id.imagePoint1);
        btn2 = (Button) findViewById(R.id.imagePoint2);
        btn3 = (Button) findViewById(R.id.imagePoint3);
        btn4 = (Button) findViewById(R.id.imagePoint4);

        btn5 = (Button) findViewById(R.id.imagePoint5);
        btn6 = (Button) findViewById(R.id.imagePoint6);
        btn7 = (Button) findViewById(R.id.imagePoint7);

        id = prefs.getString("uid","");

        ModelManager.Factory.getInstance().getOneUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int code = response.code();
                if (code == 200) {
                    body = response.body();
                    temp = body.getPoint();
                    point.setText(""+temp);
                } else {
                    try {
                        Log.i("33333", response.errorBody().string());
                        Log.d("cek",response.errorBody().string());
                        AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                        alert.setTitle("Invalid User");
                        alert.setMessage("Please check on your username");
                        alert.setPositiveButton("OK",null);
                        alert.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();

                Log.i("33333", t.toString());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(EnergyPointActivity.this, HomeMainActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUser(){
        ModelManager.Factory.getInstance().updateUser(id,body).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int code = response.code();
                if (code == 200) {
//                    AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
//                    alert.setTitle("User updated ! ");
//                    alert.setPositiveButton("OK", null);
//                    alert.show();
                } else {
                    try {
                        Log.i("33333", response.errorBody().string());
                        Log.d("cek",response.errorBody().string());
                        AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                        alert.setTitle("Invalid User");
                        alert.setMessage("Please check on your username");
                        alert.setPositiveButton("OK",null);
                        alert.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();

                Log.i("33333", t.toString());
            }
        });
    }

    public void btn15pt(View view) {
        if(btn1.isEnabled()) {
            if (temp - 5 > 0) {
                temp = temp - 5;
                point.setText("" + temp);
                body.setPoint(temp);
                btn1.setEnabled(false);
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Oat 8 sebagai menu camilanmu! ");
                alert.setMessage("Completed");
                alert.setPositiveButton("OK", null);
                alert.show();
                updateUser();

            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Point tidak cukup ! ");
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
            alert.setTitle("Reward sudah didapat ! ");
            alert.setPositiveButton("OK", null);
            alert.show();
        }
    }

    public void btn25pt(View view) {
        if(btn2.isEnabled()) {
            if (temp - 5 > 0) {
                temp = temp - 5;
                point.setText("" + temp);
                body.setPoint(temp);
                btn2.setEnabled(false);
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Diary 3 hari berturut-turut ");
                alert.setMessage("Completed");
                alert.setPositiveButton("OK", null);
                alert.show();
                updateUser();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Point tidak cukup ! ");
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
            alert.setTitle("Reward sudah didapat ! ");
            alert.setPositiveButton("OK", null);
            alert.show();
        }
    }

    public void btn310pt(View view) {
        if(btn3.isEnabled()) {
            if (temp - 10 > 0) {
                temp = temp - 10;
                point.setText("" + temp);
                body.setPoint(temp);
                btn3.setEnabled(false);
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Ngemil dibawah 700 kkal 7 hari berturut-turut ");
                alert.setMessage("Completed");
                alert.setPositiveButton("OK", null);
                alert.show();
                updateUser();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Point tidak cukup ! ");
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
            alert.setTitle("Reward sudah didapat ! ");
            alert.setPositiveButton("OK", null);
            alert.show();
        }
    }

    public void btn410pt(View view) {
        if(btn4.isEnabled()) {
            if (temp - 10 > 0) {
                temp = temp - 10;
                point.setText("" + temp);
                body.setPoint(temp);
                btn4.setEnabled(false);
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Diary 7 hari berturut-turut ");
                alert.setMessage("Completed");
                alert.setPositiveButton("OK", null);
                alert.show();
                updateUser();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Point tidak cukup ! ");
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
            alert.setTitle("Reward sudah didapat ! ");
            alert.setPositiveButton("OK", null);
            alert.show();
        }
    }

//    btn 5 6 7

    public void btn5rw(View view) {
        if(btn5.isEnabled()) {
            if (temp - 100 > 0) {
                temp = temp - 100;
                point.setText("" + temp);
                body.setPoint(temp);
                btn5.setEnabled(false);
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Diary 7 hari berturut-turut ");
                alert.setMessage("Completed");
                alert.setPositiveButton("OK", null);
                alert.show();
                updateUser();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Point tidak cukup ! ");
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
            alert.setTitle("Reward sudah didapat ! ");
            alert.setPositiveButton("OK", null);
            alert.show();
        }
    }

    public void btn6rw(View view) {
        if(btn6.isEnabled()) {
            if (temp - 300 > 0) {
                temp = temp - 300;
                point.setText("" + temp);
                body.setPoint(temp);
                btn6.setEnabled(false);
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Oat 8 Inner Box");
                alert.setMessage("Completed");
                alert.setPositiveButton("OK", null);
                alert.show();
                updateUser();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Point tidak cukup ! ");
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
            alert.setTitle("Oat 8 Almond Hampers, Cosmetic Bag");
            alert.setPositiveButton("OK", null);
            alert.show();
        }
    }

    public void btn7rw(View view) {
        if(btn7.isEnabled()) {
            if (temp - 500 > 0) {
                temp = temp - 500;
                point.setText("" + temp);
                body.setPoint(temp);
                btn7.setEnabled(false);
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Oat 8 Almond Hampers, Water Bottle Sport ");
                alert.setMessage("Completed");
                alert.setPositiveButton("OK", null);
                alert.show();
                updateUser();
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
                alert.setTitle("Point tidak cukup ! ");
                alert.setPositiveButton("OK", null);
                alert.show();
            }
        }else{
            AlertDialog.Builder alert = new AlertDialog.Builder(EnergyPointActivity.this);
            alert.setTitle("Reward sudah didapat ! ");
            alert.setPositiveButton("OK", null);
            alert.show();
        }
    }
}
