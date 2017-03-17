//package com.blaizmiko.popcornapp.ui.movies.details;
//
//import com.arellomobile.mvp.InjectViewState;
//import com.blaizmiko.popcornapp.application.BaseApplication;
//import com.blaizmiko.popcornapp.application.Constants;
//import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
//import com.blaizmiko.popcornapp.common.utils.StringUtil;
//import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
//import com.blaizmiko.popcornapp.data.models.cast.Crew;
//import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import rx.Subscription;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
//@InjectViewState
//public class MovieDetailsPresenter extends BaseMvpPresenter<MovieDetailsView> {
//
//    @Inject
//    MovieDbApi movieDbApi;
//
//    public MovieDetailsPresenter() {
//        BaseApplication.getComponent().inject(this);
//    }
//
//    public void loadMovie(int id) {
//        getViewState().startLoad();
//        final Subscription detailMovieSubscription = movieDbApi.getMovie(id, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.MovieDetailsAppendToResponse)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(movie -> {
//                    getViewState().setMovie(movie);
//                }, error -> {
//                    error.getStackTrace();
//                    getViewState().finishLoad();
//                    getViewState().showError();
//                }, () -> getViewState().finishLoad());
//
//        unSubscribeOnDestroy(detailMovieSubscription);
//    }
//
//}
