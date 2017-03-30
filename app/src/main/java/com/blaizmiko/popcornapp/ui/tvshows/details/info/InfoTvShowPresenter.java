package com.blaizmiko.popcornapp.ui.tvshows.details.info;

import com.arellomobile.mvp.InjectViewState;
import com.blaizmiko.popcornapp.application.BaseApplication;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.network.api.MovieDbApi;
import com.blaizmiko.popcornapp.common.utils.StringUtil;
import com.blaizmiko.popcornapp.common.utils.SymbolUtil;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.ChannelTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.CreatorTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.SeasonTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.BaseTvShowModel;
import com.blaizmiko.popcornapp.data.models.videos.VideoModel;
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
                    System.out.println("similar");
                    for (BaseTvShowModel tvShow: info.getSimilarTvShows().getTvShows()) {
                        System.out.println(tvShow.getName());
                    }

                    System.out.println("videos");
                    for (VideoModel video : info.getVideos().getResults()) {
                        System.out.println(video.getName());
                    }
                    getViewState().setTvShowInfo(info);
                }, error -> {
                    System.out.println(error.getMessage());
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

    public void getFormattedChannels(List<ChannelTvShowModel> channels) {
        String channelsText = StringUtil.EMPTY_STRING;
        for (ChannelTvShowModel channel : channels) {
            channelsText += channel.getName() +SymbolUtil.COMMA +SymbolUtil.SPACE;
        }
        getViewState().setFormattedChannels(cutLastSymbolsInSequence(channelsText));
    }

    public void getFormattedCreators(List<CreatorTvShowModel> creators) {
        String creatorsText = StringUtil.EMPTY_STRING;
        for (CreatorTvShowModel creator : creators) {
            creatorsText += creator.getName() +SymbolUtil.COMMA + SymbolUtil.SPACE;
        }
        getViewState().setFormattedCreators(cutLastSymbolsInSequence(creatorsText));
    }

    private String cutLastSymbolsInSequence(String text) {
        final int START_INDEX = 0;
        final int LAST_INDEX = text.length() - 2;
        return text.substring(START_INDEX, LAST_INDEX);
    }

    public void getFormattedSeasonsReleaseDates(List<SeasonTvShowModel> seasons) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date date;
        for (int i = 0; i < seasons.size(); i++) {
            try {
                date = format.parse(seasons.get(i).getReleaseDate());
                String pish = new SimpleDateFormat("MMM, yyyy").format(date);
                seasons.get(i).setReleaseDate(pish);
            } catch (ParseException exception) {
                seasons.get(i).setReleaseDate(StringUtil.NOT_AVAILABLE_STRING);
            }
        }

        getViewState().setFormattedReleaseDate(seasons);
    }

}
