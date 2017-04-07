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

    private int numberOfPosters;
    private double chance;
    private int currentPosterNumber = 0;
    private boolean posterFound = false;

    ReviewPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadPosters(int movieId) {
        getViewState().startLoad();

        final Subscription reviewPosterSubscription = movieDbApi.getMovieImages(movieId, Constants.MovieDbApi.IncludeImageLanguage)
                .flatMap(images -> {
                    numberOfPosters = images.getPosters().size();
                    chance = 1.0 / numberOfPosters;
                    return Observable.from(images.getPosters());
                })
                .filter(image -> {
                    boolean result = getRandomPostersForReviews();
                    currentPosterNumber++;
                    return result;})
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posters -> {
                    getViewState().showPosters(posters);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(reviewPosterSubscription);
    }


    public boolean getRandomPostersForReviews() {
        if (posterFound) return false;

        //if last item - pick it
        if (currentPosterNumber + 1 == numberOfPosters) return true;
        //else random
        if (new Random().nextDouble() <= chance) {
            posterFound = true;
            return true;
        }
        return false;
    }
}
