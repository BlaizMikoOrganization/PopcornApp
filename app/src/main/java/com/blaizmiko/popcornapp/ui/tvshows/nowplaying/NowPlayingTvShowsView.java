package com.blaizmiko.popcornapp.ui.tvshows.nowplaying;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface NowPlayingTvShowsView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setNowPlayingTvShowsList(final List<? extends TileAdapter.ITileItem> nowPlayingTvShowsList);
}
