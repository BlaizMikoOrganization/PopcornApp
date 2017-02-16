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
import com.blaizmiko.popcornapp.models.movies.NowPlayingMovies;
import com.blaizmiko.popcornapp.presentation.presenters.movies.NowMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.NowMoviesView;
import com.blaizmiko.popcornapp.ui.adapters.movies.ShortMoviesAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;
import butterknife.BindView;

public class MoviesFragment extends BaseMvpFragment implements NowMoviesView {

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @InjectPresenter
    NowMoviesPresenter mNowMoviesPresenter;
    private ShortMoviesAdapter mShortMoviesAdapter;
    //Bind views
    @BindView(R.id.fragment_movies_recycler_view)
    protected RecyclerView mNowMoviesRecyclerView;
    @BindView(R.id.fragment_movies_progress_bar)
    protected ProgressBar mProgressBar;

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
    }

    private void initAdapter(){
        final Context context = getActivity().getApplicationContext();

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mNowMoviesRecyclerView.setLayoutManager(layoutManager);
        mNowMoviesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mShortMoviesAdapter = new ShortMoviesAdapter(context);
        mNowMoviesRecyclerView.setAdapter(mShortMoviesAdapter);
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
}
