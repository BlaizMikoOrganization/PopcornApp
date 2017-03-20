package com.blaizmiko.popcornapp.ui.all.presentation.details.info.tvshow;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.tvshowsNew.DetailedTvShowModel;

public interface InfoTvShowView extends MvpView{
    void setTvShowInfo(DetailedTvShowModel tvShowInfo);
}
