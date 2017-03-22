package com.blaizmiko.popcornapp.ui.all.presentation.details.info.tvshow;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.rating.Rating;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Channel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.Creator;
import com.blaizmiko.popcornapp.data.models.tvshowsNew.BaseTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshowsNew.DetailedTvShowModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.info.base.BaseInfoFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class InfoTvShowFragment extends BaseInfoFragment implements InfoTvShowView, RatingView {

    public static InfoTvShowFragment newInstance() {
        return new InfoTvShowFragment();
    }

    @InjectPresenter
    InfoTvShowPresenter infoTvShowPresenter;

    private int tvShowId;

    @BindView(R.id.text_view_info_tv_shows_first_air_date)
    protected TextView firstAirDateTextView;
    @BindView(R.id.text_view_info_tv_shows_last_air_date)
    protected TextView lastAirDateTextView;
    @BindView(R.id.text_view_info_tv_shows_networks)
    protected TextView networksTextView;
    @BindView(R.id.text_view_info_tv_shows_created_by)
    protected TextView createdByTextView;
    @BindView(R.id.text_view_info_tv_shows_status)
    protected TextView statusTextView;

    //Life Cycle Methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvShowId = getArguments().getInt(Constants.Extras.ID);
    }

    //Bind views methods
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, R.layout.fragment_info_tv_shows);
        return inflater.inflate(R.layout.fragment_info_tv_shows, container, false);
    }

    @Override
    protected void bindViews() {
        initBaseAdapters();
        //similarAdapter.setItemClickListener(this);
        infoTvShowPresenter.loadTvShowInfo(tvShowId);
    }

    //Info Presenter
    @Override
    public void setTvShowInfo(DetailedTvShowModel tvShowInfo) {
        setStoryLineView(tvShowInfo.getOverview());

        trailersAdapter.update(tvShowInfo.getVideos().getResults());
        photosAdapter.update(tvShowInfo.getImages().getBackdrops());
        genresTagsAdapter.update(tvShowInfo.getGenres());

        firstAirDateTextView.setText(tvShowInfo.getFirstAirDate());
        lastAirDateTextView.setText(tvShowInfo.getLastAirDate());
        statusTextView.setText(tvShowInfo.getStatus());

        infoTvShowPresenter.getSimilarTvShows(tvShowInfo.getSimilarTvShows().getList());
        ratingPresenter.loadRating(tvShowInfo.getExternalIds().getImdbId());

        infoTvShowPresenter.getFormattedChannels(tvShowInfo.getChannels());
        infoTvShowPresenter.getFormattedCreators(tvShowInfo.getCreators());
    }

    @Override
    public void setSimilarTvShowsAdapter(List<TileAdapter.Item> items) {
        similarAdapter.update(items);
    }

    @Override
    public void setFormattedChannels(String text) {
        networksTextView.setText(text);
    }

    @Override
    public void setFormattedCreators(String creators) {
        createdByTextView.setText(creators);
    }

    @Override
    public void setFullRating(Rating rating) {
        ratingAdapter.update(rating);
    }


    //Listeners
    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        final TileAdapter.Item item = ((TileAdapter) adapter).getItemByPosition(position);
        ActivityNavigator.startDetailsTvShowActivity(getActivity(),
                item.getId(),
                item.getTitle(),
                item.getBackdropUrl(),
                item.getPosterUrl());
    }
}
