package com.blaizmiko.popcornapp.ui.actors.details;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.actors.BaseActorModel;
import com.blaizmiko.popcornapp.data.models.actors.DetailedActorModel;
import com.blaizmiko.popcornapp.data.models.actors.TaggedImageModel;

import java.util.List;

public interface DetailsActorView extends MvpView{

    void finishLoad();
    void showError();
    void startLoad();

    void showActor(DetailedActorModel actor);
    void showBackdrop(List<TaggedImageModel> images);
}
