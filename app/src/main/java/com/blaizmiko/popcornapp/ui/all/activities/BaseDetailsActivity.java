package com.blaizmiko.popcornapp.ui.all.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;

public abstract class BaseDetailsActivity extends BaseMvpActivity {
    protected int id;
    protected String cinemaName;

    //Bind views
    @BindView(R.id.toolbar_details_toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.image_view_details_toolbar_backdrop)
    protected ImageView backdropImageView;
    @BindView(R.id.image_view_details_toolbar_poster)
    protected ImageView posterImageView;
    @BindView(R.id.text_view_details_toolbar_title)
    protected TextView titleTextView;
    @BindView(R.id.viewpager_details)
    protected ViewPager viewPager;
    @BindView(R.id.progress_bar_details_load)
    protected ProgressBar progressBar;
    @BindView(R.id.tabs_details_toolbar)
    protected TabLayout tabLayout;

    //Life Cycle Methods
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    protected void bindToolbar() {
        cinemaName = getIntent().getStringExtra(Constants.Extras.TITLE);
        toolbar.setTitle(cinemaName);
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);

        final int defaultId = 0;
        id = getIntent().getIntExtra(Constants.Extras.ID, defaultId);
        titleTextView.setText(cinemaName);
        Context context = getApplication().getApplicationContext();
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + getIntent().getStringExtra(Constants.Extras.POSTER_URL))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(posterImageView);
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + getIntent().getStringExtra(Constants.Extras.BACKDROP_URL))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(backdropImageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
