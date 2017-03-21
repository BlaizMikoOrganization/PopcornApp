package com.blaizmiko.popcornapp.ui.all.presentation.details.info.movie;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.moviesNew.DetailedMovieModel;
import com.blaizmiko.popcornapp.data.models.rating.Rating;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.details.info.base.BaseInfoFragment;
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

    private int movieId;
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
        similarAdapter.setItemClickListener(this);

        infoMoviePresenter.loadMovieInfo(movieId);
    }

    public void setMovieInfo(DetailedMovieModel movie) {
        setStoryLineView(movie.getOverview());
        trailersAdapter.update(movie.getVideos().getResults());
        photosAdapter.update(movie.getImages().getBackdrops());
        genresTagsAdapter.update(movie.getGenres());

        infoMoviePresenter.getSimilarMovies(movie.getSimilarMovies().getList());
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

    //Rating presenter
    @Override
    public void setFullRating(Rating rating) {
        ratingAdapter.update(rating);
    }

    //Listeners
    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        final TileAdapter.Item item = ((TileAdapter) adapter).getItemByPosition(position);
        ActivityNavigator.startDetailsMovieActivity(getActivity(),
                item.getId(),
                item.getTitle(),
                item.getBackdropUrl(),
                item.getPosterUrl());
    }
}
