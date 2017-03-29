package com.blaizmiko.popcornapp.ui.tvshows.popular;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import java.util.List;

public interface PopularTvShowsView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setPopularTvShowsList(List<TileAdapter.Item> popularTvShowsList);
}
