package com.blaizmiko.popcornapp.ui.fragments.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.presentation.presenters.movies.NowMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.NowMoviesView;
import com.blaizmiko.popcornapp.presentation.views.popularMovies.PopularMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.movies.TileAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;

public class MoviesFragment extends BaseMvpFragment implements NowMoviesView, PopularMoviesView {

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @InjectPresenter
    NowMoviesPresenter mNowMoviesPresenter;

    @InjectPresenter
    PopularMoviesPresenter mPopularMoviesPresenter;

    private TileAdapter mNowPlayingMoviesAdapter;
    private TileAdapter mPopularMoviesAdapter;

    //Bind views
    @BindView(R.id.fragment_movies_now_playing_recycler_view)
    protected RecyclerView mNowMoviesRecyclerView;
    @BindView(R.id.fragment_movies_progress_bar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.fragment_movies_popular_movies_recycler_view)
    protected RecyclerView mPopularMoviesGridView;

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

        final LinearLayoutManager nowMoviesLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mNowMoviesRecyclerView.setLayoutManager(nowMoviesLayoutManager);
        mNowMoviesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mNowPlayingMoviesAdapter = new TileAdapter(context, R.layout.adapter_now_playing_movie_item);
        mNowMoviesRecyclerView.setAdapter(mNowPlayingMoviesAdapter);

        final LinearLayoutManager popularMoviesLayoutManger = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mPopularMoviesGridView.setLayoutManager(popularMoviesLayoutManger);
        mPopularMoviesGridView.setHasFixedSize(true);

        mPopularMoviesAdapter = new TileAdapter(getActivity().getApplicationContext(), R.layout.adapter_popular_movie_item);
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
    public void setNowMoviesList(final List<TileAdapter.Item> nowMoviesCells) {
        mNowPlayingMoviesAdapter.update(nowMoviesCells);
    }

    @Override
    public void setPopularMoviesList(final List<TileAdapter.Item> popularMoviesCells) {
        mPopularMoviesAdapter.update(popularMoviesCells);
    }
}
