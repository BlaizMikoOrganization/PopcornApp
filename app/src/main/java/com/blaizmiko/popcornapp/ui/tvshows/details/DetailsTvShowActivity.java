package com.blaizmiko.popcornapp.ui.tvshows.details;

import android.os.Bundle;
import android.util.Log;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.activities.BaseDetailsActivity;
import com.blaizmiko.popcornapp.ui.all.adapters.TabsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseDetailsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.details.cast.CastTvShowFragment;
import com.blaizmiko.popcornapp.ui.tvshows.details.info.InfoTvShowFragment;
import com.blaizmiko.popcornapp.ui.movies.details.review.ReviewsFragment;

public class DetailsTvShowActivity extends BaseDetailsActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindViews() {

        id = getIntent().getLongExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_CINEMA_ID);
        cinemaName = getIntent().getStringExtra(Constants.Extras.TITLE);
        backdropUrl = getIntent().getStringExtra(Constants.Extras.BACKDROP_URL);
        rating = getIntent().getDoubleExtra(Constants.Extras.RATING, Constants.MovieDbApi.DEFAULT_CINEMA_RATING);

        baseDetailsPresenter.loadBriefTvShow(id, cinemaName, backdropUrl);
        initViewPager();
    }

    private void initViewPager() {
        final InfoTvShowFragment infoFragment = InfoTvShowFragment.newInstance(loadProgressPresenter);
        final CastTvShowFragment castFragment = CastTvShowFragment.newInstance(loadProgressPresenter);

        final Bundle castBundle = new Bundle();
        castBundle.putLong(Constants.Extras.ID, id);
        castFragment.setArguments(castBundle);

        final Bundle infoBundle = (Bundle) castBundle.clone();
        infoBundle.putDouble(Constants.Extras.RATING, rating);
        infoFragment.setArguments(infoBundle);

        final TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(infoFragment, InfoTvShowFragment.TITLE);
        adapter.addFragment(castFragment, CastTvShowFragment.TITLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }
}
