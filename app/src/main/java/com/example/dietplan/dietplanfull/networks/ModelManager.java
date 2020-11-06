package com.example.dietplan.dietplanfull.networks;

import android.util.Log;

import com.example.dietplan.dietplanfull.model.EnergyDiary;
import com.example.dietplan.dietplanfull.model.History;
import com.example.dietplan.dietplanfull.model.User;
import com.example.dietplan.dietplanfull.model.UserRequest;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by natasya angelia on 7/17/2016.
 */
public interface ModelManager {

    String BASE_URL = "https://dietplanindah.herokuapp.com/";

    @POST("users")
    Call<User> signUp(@Body UserRequest request);

    @PUT("users/{id}")
    Call<User>  addPoint(@Path("id") String id,@Body UserRequest user);

    @POST("users/login")
    Call<User> login(@Body UserRequest request);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    @GET("history")
    Call<ArrayList<History>> getHistory();

    @GET("energy-diary")
    Call<ArrayList<EnergyDiary>> getEnergyDiary();

    @POST("history")
    Call<History> addEnergyDiary(@Body History request);

    @GET("history")
    Call<ArrayList<History>> getAllHistories();

    @GET("users/{id}")
    Call<User> getOneUser(@Path("id") String id);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") String id, @Body User request);

    @GET("energy-diary/{id}")
    Call<EnergyDiary> getEnergyDiaryId(@Path("id") String id);



    class Factory {
        static ModelManager service;

        public static ModelManager getInstance() {
            Log.d("cek","into modelmanager getInstance");
            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Log.d("cek","success connect to db");
                service = retrofit.create(ModelManager.class);
                return service;
            } else {
                Log.d("cek","unsuccess connect to db");
                return service;
            }
        }
    }
}
