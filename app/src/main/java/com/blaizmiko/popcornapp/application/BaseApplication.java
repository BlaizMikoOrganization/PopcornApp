package com.blaizmiko.popcornapp.application;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.GsonConverterFactory;
import retrofit2.Retrofit;

public class BaseApplication extends Application{

    private static final String BASE_URL = "";
    private static final Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();



    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        return retrofit;
    }
    }

    public static Retrofit getRetrofit() {
        if (retrofit!= null) return retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
