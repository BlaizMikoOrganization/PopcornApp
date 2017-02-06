package com.blaizmiko.popcornapp.application;

import android.app.Application;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.blaizmiko.popcornapp.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application{
    private static final String BASE_URL = "";
    private static Retrofit retrofit = null;

    @Override
    public void onCreate() {
        super.onCreate();

        initCalligraphy();
    }

    //Init methods
    private void initCalligraphy() {
        final String defaultFontPath = getString(R.string.roboto_regular_path);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(defaultFontPath)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    //Returns retofit Object for BASE_URL
    public static Retrofit getRetrofit() {
        if (retrofit!= null) return retrofit;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
