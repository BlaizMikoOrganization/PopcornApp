package com.blaizmiko.popcornapp.ui.actors;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.blaizmiko.popcornapp.data.models.actors.popular.PopularActorsResponse;

@StateStrategyType(AddToEndSingleStrategy.class)
interface PopularActorsView extends MvpView {

    void showProgress();

    void hideProgress();

    void showError();

    void showActorsList(PopularActorsResponse popularActorsResponse);
}
