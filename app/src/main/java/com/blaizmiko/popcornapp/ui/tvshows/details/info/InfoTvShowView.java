package com.blaizmiko.popcornapp.ui.tvshows.details.info;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.SeasonTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.DetailedTvShowModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;

import java.util.List;

public interface InfoTvShowView extends MvpView{
    void setTvShowInfo(DetailedTvShowModel tvShowInfo);
    void setSimilarTvShowsAdapter(List<TileAdapter.Item> similarTvShows);
    void setFormattedChannels(String text);
    void setFormattedCreators(String creators);
    void updateSeasons(List<SeasonTvShowModel> seasons);
    void setFormattedAirDates(String firstAirDate, String lastAirDate);

    void finishLoad();
    void showError();
    void startLoad();
}
