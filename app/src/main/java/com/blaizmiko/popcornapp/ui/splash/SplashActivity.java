package com.blaizmiko.popcornapp.ui.splash;

import android.os.Bundle;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.activities.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        ActivityNavigator.startHomeActivity(this);
        this.finish();
    }

    @Override
    protected void bindViews() {

    }
}
