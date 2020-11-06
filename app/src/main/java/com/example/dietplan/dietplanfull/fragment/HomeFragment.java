package com.example.dietplan.dietplanfull.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dietplan.dietplanfull.R;
import com.example.dietplan.dietplanfull.model.History;
import com.example.dietplan.dietplanfull.model.User;
import com.example.dietplan.dietplanfull.networks.ModelManager;

import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    ProgressBar progressBmi,progressBarCalori; int totalConsumend= 0;
    int totalConsumendMor= 0;int totalConsumendAft= 0;int totalConsumendNig= 0;
    TextView idealCalorieField,morText,aftText,nigText,idealCaloriConsumed,bmiField,levelBMI,levelCalorie;
    SharedPreferences prefs;
    ArrayList<History> v = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = this.getActivity().getSharedPreferences("mySession",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Date date = new Date();
        String dates = ""+date;
        String[] split = dates.split(" ");
        final String tanggal = split[1]+ " "+split[2]+ " "+split[5];
        final String userId = prefs.getString("uid","");
        ModelManager.Factory.getInstance().getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 200) {
                    User user = response.body();
                    int idealCalori = user.getIdealCalori();
                    int bmi = user.getBmi();
                    idealCalorieField = (TextView)view.findViewById(R.id.idealCaloriesField);
                    progressBmi = (ProgressBar)view.findViewById(R.id.progressBar1);
                    bmiField = (TextView)view.findViewById(R.id.bmiField);
                    levelBMI= (TextView)view.findViewById(R.id.levelBMI);
                    int progBmi = 100- (bmi*(100/30));
                    progressBmi.setProgress(progBmi);
                    bmiField.setText(""+bmi);
                    idealCalorieField.setText(""+idealCalori);
                    if(bmi <= 18){
                        levelBMI.setText("Underweight");
                    }else if(bmi>18 && bmi<=25){
                        levelBMI.setText("Normal");
                    }else if(bmi>25 && bmi<=30){
                        levelBMI.setText("Overweight");
                    }else{
                        levelBMI.setText("Obesity");
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        ModelManager.Factory.getInstance().getHistory().enqueue(new Callback<ArrayList<History>>() {
            @Override
            public void onResponse(Call<ArrayList<History>> call, Response<ArrayList<History>> response) {
                v = response.body();
                for(History histori: v){
                    if(histori.getIdUser().equalsIgnoreCase(userId) && (histori.getDate().equalsIgnoreCase(tanggal)) ){
                        if(histori.getTypeSnack().equalsIgnoreCase("morning")){
                            totalConsumendMor+=histori.getEnergy();
                        }else if(histori.getTypeSnack().equalsIgnoreCase("afternoon")){
                            totalConsumendAft+=histori.getEnergy();
                        }else if(histori.getTypeSnack().equalsIgnoreCase("night")){
                            totalConsumendNig+=histori.getEnergy();
                        }
                        totalConsumend += histori.getEnergy();
                    }
                }
                morText =(TextView)view.findViewById(R.id.morningText);
                aftText =(TextView)view.findViewById(R.id.afternoonText);
                nigText =(TextView)view.findViewById(R.id.nightText);
                levelCalorie= (TextView)view.findViewById(R.id.levelCalorie);
                idealCaloriConsumed =(TextView)view.findViewById(R.id.idealCaloriesConsumed);
                morText.setText(""+totalConsumendMor);
                nigText.setText(""+totalConsumendNig);
                aftText.setText(""+totalConsumendAft);
                idealCaloriConsumed.setText(""+totalConsumend);
                progressBarCalori = (ProgressBar)view.findViewById(R.id.progressBar);
                int setProg=0;
                if(totalConsumend>350){
                    setProg = 0;
                }else{
                    setProg = 350-totalConsumend;
                    setProg = (int) (setProg * 0.35);
                }
                progressBarCalori.setProgress(setProg);

                if(totalConsumend<=88){
                    levelCalorie.setText("Less");
                }else if(totalConsumend>88 && totalConsumend<=175){
                    levelCalorie.setText("Medium");
                }else if(totalConsumend>175 && totalConsumend<=263){
                    levelCalorie.setText("Enough");
                }else{
                    levelCalorie.setText("Over");
                }

            }

            @Override
            public void onFailure(Call<ArrayList<History>> call, Throwable t) {

            }
        });

    }

}
