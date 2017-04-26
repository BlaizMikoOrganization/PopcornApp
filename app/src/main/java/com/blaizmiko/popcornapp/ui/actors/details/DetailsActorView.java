package com.blaizmiko.popcornapp.ui.actors.details;

import com.arellomobile.mvp.MvpView;
import com.blaizmiko.popcornapp.data.models.actors.detailed.TaggedImageModel;

import java.util.List;

public interface DetailsActorView extends MvpView{

    void showBackdrop(List<TaggedImageModel> backdropsUrl);

    void finishLoad();
    void showError();
    void startLoad();
}
