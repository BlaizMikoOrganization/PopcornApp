package com.blaizmiko.popcornapp.ui.activities.base;

import android.content.Context;

import com.arellomobile.mvp.MvpAppCompatActivity;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BaseActivity extends MvpAppCompatActivity{

    @Override
    protected void attachBaseContext(final Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
