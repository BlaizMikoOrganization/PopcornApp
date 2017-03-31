package com.blaizmiko.popcornapp.ui.all.presentation.rating;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.OMDbApi;
import com.blaizmiko.popcornapp.common.utils.FormatUtil;
import com.blaizmiko.popcornapp.data.models.rating.RatingModel;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class RatingPresenter extends BaseMvpPresenter <RatingView>{

    @Inject
    OMDbApi OMDbApi;

    public RatingPresenter() {
        BaseApplication.getComponent().inject(this);
    }


    public void loadRating(String id) {
        final Pattern ratingPattern = Pattern.compile("\\d\\.\\d|\\d\\d");
        final int firstMatchPosition = 0;

        getViewState().startLoad();
        final Subscription ratingSubscription = OMDbApi.getMovieRating(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(ratingResponse -> Observable.from(ratingResponse.getRatings()))
                .map(ratingModel -> {
                    Matcher matcher = ratingPattern.matcher(ratingModel.getRating());
                    matcher.find();
                    ratingModel.setRating(matcher.group(firstMatchPosition));
                    return ratingModel;
                })
                .toList()
                .subscribe(rating -> {
                    getViewState().setFullRating(rating);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(ratingSubscription);
    }

    public void loadTvShowsRating(String id) {
        getViewState().startLoad();
        final String IMDB_RATING_NAME = "Imdb";
        final Subscription ratingSubscription = OMDbApi.getTvShowRating(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ratingTvShowResponse -> {
                    RatingModel ratingModel = new RatingModel();
                    ratingModel.setSite(IMDB_RATING_NAME);
                    ratingModel.setRating(ratingTvShowResponse.getImdbRating());
                    return ratingModel;
                })
                .toList()
                .subscribe(rating -> {
                    getViewState().setFullRating(rating);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(ratingSubscription);
    }

    public void addMovieDbRatingToRatingsList(List<RatingModel> ratings, double movieDbRating) {
        final String movieDbName = "MovieDb";
        RatingModel movieDbRatingModel = new RatingModel();
        movieDbRatingModel.setSite(movieDbName);
        movieDbRatingModel.setRating(Float.toString(FormatUtil.roundToOneDecimal(FormatUtil.fromFiveToTenPointScale(movieDbRating))));
        ratings.add(movieDbRatingModel);
    }
}
