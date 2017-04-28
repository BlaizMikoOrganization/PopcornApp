package com.blaizmiko.popcornapp.ui.actors.details.cinemas;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.actors.cinemascredits.ActorCinemaCastModel;
import com.blaizmiko.popcornapp.data.models.actors.cinemascredits.ActorCinemaCrewModel;

import java.util.List;

public interface CinemasActorView extends MvpView{
    void showCrewCinemas(final List<ActorCinemaCrewModel> crewList);
    void showCastCinemas(final List<ActorCinemaCastModel> castList);

    void finishLoad();
    void showError();
    void startLoad();
}
