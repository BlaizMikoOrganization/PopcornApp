package com.blaizmiko.popcornapp.ui.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.details.DetailsActivity;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.blaizmiko.popcornapp.ui.movies.nowplaying.NowPlayingMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.nowplaying.NowPlayingMoviesView;
import com.blaizmiko.popcornapp.ui.movies.popular.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.popular.PopularMoviesView;
import com.blaizmiko.popcornapp.ui.movies.top.TopMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.top.TopMoviesView;
import com.blaizmiko.popcornapp.ui.movies.upcoming.UpcomingMoviesPresenter;
import com.blaizmiko.popcornapp.ui.movies.upcoming.UpcomingMoviesView;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import com.blaizmiko.ui.listeners.RecyclerViewLoadMore;

import java.util.List;

import butterknife.BindView;

public class MoviesFragment extends BaseMvpFragment implements RecyclerViewListeners.OnItemClickListener, RecyclerViewListeners.OnLoadMoreListener, LoadProgressView, NowPlayingMoviesView, PopularMoviesView, TopMoviesView, UpcomingMoviesView {

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }
    @InjectPresenter
    NowPlayingMoviesPresenter nowPlayingMoviesPresenter;
    @InjectPresenter
    PopularMoviesPresenter popularMoviesPresenter;
    @InjectPresenter
    TopMoviesPresenter topRatedMoviesPresenter;
    @InjectPresenter
    UpcomingMoviesPresenter upcomingMoviesPresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;

    private TileAdapter nowPlayingMoviesAdapter, popularMoviesAdapter, topMoviesAdapter, upcomingMoviesAdapter;

    //Bind views
    @BindView(R.id.recycler_view_fragment_movies_now_playing)
    protected RecyclerView nowMoviesRecyclerView;
    @BindView(R.id.recycler_view_fragment_movies_top_movies)
    protected RecyclerView topRatedMoviesRecyclerView;
    @BindView(R.id.recycler_view_fragment_movies_popular)
    protected RecyclerView popularMoviesRecyclerView;
    @BindView(R.id.progress_bar_fragment_tv_shows_load_progress)
    protected ProgressBar progressBar;
    @BindView(R.id.recycler_view_fragment_movies_upcoming_movies)
    protected RecyclerView upcomingMoviesRecyclerView;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    //Init methods
    @Override
    protected void bindViews() {
        initAdapters();
        nowPlayingMoviesPresenter.loadNowMoviesList();
        popularMoviesPresenter.loadPopularMoviesList();
        topRatedMoviesPresenter.loadTopRatedMoviesList();
        upcomingMoviesPresenter.loadUpcomingMoviesList();
    }

    private void initAdapters() {
        final Context context = getActivity().getApplicationContext();

        nowPlayingMoviesAdapter = initAdapter(context, nowMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.HORIZONTAL_TILE);
        popularMoviesAdapter = initAdapter(context, popularMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
        topMoviesAdapter = initAdapter(context, topRatedMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
        upcomingMoviesAdapter = initAdapter(context, upcomingMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
    }

    private TileAdapter initAdapter(final Context context, final RecyclerView recyclerView, final int layoutManagerType, final TileAdapter.TileType tileType) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, layoutManagerType, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerViewLoadMore(this, linearLayoutManager));

        final TileAdapter adapter = new TileAdapter(context, tileType);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(this);
        return adapter;
    }

    //Movies presenters
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

    @Override
    public void setNowMoviesList(final List<TileAdapter.Item> nowMoviesCells) {
        nowPlayingMoviesAdapter.add(nowMoviesCells);
    }

    @Override
    public void setPopularMoviesList(final List<TileAdapter.Item> popularMoviesCells) {
        popularMoviesAdapter.add(popularMoviesCells);
    }

    @Override
    public void setTopMoviesList(final List<TileAdapter.Item> topMovies) {
        topMoviesAdapter.add(topMovies);
    }

    @Override
    public void setUpcomingMoviesList(final List<TileAdapter.Item> upcomingMoviesCells) {
        upcomingMoviesAdapter.add(upcomingMoviesCells);
    }

    //LoadProgress presenter
    public void showProgress() {
        if (progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar.getVisibility() != View.GONE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    //Listeners
    @Override
    public void onLoadMore(final RecyclerView recyclerView, final int nextPage) {
        switch (recyclerView.getId()) {
            case R.id.recycler_view_fragment_movies_now_playing:
                nowPlayingMoviesPresenter.loadNowMoviesList();
                break;
            case R.id.recycler_view_fragment_movies_popular:
                popularMoviesPresenter.loadPopularMoviesList();
                break;
            case R.id.recycler_view_fragment_movies_top_movies:
                topRatedMoviesPresenter.loadTopRatedMoviesList();
                break;
            case R.id.recycler_view_fragment_movies_upcoming_movies:
                upcomingMoviesPresenter.loadUpcomingMoviesList();
                break;
        }
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final TileAdapter tileAdapter = (TileAdapter) adapter;

        final int movieId = tileAdapter.getItemByPosition(position).getId();
        final String movieTitle = tileAdapter.getItemByPosition(position).getTitle();
        final String movieBackdropUrl = tileAdapter.getItemByPosition(position).getBackdropUrl();
        final String moviePosterUrl = tileAdapter.getItemByPosition(position).getPosterUrl();
        ActivityNavigator.startDetailsMovieActivity(getActivity(), movieId, movieTitle, movieBackdropUrl, moviePosterUrl);
    }
}
