package com.blaizmiko.popcornapp.ui.movies.details;

import android.os.Bundle;

import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.activities.BaseDetailsActivity;
import com.blaizmiko.popcornapp.ui.all.adapters.TabsAdapter;
import com.blaizmiko.popcornapp.ui.movies.details.cast.CastMovieFragment;
import com.blaizmiko.popcornapp.ui.movies.details.info.InfoMovieFragment;
import com.blaizmiko.popcornapp.ui.movies.details.review.ReviewsFragment;

public class DetailsMovieActivity extends BaseDetailsActivity {

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

        baseDetailsPresenter.loadBriefMovie(id, cinemaName, backdropUrl);
        initViewPager();
    }

    private void initViewPager() {
        final InfoMovieFragment infoFragment = InfoMovieFragment.newInstance(loadProgressPresenter);
        final ReviewsFragment reviewsFragment = ReviewsFragment.newInstance(loadProgressPresenter);
        final CastMovieFragment castMovieFragment = CastMovieFragment.newInstance(loadProgressPresenter);

        final Bundle castMovieBundle = new Bundle();
        castMovieBundle.putLong(Constants.Extras.ID, id);
        castMovieFragment.setArguments(castMovieBundle);

        final Bundle infoMovieBundle = (Bundle) castMovieBundle.clone();
        infoMovieBundle.putDouble(Constants.Extras.RATING, rating);
        infoFragment.setArguments(infoMovieBundle);

        final Bundle reviewsBundle = (Bundle) castMovieBundle.clone();
        reviewsBundle.putString(Constants.Extras.TITLE, cinemaName);
        reviewsFragment.setArguments(reviewsBundle);

        final TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(infoFragment, InfoMovieFragment.TITLE);
        adapter.addFragment(castMovieFragment, CastMovieFragment.TITLE);
        adapter.addFragment(reviewsFragment, ReviewsFragment.TITLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }
}
