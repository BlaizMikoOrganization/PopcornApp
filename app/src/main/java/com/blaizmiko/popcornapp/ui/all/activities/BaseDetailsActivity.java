package com.blaizmiko.popcornapp.ui.all.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;

public abstract class BaseDetailsActivity extends BaseMvpActivity {

    //Bind views
    @BindView(R.id.toolbar_details_toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.image_view_details_toolbar_backdrop)
    protected ImageView backdropImageView;
    @BindView(R.id.viewpager_details)
    protected ViewPager viewPager;
    @BindView(R.id.progress_bar_details_load)
    protected ProgressBar progressBar;
    @BindView(R.id.tabs_details_toolbar)
    protected TabLayout tabLayout;

    protected int id;
    protected String cinemaName;
    protected double rating;

    //Life cycle
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    protected void bindToolbar() {
        rating = getIntent().getDoubleExtra(Constants.Extras.RATING, Constants.MovieDbApi.DEFAULT_CINEMA_RATING);
        id = getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_CINEMA_ID);
        cinemaName = getIntent().getStringExtra(Constants.Extras.TITLE);

        setToolbar(toolbar);
        setToolbarTitle(cinemaName);
        setToolbarDisplayHomeButtonEnabled(true);

        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + getIntent().getStringExtra(Constants.Extras.BACKDROP_URL))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(backdropImageView);
    }

    //Listeners
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
