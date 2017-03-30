package com.blaizmiko.popcornapp.ui.movies.reviews;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.Random;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class ReviewPresenter extends BaseMvpPresenter<ReviewView> {

    @Inject
    MovieDbApi movieDbApi;


    private int numberOfReviews;
    private boolean passedExists = false;
    private int numberOfPassedRandomz = 0;
    private int numberOfPosters;
    private int chance;
    private int currentItemNumber = 0;

    ReviewPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadPosters(int movieId) {
        final Subscription reviewPosterSubscription = movieDbApi.getMovieImages(movieId, Constants.MovieDbApi.IncludeImageLanguage)
                .flatMap(images -> {
                    numberOfPosters = images.getPosters().size();
                    return Observable.from(images.getPosters());
                })
                .filter(image -> {
                    boolean result = getRandomPostersForReviews();
                    currentItemNumber++;
                    return result;})
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posters -> {
                    getViewState().setPosters(posters);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(reviewPosterSubscription);
    }


    public boolean getRandomPostersForReviews() {
        if (numberOfPosters == currentItemNumber+1) {
            return true;
        }

        if (numberOfPassedRandomz >= numberOfReviews) return false;

        chance = numberOfReviews/numberOfPosters;
        if (new Random().nextDouble() < chance) {
            passedExists = true;
            return true;
        }

        if (new Random().nextDouble() < chance || currentItemNumber >= numberOfPosters - (numberOfReviews - numberOfPassedRandomz)) return true;
        return false;
    }
}
