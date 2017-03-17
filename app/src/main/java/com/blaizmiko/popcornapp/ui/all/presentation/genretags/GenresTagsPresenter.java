package com.blaizmiko.popcornapp.ui.all.presentation.genretags;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.data.models.genretags.Genre;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class GenresTagsPresenter extends BaseMvpPresenter<GenresTagsView>{

    public GenresTagsPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    List<Integer> genreIds;

    @Inject
    MovieDbApi movieDbApi;

    public void loadGenres(List<Integer> genreIdsList) {

        getViewState().showProgress();
        genreIds = genreIdsList;

        final Subscription genresSubscription = movieDbApi.getAllGenres()
                .flatMap(genres -> Observable.from(genres.getGenres()))
                .filter(genre -> {
                    for (Integer genreId: genreIds) {
                        if (genre.getId() == genreId) return true;
                    }
                    return false;
                })
                .map(genre -> genre.getName())
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(genres -> {
                    getViewState().setGenreTags(genres);
                }, error -> {
                    error.getStackTrace();
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(genresSubscription);
    }
}
