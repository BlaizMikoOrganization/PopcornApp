package com.blaizmiko.popcornapp.ui.fragments.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.presentation.presenters.movies.DetailMoviesPresenter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;

public class DetailMoviesFragment extends BaseMvpFragment {

    public static DetailMoviesFragment newInstance() {
        return new DetailMoviesFragment();
    }

    @InjectPresenter
    DetailMoviesPresenter mDetailMoviesPresenter;

    private int mMovieId;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        mMovieId = getArguments().getInt("id");
        return inflater.inflate(R.layout.fragment_detail_movies, container, false);
    }

    @Override
    protected void bindViews() {
        mDetailMoviesPresenter.loadMovie(mMovieId);

    }

}
