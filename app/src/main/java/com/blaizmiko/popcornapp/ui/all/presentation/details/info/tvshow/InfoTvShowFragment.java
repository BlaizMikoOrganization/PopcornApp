package com.blaizmiko.popcornapp.ui.all.presentation.details.info.tvshow;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.data.models.moviesNew.BaseMovieModel;
import com.blaizmiko.popcornapp.data.models.tvshowsNew.BaseTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshowsNew.DetailedTvShowModel;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.info.base.BaseInfoFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.details.info.movie.InfoMovieFragment;

import java.util.ArrayList;

public class InfoTvShowFragment extends BaseInfoFragment implements InfoTvShowView {

    public static InfoTvShowFragment newInstance() {
        return new InfoTvShowFragment();
    }
    @InjectPresenter
    InfoTvShowPresenter infoTvShowPresenter;


    @Override
    protected void bindViews() {

    }

    @Override
    public void setTvShowInfo(DetailedTvShowModel tvShowInfo) {
        setInfo(tvShowInfo);

        ArrayList<TileAdapter.Item> tileItems = new ArrayList<>();
        for (BaseTvShowModel similarMovie : tvShowInfo.getSimilarTvShows().getList()) {
            tileItems.add(new TileAdapter.Item(similarMovie.getId(), similarMovie.getPosterPath(), similarMovie.getName(), similarMovie.getVoteAverage(), similarMovie.getBackdropPath(), similarMovie.getPosterPath()));
        }
        similarAdapter.update(tileItems);
    }
}
