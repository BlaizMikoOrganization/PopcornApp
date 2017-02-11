package com.blaizmiko.popcornapp.application;

import android.app.Application;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.di.ApplicationComponent;
import com.blaizmiko.popcornapp.di.DaggerApplicationComponent;
import com.blaizmiko.popcornapp.di.modules.ApiModule;
import com.blaizmiko.popcornapp.di.modules.ApplicationModule;
import com.blaizmiko.popcornapp.di.modules.NetworkModule;

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
        final String defaultFontPath = getString(R.string.roboto_regular_path);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(defaultFontPath)
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiModule(new ApiModule(Constants.Api.BasePealUrl))
                .networkModule(new NetworkModule(Constants.NetworkingConfig.TIMEOUT))
                .build();
    }
}
