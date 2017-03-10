package com.blaizmiko.popcornapp.ui.actors.details;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.actors.ActorModel;

interface ActorDetailsView extends MvpView{

    void showProgress();

    void hideProgress();

    void showError();

    void setActorDetails(ActorModel actor);
}
