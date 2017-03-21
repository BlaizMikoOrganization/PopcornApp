package com.blaizmiko.popcornapp.ui.all.presentation.details.info.tvshow;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.tvshowsNew.DetailedTvShowModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface InfoTvShowView extends MvpView{
    void setTvShowInfo(DetailedTvShowModel tvShowInfo);
    void setSimilarTvShowsAdapter(List<TileAdapter.Item> similarTvShows);
    void setFormattedChannels(String text);
    void setFormattedCreators(String creators);

    void finishLoad();
    void showError();
    void startLoad();
}
