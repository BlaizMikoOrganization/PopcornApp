package com.blaizmiko.popcornapp.ui.all.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.cinema.BriefCinema;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseDetailsView;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;

public abstract class BaseDetailsActivity extends BaseMvpActivity implements BaseDetailsView, LoadProgressView{

    //Bind views
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.image_view_backdrop)
    protected ImageView backdropImageView;
    @BindView(R.id.tab_layout)
    protected TabLayout tabLayout;
    @BindView(R.id.viewpager_details)
    protected ViewPager viewPager;
    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;

    protected int id;
    protected String cinemaName;
    protected double rating;

    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;

    //Life cycle
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    protected void bindToolbar() {
        id = getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_CINEMA_ID);

        final String cinemaName = getIntent().getStringExtra(Constants.Extras.TITLE);
        final String backdropUrl = getIntent().getStringExtra(Constants.Extras.BACKDROP_URL);
        Log.d("BaseDetailsActivity", "here " +cinemaName +" " +backdropUrl);
        if (cinemaName == null || backdropUrl == null) initToolbar();

        setToolbar(toolbar);
        setToolbarTitle(cinemaName);
        setToolbarDisplayHomeButtonEnabled(true);

        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + backdropUrl)
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

    public abstract void initToolbar();

    @Override
    public void showToolbar(BriefCinema briefCinema) {
        setToolbar(toolbar);
        setToolbarTitle(briefCinema.getTitle());
        setToolbarDisplayHomeButtonEnabled(true);

        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + briefCinema.getBackdropPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(backdropImageView);
    }

    //Movies presenters
    @Override
    public void showError() {
        //Toast.makeText(getActivity().getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishLoad() {
        Log.d("finishLoad", "finishLoad");
        loadProgressPresenter.hideProgress();
    }

    @Override
    public void startLoad() {
        Log.d("startLoad", "startLoad");
        loadProgressPresenter.showProgress();
    }


    //LoadProgress presenter
    public void showProgress() {
        if (progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar.getVisibility() != View.GONE) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
