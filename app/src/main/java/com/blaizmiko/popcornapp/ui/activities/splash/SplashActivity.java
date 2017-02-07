package com.blaizmiko.popcornapp.ui.activities.splash;

import android.os.Bundle;

import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.activities.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNavigator.startHomeActivity(this);
        this.finish();
    }

    @Override
    protected void bindViews() {

    }
}
