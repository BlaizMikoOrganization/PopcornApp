package com.blaizmiko.popcornapp.ui.actors.details.movies;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.actors.moviecredits.ActorMovieCastModel;
import com.blaizmiko.popcornapp.data.models.actors.moviecredits.ActorMovieCrewModel;

import java.util.List;

public interface MoviesActorView extends MvpView{

    void showActorMoviesCrew(final List<ActorMovieCrewModel> crewList);
    void showActorMoviesCast(final List<ActorMovieCastModel> castList);

    void finishLoad();
    void showError();
    void startLoad();
}
