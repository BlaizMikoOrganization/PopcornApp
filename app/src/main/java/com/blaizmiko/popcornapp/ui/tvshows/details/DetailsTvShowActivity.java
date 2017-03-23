package com.blaizmiko.popcornapp.ui.tvshows.details;

import android.os.Bundle;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.activities.BaseDetailsActivity;
import com.blaizmiko.popcornapp.ui.all.adapters.TabsAdapter;
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
        bindToolbar();
        initViewPager();
    }

    private void initViewPager() {
        InfoTvShowFragment infoFragment = InfoTvShowFragment.newInstance();
        CastTvShowFragment castFragment = CastTvShowFragment.newInstance();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.Extras.ID, id);
        infoFragment.setArguments(bundle);
        castFragment.setArguments(bundle);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(infoFragment, InfoTvShowFragment.TITLE);
        adapter.addFragment(castFragment, CastTvShowFragment.TITLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }
}
