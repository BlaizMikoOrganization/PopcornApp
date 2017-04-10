package com.blaizmiko.popcornapp.ui.tvshows.details.info;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.rating.RatingModel;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.SeasonTvShowModel;
import com.blaizmiko.popcornapp.data.models.tvshows.DetailedTvShowModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseInfoFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingView;

import java.util.List;

import butterknife.BindView;

public class InfoTvShowFragment extends BaseInfoFragment implements InfoTvShowView, RatingView {

    public static InfoTvShowFragment newInstance() {
        return new InfoTvShowFragment();
    }

    @InjectPresenter
    InfoTvShowPresenter infoTvShowPresenter;
    protected InfoSeasonsAdapter seasonsAdapter;
    protected RatingAdapter ratingAdapter;

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
    @BindView(R.id.recycler_view_info_tv_shows_seasons)
    protected RecyclerView seasonsRecyclerView;
    @BindView(R.id.recycler_view_base_info_ratings)
    protected RecyclerView ratingRecyclerView;

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

        Context context = getActivity().getApplicationContext();

        ratingAdapter = new RatingAdapter();
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        ratingRecyclerView.setLayoutManager(layoutManager);
        ratingRecyclerView.setAdapter(ratingAdapter);

        LinearLayoutManager seasonsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        seasonsAdapter = new InfoSeasonsAdapter(context);
        seasonsRecyclerView.setAdapter(seasonsAdapter);
        seasonsRecyclerView.setLayoutManager(seasonsLayoutManager);
        seasonsAdapter.setItemClickListener(this);

        infoTvShowPresenter.loadTvShowInfo(tvShowId);
    }

    //Info Presenter
    @Override
    public void setTvShowInfo(DetailedTvShowModel tvShowInfo) {
        setStoryLineView(tvShowInfo.getOverview());

        cinemaName = tvShowInfo.getTitle();
        cinemaReleaseDate = tvShowInfo.getFirstAirDate();

        trailersAdapter.update(tvShowInfo.getVideos().getResults());
        photosAdapter.update(tvShowInfo.getImages().getBackdrops());
        genresTagsAdapter.update(tvShowInfo.getGenres());
        statusTextView.setText(tvShowInfo.getStatus());
        seasonsAdapter.update(tvShowInfo.getSeasons());
        ratingPresenter.loadTvShowsRating(tvShowInfo.getExternalIds().getImdbId());

        similarCinemasPresenter.parseSimilarCinemas(tvShowInfo.getSimilarTvShows().getTvShows());
    }

    @Override
    public void updateChannels(String text) {
        networksTextView.setText(text);
    }

    @Override
    public void updateCreators(String creators) {
        createdByTextView.setText(creators);
    }

    @Override
    public void showFullRating(List<RatingModel> ratings) {
        ratingPresenter.addMovieDbRatingToRatingsList(ratings, getArguments().getDouble(Constants.Extras.RATING));
        ratingAdapter.update(ratings);
    }

    @Override
    public void updateAirDates(String firstAirDate, String lastAirDate) {
        firstAirDateTextView.setText(firstAirDate);
        lastAirDateTextView.setText(lastAirDate);
    }

    //Listeners
    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        super.onItemClick(view, position, adapter);

        switch(view.getId()) {
            case R.id.vertical_tile_item:
                final TileAdapter.Item item = ((TileAdapter) adapter).getItemByPosition(position);
                ActivityNavigator.startDetailsTvShowActivity(getActivity(),
                        item.getId(),
                        item.getTitle(),
                        item.getBackdropUrl(),
                        item.getPosterUrl(),
                        item.getRating());
                break;

            case R.id.layout_seasons_tv_show:
                ActivityNavigator.startTvSeasonActivity(getActivity(), tvShowId, cinemaName,
                        ((InfoSeasonsAdapter)adapter).getItemByPosition(position).getSeasonNumber());
                break;
        }
    }
}
