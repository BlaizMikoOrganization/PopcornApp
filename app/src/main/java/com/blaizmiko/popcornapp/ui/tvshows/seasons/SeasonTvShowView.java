package com.blaizmiko.popcornapp.ui.tvshows.seasons;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.seasons.EpisodeModel;

import java.util.List;

public interface SeasonTvShowView extends MvpView{
    void startLoad();
    void finishLoad();
    void showError();

    void setEpisodeInfo(List<EpisodeModel> episodes);

}
