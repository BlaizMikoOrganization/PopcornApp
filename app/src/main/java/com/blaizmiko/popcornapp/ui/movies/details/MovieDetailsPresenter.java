package com.blaizmiko.popcornapp.ui.movies.details;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.network.api.OMDbApi;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.blaizmiko.popcornapp.data.models.cast.Crew;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class MovieDetailsPresenter extends BaseMvpPresenter<MovieDetailsView>{

    @Inject
    MovieDbApi movieDbApi;

    public MovieDetailsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadMovie(int id) {
        getViewState().startLoad();
        final Subscription detailMovieSubscription = movieDbApi.getMovie(id, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.MovieDetailsAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie -> {
                    getViewState().setMovie(movie);
                }, error -> {
                    System.out.println(error.getMessage());
                    System.out.println("pish");
                    error.getStackTrace();
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(detailMovieSubscription);
    }

    public void getDirector(List<Crew> crew) {
        System.out.println("here");
        final String DIRECTOR = "Director";
        String directorName = StringUtil.NOT_AVALIBLE_STRING;
        for (Crew member: crew) {
            if (member.getJob().equals(DIRECTOR)) {
                directorName = member.getName();
                System.out.println("pu " +directorName);
                break;
            }
        }
        System.out.println(directorName);
        getViewState().setMovieDirector(directorName);
    }
}
