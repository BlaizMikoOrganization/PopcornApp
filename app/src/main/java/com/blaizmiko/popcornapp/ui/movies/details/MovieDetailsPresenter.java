package com.blaizmiko.popcornapp.ui.movies.details;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.cast.Crew;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class MovieDetailsPresenter extends BaseMvpPresenter<MovieDetailsView> {

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
                    System.out.println("param");
                    System.out.println(movie.getMovieReviews().getReviews().get(0).getContent());
                    getViewState().setMovie(movie);
                }, error -> {
                    System.out.println("error");
                    error.getStackTrace();
                    System.out.println(error.getMessage());
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(detailMovieSubscription);
    }

    public void getDirector(List<Crew> crew) {
        final String DIRECTOR = "Director";
        String directorName = StringUtil.NOT_AVAILABLE_STRING;
        for (Crew member : crew) {
            if (member.getJob().equals(DIRECTOR)) {
                directorName = member.getName();
                break;
            }
        }
        getViewState().setMovieDirector(directorName);
    }

    public void getFormattedReleaseDate(String releaseDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(releaseDate);
            String push = new SimpleDateFormat("MMMM dd, yyyy").format(date);
            getViewState().setFormattedReleaseDate(push);
        } catch (ParseException exception) {
            getViewState().showError();
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
