package com.blaizmiko.popcornapp.ui.fragments.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.movies.TopRatedMovies;
import com.blaizmiko.popcornapp.models.movies.UpcomingMovies;
import com.blaizmiko.popcornapp.presentation.presenters.movies.NowMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.TopRatedMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.UpcomingMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.NowMoviesView;
import com.blaizmiko.popcornapp.presentation.views.movies.PopularMoviesView;
import com.blaizmiko.popcornapp.presentation.views.movies.TopRatedMoviesView;
import com.blaizmiko.popcornapp.presentation.views.movies.UpcomingMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;

public class MoviesFragment extends BaseMvpFragment implements NowMoviesView, PopularMoviesView, TopRatedMoviesView, UpcomingMoviesView {

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @InjectPresenter
    NowMoviesPresenter mNowMoviesPresenter;

    @InjectPresenter
    PopularMoviesPresenter mPopularMoviesPresenter;

    @InjectPresenter
    TopRatedMoviesPresenter mTopRatedMoviesPresenter;

    @InjectPresenter
    UpcomingMoviesPresenter mUpcomingMoviesPresenter;

    private TileAdapter mNowPlayingMoviesAdapter, mPopularMoviesAdapter, mTopRatedMoviesAdapter, mUpcomingMoviesAdapter;

    //Bind views
    @BindView(R.id.fragment_movies_now_playing_recycler_view)
    protected RecyclerView mNowMoviesRecyclerView;

    @BindView(R.id.fragment_movies_top_rated_movies_recycler_view)
    protected RecyclerView mTopRatedMoviesRecyclerView;

    @BindView(R.id.fragment_movies_popular_movies_recycler_view)
    protected RecyclerView mPopularMoviesRecyclerView;

    @BindView(R.id.fragment_movies_progress_bar)
    protected ProgressBar mProgressBar;

    @BindView(R.id.fragment_movies_upcoming_movies_recycler_view)
    protected RecyclerView mUpcomingMoviesRecyclerView;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    //Init methods
    @Override
    protected void bindViews() {
        initAdapters();

        mNowMoviesPresenter.loadNowMoviesList();
        mPopularMoviesPresenter.loadPopularMoviesList();
        mTopRatedMoviesPresenter.loadTopRatedMoviesList();
        mUpcomingMoviesPresenter.loadUpcomingMoviesList();
    }

    private void initAdapters(){
        mNowPlayingMoviesAdapter = initAdapter(mNowMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.HORIZONTAL_TILE);
        mPopularMoviesAdapter = initAdapter(mPopularMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
        mTopRatedMoviesAdapter = initAdapter(mTopRatedMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
        mUpcomingMoviesAdapter = initAdapter(mUpcomingMoviesRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
    }

    private TileAdapter initAdapter(RecyclerView recyclerView, int layoutManagerType, TileAdapter.TileType tileType) {
        final Context context = getActivity().getApplicationContext();

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, layoutManagerType, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        TileAdapter adapter = new TileAdapter(context, tileType);
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    //Movies View
    @Override
    public void showProgress() {
        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void setNowMoviesList(final List<TileAdapter.Item> nowMoviesCells) {
        mNowPlayingMoviesAdapter.update(nowMoviesCells);
    }

    @Override
    public void setPopularMoviesList(final List<TileAdapter.Item> popularMoviesCells) {
        mPopularMoviesAdapter.update(popularMoviesCells);
    }

    @Override
    public void setTopRatedMoviesList(final List<TileAdapter.Item> topRatedMoviesCells) {
        mTopRatedMoviesAdapter.update(topRatedMoviesCells);
    }

    @Override
    public void setUpcomingMoviesList(final List<TileAdapter.Item> upcomingMoviesCells) {
        mUpcomingMoviesAdapter.update(upcomingMoviesCells);
    }
}
