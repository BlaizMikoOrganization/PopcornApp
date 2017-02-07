package com.blaizmiko.popcornapp.ui.activities.splash;

import android.content.Intent;
import android.os.Bundle;

import com.blaizmiko.popcornapp.ui.activities.base.BaseActivity;
import com.blaizmiko.popcornapp.ui.activities.home.HomeActivity;

import okhttp3.OkHttpClient;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
