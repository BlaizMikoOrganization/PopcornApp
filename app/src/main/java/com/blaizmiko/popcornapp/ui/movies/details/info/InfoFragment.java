package com.blaizmiko.popcornapp.ui.movies.details.info;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.blaizmiko.popcornapp.ui.all.presentation.photos.PhotosAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.blaizmiko.popcornapp.ui.all.presentation.trailers.TrailersAdapter;
import com.blaizmiko.popcornapp.ui.movies.details.ReviewAdapter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import java.util.ArrayList;

import butterknife.BindView;

public class InfoFragment extends BaseMvpFragment implements LoadProgressView, StorylineView, RecyclerViewListeners.OnItemClickListener, View.OnClickListener, InfoView {

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @InjectPresenter
    StorylinePresenter storylinePresenter;
    @InjectPresenter
    InfoPresenter infoPresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;

    public static final String TITLE = "Info";
    private Context context;
    private GenresTagsAdapter genresTagsAdapter;
    private TrailersAdapter trailersAdapter;
    private RatingAdapter ratingAdapter;
    private PhotosAdapter photosAdapter;
    private TileAdapter similarMoviesAdapter;
    private int movieId;

    @BindView(R.id.text_view_activity_movie_details_storyline)
    TextView storyLineTextView;
    @BindView(R.id.recycler_view_activity_movie_details_trailers)
    RecyclerView trailersRecyclerView;
    @BindView(R.id.recycler_view_activity_movie_details_photos)
    RecyclerView imagesRecyclerView;
    @BindView(R.id.recycler_view_activity_movie_details_ratings)
    RecyclerView recyclerViewRatings;
    @BindView(R.id.text_view_movie_details_release_date)
    TextView releaseDateTextView;
    @BindView(R.id.text_view_movie_details_budget)
    TextView budgetTextView;
    @BindView(R.id.text_view_movie_details_revenue)
    TextView revenueTextView;
    @BindView(R.id.text_view_movie_details_original_name)
    TextView originalNameTextView;
    @BindView(R.id.text_view_movie_details_runtime)
    TextView runtimeTextView;
    @BindView(R.id.recycler_view_movie_details_info_similar)
    RecyclerView similarMoviesRecyclerView;

    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        movieId = getArguments().getInt(Constants.Extras.ID);
        progressBar = (ProgressBar) getActivity().findViewById(R.id.progress_bar_activity_movie_details_load_progress);
        getActivity().findViewById(R.id.toolbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details_info, container, false);
    }

    @Override
    protected void bindViews() {
        context = getActivity().getApplicationContext();

        final LinearLayoutManager imagesLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        imagesRecyclerView.setLayoutManager(imagesLayoutManager);
        photosAdapter = new PhotosAdapter(context);
        imagesRecyclerView.setAdapter(photosAdapter);

        final LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        trailersRecyclerView.setLayoutManager(trailersLayoutManager);
        trailersAdapter = new TrailersAdapter(context);
        trailersRecyclerView.setAdapter(trailersAdapter);

        final LinearLayoutManager ratingsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRatings.setLayoutManager(ratingsLayoutManager);
        ratingAdapter = new RatingAdapter();
        recyclerViewRatings.setAdapter(ratingAdapter);

        final LinearLayoutManager similarMoviesLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        similarMoviesRecyclerView.setLayoutManager(similarMoviesLayoutManager);
        similarMoviesRecyclerView.setHasFixedSize(true);
        similarMoviesAdapter = new TileAdapter(context, TileAdapter.TileType.VERTICAL_TILE);
        similarMoviesAdapter.setItemClickListener(this);
        similarMoviesRecyclerView.setAdapter(similarMoviesAdapter);

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

    public void setInfo(DetailedMovie movie) {
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
        //genresTagsAdapter.update(movie.getGenres());
        trailersAdapter.update(movie.getVideos().getResults());
        photosAdapter.update(movie.getPictures().getBackdrops());

        ArrayList<TileAdapter.Item> tileItems = new ArrayList<>();
        for (BriefMovie briefMovie : movie.getSimilarMovies().getMovies()) {
            tileItems.add(new TileAdapter.Item(briefMovie.getId(), briefMovie.getPosterPath(), briefMovie.getTitle(), briefMovie.getVoteAverage(), briefMovie.getBackdropPath(), briefMovie.getPosterPath()));
        }
        similarMoviesAdapter.update(tileItems);
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        TileAdapter tileAdapter = (TileAdapter) adapter;
        final int movieId = tileAdapter.getItemByPosition(position).getId();
        final String movieTitle = tileAdapter.getItemByPosition(position).getTitle();
        final double movieRating = tileAdapter.getItemByPosition(position).getRating();
        final String movieBackdropUrl = tileAdapter.getItemByPosition(position).getBackdropUrl();
        final String moviePosterUrl = tileAdapter.getItemByPosition(position).getPosterUrl();
        ActivityNavigator.startMovieDetailsActivity(getActivity(), movieId, movieTitle, movieRating, movieBackdropUrl, moviePosterUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_activity_movie_details_storyline:
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
