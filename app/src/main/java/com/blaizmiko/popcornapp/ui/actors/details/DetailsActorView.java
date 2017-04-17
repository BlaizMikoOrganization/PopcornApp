package com.blaizmiko.popcornapp.ui.actors.details;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.actors.BaseActorModel;
import com.blaizmiko.popcornapp.data.models.actors.DetailedActorModel;

public interface DetailsActorView extends MvpView{

    void finishLoad();
    void showError();
    void startLoad();

    void showActor(DetailedActorModel actor);
}
