package com.blaizmiko.popcornapp.ui.fragments.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.movies.NowPlayingMovies;
import com.blaizmiko.popcornapp.models.movies.PopularMovies;
import com.blaizmiko.popcornapp.presentation.presenters.movies.NowMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.NowMoviesView;
import com.blaizmiko.popcornapp.presentation.views.popularMovies.PopularMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.movies.NowPlayingMoviesAdapter;
import com.blaizmiko.popcornapp.ui.adapters.movies.PopularMoviesAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;

import butterknife.BindView;

public class MoviesFragment extends BaseMvpFragment implements NowMoviesView, PopularMoviesView {

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @InjectPresenter
    NowMoviesPresenter mNowMoviesPresenter;

    @InjectPresenter
    PopularMoviesPresenter mPopularMoviesPresenter;

    private NowPlayingMoviesAdapter mNowPlayingMoviesAdapter;
    private PopularMoviesAdapter mPopularMoviesAdapter;

    //Bind views
    @BindView(R.id.fragment_movies_recycler_view)
    protected RecyclerView mNowMoviesRecyclerView;
    @BindView(R.id.fragment_movies_progress_bar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.fragment_movies_popular_movies_recycler_view)
    protected RecyclerView mPopularMoviesGridView;

    private final int POPULAR_MOVIES_COLUMN_COUNT = 3;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    //Init methods
    @Override
    protected void bindViews() {
        initAdapter();

        mNowMoviesPresenter.loadNowMoviesList();
        mPopularMoviesPresenter.loadPopularMoviesList();
    }

    private void initAdapter(){
        final Context context = getActivity().getApplicationContext();

        //Now playing movies
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mNowMoviesRecyclerView.setLayoutManager(layoutManager);
        mNowMoviesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mNowPlayingMoviesAdapter = new NowPlayingMoviesAdapter(context);
        mNowMoviesRecyclerView.setAdapter(mNowPlayingMoviesAdapter);

        //Popular movies
        final RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(context, POPULAR_MOVIES_COLUMN_COUNT);
        mPopularMoviesGridView.setLayoutManager(gridLayoutManager);
        mPopularMoviesGridView.setHasFixedSize(true);

        mPopularMoviesAdapter = new PopularMoviesAdapter(context);
        mPopularMoviesGridView.setAdapter(mPopularMoviesAdapter);
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
    public void setNowMoviesList(final NowPlayingMovies nowPlayingMovies) {
        mNowPlayingMoviesAdapter.update(nowPlayingMovies.getMovies());
    }

    @Override
    public void setPopularMoviesList(final PopularMovies popularMovies) {
        mPopularMoviesAdapter.update(popularMovies.getMovies());
    }
}
