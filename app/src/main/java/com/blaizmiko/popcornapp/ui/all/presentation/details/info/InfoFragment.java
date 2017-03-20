/*
package com.blaizmiko.popcornapp.ui.all.presentation.details.info;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.movies.BriefMovie;
import com.blaizmiko.popcornapp.data.models.movies.DetailedMovie;
import com.blaizmiko.popcornapp.data.models.rating.Rating;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.DetailsActivity;
import com.blaizmiko.popcornapp.ui.all.presentation.details.info.base.BaseInfoFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.blaizmiko.popcornapp.ui.all.presentation.photos.PhotosAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingView;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.blaizmiko.popcornapp.ui.all.presentation.trailers.TrailersAdapter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoFragment extends BaseInfoFragment implements RatingView, LoadProgressView, StorylineView, RecyclerViewListeners.OnItemClickListener, View.OnClickListener, InfoView {

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @InjectPresenter
    StorylinePresenter storylinePresenter;
    @InjectPresenter
    InfoPresenter infoPresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;
    @InjectPresenter
    RatingPresenter ratingPresenter;

    private DetailsActivity.Type type;
    public static final String TITLE = "Info";
    private Context context;
    private GenresTagsAdapter genresTagsAdapter;
    private TrailersAdapter trailersAdapter;
    private RatingAdapter ratingAdapter;
    private PhotosAdapter photosAdapter;
    private TileAdapter similarMoviesAdapter;
    private int movieId;

    @BindView(R.id.text_view_info_storyline)
    TextView storyLineTextView;
    @BindView(R.id.recycler_view_info_trailers)
    RecyclerView trailersRecyclerView;
    @BindView(R.id.recycler_view_info_photos)
    RecyclerView imagesRecyclerView;
    @BindView(R.id.recycler_view_info_ratings)
    RecyclerView recyclerViewRatings;
    @BindView(R.id.text_view_info_release_date)
    TextView releaseDateTextView;
    @BindView(R.id.text_view_info_budget)
    TextView budgetTextView;
    @BindView(R.id.text_view_info_revenue)
    TextView revenueTextView;
    @BindView(R.id.text_view_info_original_name)
    TextView originalNameTextView;
    @BindView(R.id.text_view_info_runtime)
    TextView runtimeTextView;
    @BindView(R.id.recycler_view_movie_details_info_similar)
    RecyclerView similarMoviesRecyclerView;

    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        movieId = getArguments().getInt(Constants.Extras.ID);
        type = (DetailsActivity.Type)getArguments().getSerializable(Constants.Extras.TYPE);
        //progressBar = ButterKnife.findById(getActivity(), R.id.progress_bar_details_load);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, R.layout.fragment_info);
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    protected void bindViews() {
        super.bindViews();
        context = getActivity().getApplicationContext();

//        final LinearLayoutManager ratingsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        recyclerViewRatings.setLayoutManager(ratingsLayoutManager);
//        ratingAdapter = new RatingAdapter();
//        recyclerViewRatings.setAdapter(ratingAdapter);
        infoPresenter.loadInfo(movieId);
    }


    @Override
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    @Override
    public void startLoad() {
        loadProgressPresenter.showProgress();
    }

    public void setMovieInfo(DetailedMovie movie) {
        infoPresenter.getFormattedBudget(Integer.toString(movie.getBudget()));
        infoPresenter.getFormattedRevenue(Integer.toString(movie.getRevenue()));
        infoPresenter.getFormattedReleaseDate(movie.getReleaseDate());
        infoPresenter.getFormattedRuntime(movie.getRuntime());
        originalNameTextView.setText(movie.getOriginalTitle());

        storyLineTextView.setText(movie.getOverview());
        storyLineTextView.setOnClickListener(this);
        storylinePresenter.setExpandedLinesNumber(storyLineTextView.getLineCount());
        storylinePresenter.calculateNewSize();

        updateAdapters(movie);
        ratingPresenter.loadRating(movie.getImdbId());
    }


    public void setFormattedReleaseDate(String releaseDate) {
        releaseDateTextView.setText(releaseDate);
    }

    public void setFormattedRuntime(String runtime) {
        runtimeTextView.setText(runtime);
    }

    public void setFormattedBudget(String money) {
        budgetTextView.setText(money);
    }

    public void setFormattedRevenue(String money) {
        revenueTextView.setText(money);
    }

    private void updateAdapters(DetailedMovie movie) {
        trailersAdapter.update(movie.getVideosResponseModel().getResults());
        photosAdapter.update(movie.getImagesResponseModel().getBackdrops());

        ArrayList<TileAdapter.Item> tileItems = new ArrayList<>();
        for (BriefMovie briefMovie : movie.getSimilarMovies().getMovies()) {
            tileItems.add(new TileAdapter.Item(briefMovie.getId(), briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage(), briefMovie.getBackdropPath(), briefMovie.getPosterPath()));
        }
        similarMoviesAdapter.update(tileItems);
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        final TileAdapter.Item item = ((TileAdapter) adapter).getItemByPosition(position);
        final int id = item.getId();
        final String title = item.getTitle();
        final double rating = item.getRating();
        final String backdropUrl = item.getBackdropUrl();
        final String posterUrl = item.getPosterUrl();
        ActivityNavigator.startDetailsActivity(getActivity(), type, id, title, rating, backdropUrl, posterUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_info_storyline:
                storylinePresenter.calculateNewSize();
                break;
        }
    }

    @Override
    public void changeStorylineSize(int lines) {
        storyLineTextView.setLines(lines);
    }

    @Override
    public void setFullRating(Rating rating) {
        ratingAdapter.update(rating);
    }

    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
*/
