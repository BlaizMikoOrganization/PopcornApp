package com.blaizmiko.popcornapp.ui.tvshows;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface NowPlayingTvShowsView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setNowPlayingTvShowsList(List<TileAdapter.Item> nowPlayingTvShowsList);
}
