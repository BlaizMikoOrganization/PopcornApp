package com.blaizmiko.popcornapp.ui.activities.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(final int layoutResID) {
        super.setContentView(layoutResID);
        onViewCreated();
    }

    @Override
    public void setContentView(final View view) {
        super.setContentView(view);
        onViewCreated();
    }

    @Override
    public void setContentView(final View view, final ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        onViewCreated();
    }

    @Override
    protected void attachBaseContext(final Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected void onViewCreated() {
        ButterKnife.bind(this);
        bindViews();
    }

    protected void setToolbar(@NonNull final Toolbar toolbar) {
        setSupportActionBar(toolbar);
    }

    protected void setToolbarTitle(final CharSequence title) {
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    protected void setToolbarDisplayHomeButtonEnabled(final boolean showHomeButton) {
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeButton);
        }
    }

    protected abstract void bindViews();
}
