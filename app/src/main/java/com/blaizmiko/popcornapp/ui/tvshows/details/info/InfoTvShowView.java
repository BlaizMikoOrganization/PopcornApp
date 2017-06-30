package com.blaizmiko.popcornapp.ui.tvshows.details.info;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.tvshows.DetailedTvShowModel;

public interface InfoTvShowView extends MvpView{
    void setTvShowInfo(DetailedTvShowModel tvShowInfo);
    void updateChannels(String text);
    void updateCreators(String creators);
    void updateAirDates(String firstAirDate, String lastAirDate);

    void finishLoad();
    void showError();
    void startLoad();
}
