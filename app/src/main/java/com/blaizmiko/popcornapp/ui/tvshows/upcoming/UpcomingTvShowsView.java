package com.blaizmiko.popcornapp.ui.tvshows.upcoming;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface UpcomingTvShowsView extends MvpView {
    void showError();
    void finishLoad();
    void startLoad();
    void setUpcomingTvShowsList(final List<TileAdapter.Item> upcomingTvShowsList);

}
