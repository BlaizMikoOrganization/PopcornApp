package com.blaizmiko.popcornapp.ui.tvshows.details.info;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.FormatUtil;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.tvshows.DetailedTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.ChannelTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.CreatorTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.SeasonTvShowModel;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@InjectViewState
public class InfoTvShowPresenter extends BaseMvpPresenter<InfoTvShowView>{
    @Inject
    MovieDbApi movieDbApi;

    InfoTvShowPresenter() {
        BaseApplication.getComponent().inject(this);
    }

    public void loadTvShowInfo(int tvShowId) {
        getViewState().startLoad();

        final Subscription creditsMovieSubscription = movieDbApi.getTvShowInfo(tvShowId, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.InfoDetailsTvShowAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    updateDescription(info);
                    Observable.from(info.getSeasons())
                            .filter(seasonTvShowModel ->
                                seasonTvShowModel.getSeasonNumber() != 0)
                            .toList()
                            .subscribe(seasonTvShowModels -> {
                                info.setSeasons(seasonTvShowModels);
                                getViewState().setTvShowInfo(info);
                            },
                                    error -> {
                                        getViewState().finishLoad();
                                        getViewState().showError();});
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(creditsMovieSubscription);
    }

    private void updateDescription(DetailedTvShowModel tvShowModel) {
        final String formattedFirstAirDate = formatFirstAirDate(tvShowModel.getFirstAirDate());
        final String formattedLastAirDate = formatLastAirDate(tvShowModel.getLastAirDate());
        getViewState().updateAirDates(formattedFirstAirDate, formattedLastAirDate);
        final String formattedChannels = formatChannels(tvShowModel.getChannels());
        getViewState().updateChannels(formattedChannels);
        final String formattedCreators = formatCreators(tvShowModel.getCreators());
        getViewState().updateCreators(formattedCreators);
    }

    private String formatFirstAirDate(final String firstAirDate) {
        return FormatUtil.parseDateToMaterialFormat(firstAirDate, FormatUtil.ResultMaterialDateType.FULL);
    }

    private String formatLastAirDate(final String lastAirDate) {
        return FormatUtil.parseDateToMaterialFormat(lastAirDate, FormatUtil.ResultMaterialDateType.FULL);
    }

    private String formatChannels(final List<ChannelTvShowModel> channels) {
        if (channels.size() == 0) return StringUtil.NOT_AVAILABLE_STRING;
        String result = StringUtil.EMPTY_STRING;
        for (ChannelTvShowModel channel : channels) {
            result += channel.getName() +SymbolUtil.COMMA +SymbolUtil.SPACE;
        }
        return cutLastTwoSymbols(result);
    }

    private String formatCreators(final List<CreatorTvShowModel> creators) {
        if (creators.size() == 0) return StringUtil.NOT_AVAILABLE_STRING;
        String result = StringUtil.EMPTY_STRING;
        for (CreatorTvShowModel creator : creators) {
            result += creator.getName() +SymbolUtil.COMMA +SymbolUtil.SPACE;
        }
        return cutLastTwoSymbols(result);
    }

    private String cutLastTwoSymbols(final String text) {
        final int START_INDEX = 0;
        final int LAST_INDEX = text.length() - 2;
        return text.substring(START_INDEX, LAST_INDEX);
    }
}
