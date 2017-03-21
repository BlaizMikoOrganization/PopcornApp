package com.blaizmiko.popcornapp.ui.all.presentation.details.info.tvshow;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Channel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Creator;
import com.blaizmiko.popcornapp.data.models.tvshowsNew.BaseTvShowModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.BaseMvpPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
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

        final Subscription creditsMovieSubscription = movieDbApi.getTvShow(tvShowId, Constants.MovieDbApi.IncludeImageLanguage, Constants.MovieDbApi.InfoDetailsTvShowAppendToResponse)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    getViewState().setTvShowInfo(info);
                }, error -> {
                    getViewState().finishLoad();
                    getViewState().showError();
                }, () -> getViewState().finishLoad());
        unSubscribeOnDestroy(creditsMovieSubscription);
    }

    public void getSimilarTvShows(List<BaseTvShowModel> similarTvShows) {
        ArrayList<TileAdapter.Item> tileItems = new ArrayList<>();
        for (BaseTvShowModel similar : similarTvShows) {
            tileItems.add(new TileAdapter.Item(similar.getId(), similar.getPosterPath(), similar.getName(), similar.getVoteAverage(), similar.getBackdropPath(), similar.getPosterPath()));
        }
        getViewState().setSimilarTvShowsAdapter(tileItems);
    }

    public void getFormattedChannels(List<Channel> channels) {
        String channelsText = StringUtil.EMPTY_STRING;
        for (Channel channel : channels) {
            channelsText += channel.getName() +SymbolUtil.COMMA +SymbolUtil.SPACE;
        }
        getViewState().setFormattedChannels(cutLastSymbolsInSequence(channelsText));
    }

    public void getFormattedCreators(List<Creator> creators) {
        String creatorsText = StringUtil.EMPTY_STRING;
        for (Creator creator : creators) {
            creatorsText += creator.getName() +SymbolUtil.COMMA + SymbolUtil.SPACE;
        }
        getViewState().setFormattedCreators(cutLastSymbolsInSequence(creatorsText));
    }

    private String cutLastSymbolsInSequence(String text) {
        final int START_INDEX = 0;
        final int LAST_INDEX = text.length() - 2;
        return text.substring(START_INDEX, LAST_INDEX);
    }

}
