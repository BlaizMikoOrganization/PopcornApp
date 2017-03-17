package com.blaizmiko.popcornapp.ui.movies.details.info;

import android.icu.text.IDNA;

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
public class InfoPresenter extends BaseMvpPresenter<InfoView>{
    @Inject
    MovieDbApi movieDbApi;

    InfoPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadInfo(int movieId) {
        getViewState().startLoad();
        final Subscription creditsMovieSubscription = movieDbApi.getMovieInfo(movieId, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.MovieInfoAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    getViewState().setInfo(info);
                }, error -> {
                    error.getStackTrace();
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());

        unSubscribeOnDestroy(creditsMovieSubscription);
    }


    public void getFormattedReleaseDate(String releaseDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = format.parse(releaseDate);
            String push = new SimpleDateFormat("MMMM dd, yyyy").format(date);
            getViewState().setFormattedReleaseDate(push);
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
