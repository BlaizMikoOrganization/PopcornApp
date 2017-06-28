package com.blaizmiko.popcornapp.application;

import android.app.Application;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.injection.ApplicationComponent;
import com.blaizmiko.popcornapp.injection.DaggerApplicationComponent;
import com.blaizmiko.popcornapp.injection.modules.ApiModule;
import com.blaizmiko.popcornapp.injection.modules.ApplicationModule;
import com.blaizmiko.popcornapp.injection.modules.DataManagerModule;
import com.blaizmiko.popcornapp.injection.modules.DatabaseModule;
import com.blaizmiko.popcornapp.injection.modules.NetworkModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {

    private static ApplicationComponent mApplicationComponent;

    public static ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    //Init App
    @Override
    public void onCreate() {
        super.onCreate();

        initCalligraphy();
        initApplicationComponent();
    }

    //Init methods
    private void initCalligraphy() {
        final String defaultFontPath = getString(R.string.font_regular_path);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(defaultFontPath)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(new ApplicationModule(this))
            .apiModule(new ApiModule(Constants.MovieDbApi.BASE_MOVIE_DB_URL, Constants.OMDbApi.BASE_OMDB_URL))
            .networkModule(new NetworkModule(Constants.NetworkingConfig.TIMEOUT))
            .databaseModule(new DatabaseModule())
            .dataManagerModule(new DataManagerModule())
            .build();
    }
}
