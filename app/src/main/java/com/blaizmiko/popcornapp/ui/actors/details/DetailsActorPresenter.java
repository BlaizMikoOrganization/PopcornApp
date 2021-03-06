package com.blaizmiko.popcornapp.ui.actors.details;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.Random;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class DetailsActorPresenter extends BaseMvpPresenter<DetailsActorView>{
    @Inject
    MovieDbApi movieDbApi;

    private int numberOfBackdrops = 0;
    private int currentPosterNumber = 0;
    private boolean backdropFound = false;

    DetailsActorPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadTaggedImages(final long actorId) {
        getViewState().startLoad();
        final Subscription taggedImagesSubscription = movieDbApi.getTaggedImages(actorId)
                .flatMap(taggedImagesResponse -> Observable.from(taggedImagesResponse.getImages()))
                .toList()
                .flatMap(taggedImageModels -> {
                    numberOfBackdrops = taggedImageModels.size();
                    return Observable.from(taggedImageModels);
                })
                .filter(taggedImageModel -> {
                    boolean result = getRandomPostersForReviews();
                    currentPosterNumber++;
                    return result;
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(taggedImage -> {
                    getViewState().showBackdrop(taggedImage);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(taggedImagesSubscription);
    }

    public boolean getRandomPostersForReviews() {
        if (backdropFound) return false;
        double chance = 1.0 / numberOfBackdrops;

        //if last item - pick it
        if (currentPosterNumber + 1 == numberOfBackdrops) return true;
        //else random
        if (new Random().nextDouble() <= chance) {
            backdropFound = true;
            return true;
        }
        return false;
    }
}
