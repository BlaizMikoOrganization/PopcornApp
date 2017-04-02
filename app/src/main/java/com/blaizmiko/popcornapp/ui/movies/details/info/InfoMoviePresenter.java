package com.blaizmiko.popcornapp.ui.movies.details.info;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.movies.BaseMovieModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class InfoMoviePresenter extends BaseMvpPresenter<InfoMovieView> {
    @Inject
    MovieDbApi movieDbApi;

    InfoMoviePresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadMovieInfo(int movieId) {
        getViewState().startLoad();

        final Subscription creditsMovieSubscription = movieDbApi.getMovieInfo(movieId, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.InfoDetailsMovieAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    getViewState().setMovieInfo(info);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(creditsMovieSubscription);
    }

    public void getSimilarMovies(List<BaseMovieModel> similarMovies) {
        ArrayList<TileAdapter.Item> tileItems = new ArrayList<>();
        for (BaseMovieModel similarMovie : similarMovies) {
            tileItems.add(new TileAdapter.Item(similarMovie.getId(), similarMovie.getPosterPath(), similarMovie.getTitle(), similarMovie.getVoteAverage(), similarMovie.getBackdropPath(), similarMovie.getPosterPath()));
        }
        getViewState().setSimilarMoviesAdapter(tileItems);
    }

    public void getFormattedReleaseDate(String releaseDate) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(releaseDate);
            final String releaseDateText = new SimpleDateFormat("MMMM dd, yyyy").format(date);
            getViewState().setFormattedReleaseDate(releaseDateText);
        } catch (ParseException exception) {
        }
    }

    public void getFormattedRuntime(int runtime) {
        final int minutesInHour = 60;
        int minutes = runtime % minutesInHour;
        int hours = (runtime - minutes) / minutesInHour;
        getViewState().setFormattedRuntime(hours + SymbolUtil.HOUR + SymbolUtil.SPACE + minutes + SymbolUtil.MINUTE);
    }

    public void getFormattedBudget(String money) {
        getViewState().setFormattedBudget(addSpacesToMoney(new StringBuffer(money), money.length()));
    }

    public void getFormattedRevenue(String money) {
        getViewState().setFormattedRevenue(addSpacesToMoney(new StringBuffer(money), money.length()));
    }

    private String addSpacesToMoney(StringBuffer money, int pos) {
        final int THOUSANDTH_DIGIT = 3;
        if (pos < 0) return money + SymbolUtil.SPACE +SymbolUtil.DOLAR;
        money = money.insert(pos, SymbolUtil.SPACE);
        return addSpacesToMoney(money, pos - THOUSANDTH_DIGIT);
    }
}
