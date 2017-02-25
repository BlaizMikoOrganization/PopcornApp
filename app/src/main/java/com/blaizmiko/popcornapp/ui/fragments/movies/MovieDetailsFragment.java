package com.blaizmiko.popcornapp.ui.fragments.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.utils.FormatUtils;
import com.blaizmiko.popcornapp.models.movies.Movie;
import com.blaizmiko.popcornapp.presentation.presenters.movies.DetailMoviesPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.LoadProgressPresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.LoadProgressView;
import com.blaizmiko.popcornapp.presentation.views.movies.MovieDetailsView;
import com.blaizmiko.popcornapp.ui.adapters.movies.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.fragments.base.BaseMvpFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import butterknife.BindView;

public class MovieDetailsFragment extends BaseMvpFragment implements LoadProgressView, MovieDetailsView {

    public static MovieDetailsFragment newInstance() {
        return new MovieDetailsFragment();
    }

    @InjectPresenter
    DetailMoviesPresenter mMovieDetailsPresenter;

    @InjectPresenter
    LoadProgressPresenter mLoadProgressPresenter;

    private GenresTagsAdapter mGenresTagsAdapter;
    private int mMovieId;
    private final int mTagsColumnAmount = 2;

    //Bind views
    @BindView(R.id.fragment_detail_movie_backdrop_image_view)
    ImageView mBackdropImageView;
    @BindView(R.id.fragment_detail_movie_poster_image_view)
    ImageView mPosterImageView;
    @BindView(R.id.fragment_detail_movie_rating_text_view)
    TextView mRatingTextView;
    @BindView(R.id.fragment_detail_movie_title_text_view)
    TextView mTitleTextView;
    @BindView(R.id.fragment_detail_movie_story_line_text_view)
    TextView mStoryLineTextView;
    @BindView(R.id.fragment_detail_movie_rating_bar)
    SimpleRatingBar mRatingBar;
    @BindView(R.id.fragment_detail_movie_genre_tags_recycler_view)
    RecyclerView mGenresRecyclerView;
    @BindView(R.id.fragment_detail_movie_short_info_linear_layout)
    LinearLayout mShortInfoLinearLayout;
    @BindView(R.id.fragment_detail_movie_progress_bar)
    ProgressBar mProgressBar;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        mMovieId = getArguments().getInt("id");
        return inflater.inflate(R.layout.fragment_detail_movies, container, false);
    }

    @Override
    protected void bindViews() {
        mMovieDetailsPresenter.loadMovie(mMovieId);

        Context mContext = getActivity().getApplicationContext();
        final GridLayoutManager linearLayoutManager = new GridLayoutManager(mContext, mTagsColumnAmount);

        mGenresRecyclerView.setLayoutManager(linearLayoutManager);
        mGenresTagsAdapter = new GenresTagsAdapter();
        mGenresRecyclerView.setAdapter(mGenresTagsAdapter);
    }

    @Override
    public void setMovie(Movie movie) {
        mRatingTextView.setText(Double.toString(movie.getVoteAverage()));
        mTitleTextView.setText(movie.getTitle());
        mStoryLineTextView.setText(movie.getOverview());
        mRatingBar.setRating(Float.parseFloat(new DecimalFormat(FormatUtils.ONE_DECIMAL, new DecimalFormatSymbols(Locale.US)).format(movie.getVoteAverage() / 2)));

        Glide.with(getActivity().getApplicationContext())
                .load(Constants.Api.BaseHighResImageUrl + movie.getBackdropPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mBackdropImageView);

        Glide.with(getActivity().getApplicationContext())
                .load(Constants.Api.BaseLowResImageUrl + movie.getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mPosterImageView);

        mGenresTagsAdapter.update(movie.getGenres());
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishLoad() {
        mLoadProgressPresenter.hideProgress();
    }

    @Override
    public void startLoad() {
        mLoadProgressPresenter.showProgress();
    }

    //LoadProgress presenter
    public void showProgress() {
        if (mProgressBar.getVisibility() != View.VISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressBar.getVisibility() != View.GONE) {
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
