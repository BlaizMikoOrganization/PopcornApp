package com.blaizmiko.popcornapp.application;

import android.app.Application;

import com.blaizmiko.popcornapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application{

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
}
