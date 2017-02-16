package com.blaizmiko.popcornapp.ui.fragments.movies;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.models.movie.NowPlayingMovies;
import com.blaizmiko.popcornapp.models.movie.PopularMovies;
import com.blaizmiko.popcornapp.presentation.presenters.nowMovies.NowMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.popularMovies.PopularMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.views.nowMovies.NowMoviesView;
import com.blaizmiko.popcornapp.presentation.views.popularMovies.PopularMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.popularMovies.PopularMoviesAdapter;
import com.blaizmiko.popcornapp.ui.adapters.shortMovies.ShortMoviesAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class MoviesFragment extends BaseMvpFragment implements NowMoviesView, PopularMoviesView {

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @InjectPresenter
    NowMoviesPresenter mNowMoviesPresenter;

    @InjectPresenter
    PopularMoviesPresenter mPopularMoviesPresenter;

    private ShortMoviesAdapter mShortMoviesAdapter;
    private PopularMoviesAdapter mPopularMoviesAdapter;
    //Bind views
    @BindView(R.id.fragment_movies_recycler_view)
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

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mNowMoviesRecyclerView.setLayoutManager(layoutManager);
        mNowMoviesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mShortMoviesAdapter = new ShortMoviesAdapter(context);
        mNowMoviesRecyclerView.setAdapter(mShortMoviesAdapter);

        System.out.println("pish2");
        final RecyclerView.LayoutManager f = new GridLayoutManager(context, 3);
        mPopularMoviesGridView.setLayoutManager(f);
        mPopularMoviesGridView.setHasFixedSize(true);
        System.out.println("pish3");

        mPopularMoviesAdapter = new PopularMoviesAdapter(context);
        mPopularMoviesGridView.setAdapter(mPopularMoviesAdapter);
    }

    //ActorsView
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
        mShortMoviesAdapter.update(nowPlayingMovies.getMovies());
    }

    @Override
    public void setPopularMoviesList(final PopularMovies popularMovies) {
        System.out.println("pish");
        System.out.println(popularMovies.getMovies().size());
        mPopularMoviesAdapter.update(popularMovies.getMovies());
    }

    //Pish


}
