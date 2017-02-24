package com.blaizmiko.popcornapp.ui.fragments.movies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.api.PealApi;
import com.blaizmiko.popcornapp.models.movies.Movie;
import com.blaizmiko.popcornapp.presentation.presenters.movies.DetailMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.DetailMoviesView;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import org.w3c.dom.Text;

import butterknife.BindView;

public class DetailMoviesFragment extends BaseMvpFragment implements DetailMoviesView {

    public static DetailMoviesFragment newInstance() {
        return new DetailMoviesFragment();
    }

    @InjectPresenter
    DetailMoviesPresenter mDetailMoviesPresenter;

    @BindView(R.id.fragment_detail_movie_backdrop_image_view)  ImageView mBackdropImageView;
    @BindView(R.id.fragment_detail_movie_poster_image_view)    ImageView mPosterImageView;
    @BindView(R.id.fragment_detail_movie_rating_text_view)     TextView mRatingTextView;
    @BindView(R.id.fragment_detail_movie_title_text_view)      TextView mTitleTextView;
    @BindView(R.id.fragment_detail_movie_story_line_text_view) TextView mStoryLineTextView;
    @BindView(R.id.fragment_detail_movie_rating_bar)           SimpleRatingBar mRatingBar;

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

    @Override
    public void setMovie(Movie movie) {
        mRatingTextView.setText(Double.toString(movie.getVoteAverage()));
        mTitleTextView.setText(movie.getTitle());
        mStoryLineTextView.setText(movie.getOverview());
        mRatingBar.setRating((float)movie.getVoteAverage());

        Glide.with(getActivity().getApplicationContext())
                .load(Constants.Api.BaseNowMovieImageUrl+movie.getBackdropPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mBackdropImageView);

        Glide.with(getActivity().getApplicationContext())
                .load(Constants.Api.BaseNowMovieImageUrl+movie.getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mPosterImageView);
    }

}
