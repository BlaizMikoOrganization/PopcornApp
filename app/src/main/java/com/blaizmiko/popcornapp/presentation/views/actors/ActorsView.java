package com.blaizmiko.popcornapp.presentation.views.actors;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.blaizmiko.popcornapp.models.actors.PopularActors;

import java.util.List;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ActorsView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError();

    void setActorsList(PopularActors popularActors);
}
