package com.blaizmiko.popcornapp.ui.all.presentation;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.cinema.BriefCinema;

public interface BaseDetailsView extends MvpView{
    void showToolbar(final BriefCinema briefCinema);

    void startLoad();
    void showError();
    void finishLoad();
}
