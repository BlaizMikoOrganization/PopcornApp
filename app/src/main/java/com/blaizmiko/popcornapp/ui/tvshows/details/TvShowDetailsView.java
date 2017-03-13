package com.blaizmiko.popcornapp.ui.tvshows.details;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.DetailedTvShow;

public interface TvShowDetailsView extends MvpView{
    void showError();
    void finishLoad();
    void startLoad();
    void setTvShow(DetailedTvShow tvShow);
}
