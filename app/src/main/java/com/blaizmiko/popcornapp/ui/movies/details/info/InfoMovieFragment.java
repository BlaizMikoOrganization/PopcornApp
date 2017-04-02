package com.blaizmiko.popcornapp.ui.movies.details.info;

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
import com.blaizmiko.popcornapp.data.models.movies.DetailedMovieModel;
import com.blaizmiko.popcornapp.data.models.rating.RatingModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseInfoFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingAdapter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import java.util.List;

import butterknife.BindView;

public class InfoMovieFragment extends BaseInfoFragment implements InfoMovieView, RecyclerViewListeners.OnItemClickListener {

    public static InfoMovieFragment newInstance() {
        return new InfoMovieFragment();
    }


    @BindView(R.id.text_view_info_movie_release_date)
    protected TextView releaseDateTextView;
    @BindView(R.id.text_view_info_movie_budget)
    protected TextView budgetTextView;
    @BindView(R.id.text_view_info_movie_revenue)
    protected TextView revenueTextView;
    @BindView(R.id.text_view_info_movie_original_name)
    protected TextView originalNameTextView;
    @BindView(R.id.text_view_info_movie_runtime)
    protected TextView runtimeTextView;
    @BindView(R.id.recycler_view_base_info_ratings)
    protected RecyclerView ratingRecyclerView;

    private int movieId;
    RatingAdapter ratingAdapter;

    @InjectPresenter
    InfoMoviePresenter infoMoviePresenter;

    //Life Cycle Methods
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        movieId = getArguments().getInt(Constants.Extras.ID);
    }

    //BindViews
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState){
        super.onCreateView(inflater, container, R.layout.fragment_info_movies);
        return inflater.inflate(R.layout.fragment_info_movies, container, false);
    }
    @Override
    public void bindViews() {
        initBaseAdapters();

        ratingAdapter = new RatingAdapter(RatingAdapter.CinemaType.MOVIE);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        ratingRecyclerView.setLayoutManager(layoutManager);
        ratingRecyclerView.setAdapter(ratingAdapter);

        infoMoviePresenter.loadMovieInfo(movieId);
    }

    public void setMovieInfo(DetailedMovieModel movie) {
        setStoryLineView(movie.getOverview());

        cinemaName = movie.getTitle();
        cinemaReleaseDate = movie.getReleaseDate();

        trailersAdapter.update(movie.getVideos().getResults());
        photosAdapter.update(movie.getImages().getBackdrops());
        genresTagsAdapter.update(movie.getGenres());

        infoMoviePresenter.getSimilarMovies(movie.getSimilarMovies().getMovies());
        infoMoviePresenter.getFormattedBudget(Integer.toString(movie.getBudget()));
        infoMoviePresenter.getFormattedRevenue(Integer.toString(movie.getRevenue()));
        infoMoviePresenter.getFormattedReleaseDate(movie.getReleaseDate());
        infoMoviePresenter.getFormattedRuntime(movie.getRuntime());
        originalNameTextView.setText(movie.getTitle());

        ratingPresenter.loadRating(movie.getImdbId());
    }

    //Info Movie Presenter
    public void setSimilarMoviesAdapter(List<TileAdapter.Item> items) {
        similarAdapter.update(items);
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

    //RatingMovieResponse presenter
    @Override
    public void setFullRating(List<RatingModel> ratings) {
        ratingPresenter.addMovieDbRatingToRatingsList(ratings, getArguments().getDouble(Constants.Extras.RATING));
        ratingAdapter.update(ratings);
    }

    //Listeners
    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        super.onItemClick(view, position, adapter);
        switch (view.getId()) {
            case R.id.vertical_tile_item:
                final TileAdapter.Item item = ((TileAdapter) adapter).getItemByPosition(position);
                ActivityNavigator.startDetailsMovieActivity(getActivity(),
                        item.getId(),
                        item.getTitle(),
                        item.getBackdropUrl(),
                        item.getPosterUrl(),
                        item.getRating());
                break;
        }
    }
}
