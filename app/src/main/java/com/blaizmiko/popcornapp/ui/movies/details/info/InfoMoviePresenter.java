package com.blaizmiko.popcornapp.ui.movies.details.info;

import android.support.annotation.NonNull;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.FormatUtil;
import com.blaizmiko.popcornapp.data.db.Database;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel;
import com.blaizmiko.popcornapp.data.db.models.movies.DetailedMovieDBModel_;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import javax.inject.Inject;

import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.query.Query;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class InfoMoviePresenter extends BaseMvpPresenter<InfoMovieView> {
    @Inject
    MovieDbApi movieDbApi;
    @Inject
    Database database;

    InfoMoviePresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadMovieInfo(final long movieId) {
        getViewState().startLoad();
        Log.d("movieID = ", ""+movieId);
        final Subscription creditsMovieSubscription = movieDbApi.getMovieInfo(movieId, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.InfoDetailsMovieAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    Log.d("adding", "adding");
                    database.putDetailedMovie(info);
/*                    final Box detailedMovieDBModelBox = database.getBoxForDetailedMovies();
                    final Query<DetailedMovieDBModel> query = detailedMovieDBModelBox.query().equal(DetailedMovieDBModel_.id, 297762).build();
                    query.subscribe().on(AndroidScheduler.mainThread()).observer(data ->
                            pish(data.get(0)));*/
                    updateDescription(info);
                    Log.d("checking", ""+info.getPosters().size());
                    Log.d("checking2", ""+info.getVideos().size());
                    getViewState().updateMovieExtras(info);
                }, error -> {
                    error.getStackTrace();
                    error.printStackTrace();
                    Log.d("error_pish", ""+error.getMessage());
/*                    final Box detailedMovieDBModelBox = database.getBoxForDetailedMovies();
                    final Query<DetailedMovieDBModel> query = detailedMovieDBModelBox.query().equal(DetailedMovieDBModel_.id, 297762).build();
                    query.subscribe().on(AndroidScheduler.mainThread()).observer(data ->
                            pish(data.get(0)));*/
                    getViewState().showError();
                    getViewState().finishLoad();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(creditsMovieSubscription);
    }
    private void pish(final DetailedMovieDBModel movieModel) {
        Log.d("pish", ""+movieModel.getTitle());
        Log.d("pish", ""+movieModel.getPosterPath());
    }
    private void updateDescription(final DetailedMovieDBModel movieModel) {
        final String formattedReleaseDate = formatReleaseDate(movieModel.getReleaseDate());
        getViewState().showFormattedReleaseDate(formattedReleaseDate);
        final String formattedRuntime = formatRuntime(movieModel.getRuntime());
        getViewState().showFormattedRuntime(formattedRuntime);
        final String formattedBudget = formatBudget(movieModel.getBudget());
        getViewState().showFormattedBudget(formattedBudget);
        final String formattedRevenue = formatRevenue(movieModel.getRevenue());
        getViewState().showFormattedRevenue(formattedRevenue);
    }

    private String formatReleaseDate(@NonNull final String releaseDate) {
        return FormatUtil.parseDateToMaterialFormat(releaseDate, FormatUtil.ResultMaterialDateType.FULL);
    }

    private String formatRuntime(final int runtime) {
        return FormatUtil.parseTimeToMaterialFormat(runtime);
    }

    private String formatBudget(final int money) {
        return FormatUtil.parseMoneyToMaterialFormat(money);
    }

    private String formatRevenue(final int money) {
        return FormatUtil.parseMoneyToMaterialFormat(money);
    }
}
