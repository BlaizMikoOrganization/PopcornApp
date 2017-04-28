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

    @InjectPresenter
    BaseDetailsPresenter baseDetailsPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initToolbar() {
        baseDetailsPresenter.loadBriefTvShow(id);
    }

    @Override
    protected void bindViews() {
        bindToolbar();
        initViewPager();
    }

    private void initViewPager() {
        InfoTvShowFragment infoFragment = InfoTvShowFragment.newInstance();
        CastTvShowFragment castFragment = CastTvShowFragment.newInstance();

        Bundle castBundle = new Bundle();
        castBundle.putInt(Constants.Extras.ID, id);
        castFragment.setArguments(castBundle);

        Bundle infoBundle = (Bundle) castBundle.clone();
        infoBundle.putDouble(Constants.Extras.RATING, rating);
        infoFragment.setArguments(infoBundle);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(infoFragment, InfoTvShowFragment.TITLE);
        adapter.addFragment(castFragment, CastTvShowFragment.TITLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }
}
