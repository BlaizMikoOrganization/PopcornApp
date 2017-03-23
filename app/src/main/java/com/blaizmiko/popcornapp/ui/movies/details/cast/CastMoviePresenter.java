package com.blaizmiko.popcornapp.ui.movies.details.cast;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;
import com.blaizmiko.popcornapp.ui.all.interfaces.CastView;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class CastMoviePresenter extends BaseMvpPresenter<CastView> {

    CastMoviePresenter() {
        BaseApplication.getComponent().inject(this);
    }

    @Inject
    MovieDbApi movieDbApi;

    public void loadCast(int movieId) {
        getViewState().startLoad();
        Subscription castSubscription = movieDbApi.getCredits(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(credits -> {
                    getViewState().setCast(credits.getCast());
                }, error -> {
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(castSubscription);
    }
}
