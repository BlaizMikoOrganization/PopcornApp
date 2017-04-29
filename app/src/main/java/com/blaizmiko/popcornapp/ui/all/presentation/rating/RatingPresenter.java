package com.blaizmiko.popcornapp.ui.all.presentation.rating;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.OMDbApi;
import com.blaizmiko.popcornapp.common.utils.FormatUtil;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
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

    public void loadMovieRating(final String id) {
        final Pattern decimalRatingPattern = Pattern.compile("\\d\\.\\d");
        final Pattern percentRatingPattern = Pattern.compile("\\d\\d");
        final int firstMatchPosition = 0;

        getViewState().startLoad();
        final Subscription ratingSubscription = OMDbApi.getMovieRating(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(ratingResponse -> Observable.from(ratingResponse.getRatings()))
                .map(ratingModel -> {
                    Matcher decimalPattern = decimalRatingPattern.matcher(ratingModel.getRating());
                    Matcher percentPattern = percentRatingPattern.matcher(ratingModel.getRating());
                    if (decimalPattern.find()) {
                        ratingModel.setRating(decimalPattern.group(firstMatchPosition));
                    } else if (percentPattern.find()) {
                        ratingModel.setRating(percentPattern.group(firstMatchPosition) + SymbolUtil.PERCENT);
                    } else {
                        ratingModel.setRating(StringUtil.NOT_AVAILABLE_STRING);
                    }
                    return ratingModel;
                })
                .toList()
                .subscribe(rating -> {
                    getViewState().showFullRating(rating);
                }, error -> {
                    getViewState().showError();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(ratingSubscription);
    }

    public void loadTvShowsRating(final String id) {
        getViewState().startLoad();
        final Subscription ratingSubscription = OMDbApi.getTvShowRating(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(ratingTvShowResponse -> {
                    RatingModel ratingModel = new RatingModel();
                    ratingModel.setSite(Constants.OMDbApi.RATING_NAME_IMDB);
                    ratingModel.setRating(ratingTvShowResponse.getImdbRating());
                    return ratingModel;
                })
                .toList()
                .subscribe(rating -> {
                    getViewState().showFullRating(rating);
                }, error -> {
                    getViewState().showError();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(ratingSubscription);
    }

    public void addMovieDbRatingToRatingsList(final List<RatingModel> ratings, final double movieDbRating) {
        final String movieDbName = "The movie db";
        final RatingModel movieDbRatingModel = new RatingModel();
        movieDbRatingModel.setSite(movieDbName);
        movieDbRatingModel.setRating(Float.toString(FormatUtil.roundToOneDecimal(FormatUtil.fromFiveToTenPointScale(movieDbRating))));
        ratings.add(movieDbRatingModel);
    }
}
