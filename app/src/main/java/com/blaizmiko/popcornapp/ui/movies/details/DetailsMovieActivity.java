package com.blaizmiko.popcornapp.ui.movies.details;

import android.os.Bundle;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.actors.PopularActorsFragment;
import com.blaizmiko.popcornapp.ui.all.activities.BaseDetailsActivity;
import com.blaizmiko.popcornapp.ui.all.adapters.TabsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseDetailsPresenter;
import com.blaizmiko.popcornapp.ui.movies.details.cast.CastMovieFragment;
import com.blaizmiko.popcornapp.ui.movies.details.info.InfoMovieFragment;
import com.blaizmiko.popcornapp.ui.movies.details.review.ReviewsFragment;

public class DetailsMovieActivity extends BaseDetailsActivity {

    @InjectPresenter
    BaseDetailsPresenter baseDetailsPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initToolbar() {
        baseDetailsPresenter.loadBriefMovie(this.id);
    }

    @Override
    protected void bindViews() {
        bindToolbar();
        initViewPager();
    }

    private void initViewPager() {
        final InfoMovieFragment infoFragment = InfoMovieFragment.newInstance();
        final ReviewsFragment reviewsFragment = ReviewsFragment.newInstance();
        final CastMovieFragment castMovieFragment = CastMovieFragment.newInstance();

        final Bundle castMovieBundle = new Bundle();
        castMovieBundle.putInt(Constants.Extras.ID, id);
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
