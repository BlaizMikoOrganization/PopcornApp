package com.blaizmiko.popcornapp.ui.all.presentation.details;

import android.os.Bundle;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.adapters.TabsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.cast.CastFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.details.info.movie.InfoMovieFragment;
import com.blaizmiko.popcornapp.ui.movies.details.review.ReviewsFragment;

public class DetailsMovieActivity extends DetailsActivity{

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void bindViews() {
        super.bindViews();
        initViewPager();
    }

    private void initViewPager() {
        InfoMovieFragment infoFragment = InfoMovieFragment.newInstance();
        ReviewsFragment reviewsFragment = ReviewsFragment.newInstance();
        CastFragment castFragment = CastFragment.newInstance();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.Extras.ID, id);
        infoFragment.setArguments(bundle);
        castFragment.setArguments(bundle);
        reviewsFragment.setArguments(bundle);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(infoFragment, InfoMovieFragment.TITLE);
        adapter.addFragment(castFragment, CastFragment.TITLE);
        adapter.addFragment(reviewsFragment, ReviewsFragment.TITLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }
}
