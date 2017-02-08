package com.blaizmiko.popcornapp.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.blaizmiko.popcornapp.models.api.ApiModule;

import com.blaizmiko.popcornapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {

    private ApplicationComponent mApplicationComponent;

    @NonNull
    public static BaseApplication get(@NonNull final Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initCalligraphy();
        initApplicationComponent();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
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
                .build();
    }
}
