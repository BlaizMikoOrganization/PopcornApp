package com.blaizmiko.popcornapp.ui.activities.movie;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.common.utils.AppUtils;
import com.blaizmiko.popcornapp.models.movies.DetailedMovie;
import com.blaizmiko.popcornapp.models.rating.Rating;
import com.blaizmiko.popcornapp.presentation.presenters.movies.LoadProgressPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.MovieDetailsPresenter;
import com.blaizmiko.popcornapp.presentation.presenters.movies.StorylinePresenter;
import com.blaizmiko.popcornapp.presentation.views.movies.LoadProgressView;
import com.blaizmiko.popcornapp.presentation.views.movies.MovieDetailsView;
import com.blaizmiko.popcornapp.presentation.views.movies.StorylineView;
import com.blaizmiko.popcornapp.ui.activities.base.BaseMvpActivity;
import com.blaizmiko.popcornapp.ui.adapters.moviedetails.CastAdapter;
import com.blaizmiko.popcornapp.ui.adapters.moviedetails.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.adapters.moviedetails.ScreenshotsAdapter;
import com.blaizmiko.popcornapp.ui.adapters.moviedetails.TrailersAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import org.w3c.dom.Text;

import butterknife.BindView;

public class MovieDetailsActivity extends BaseMvpActivity implements StorylineView, View.OnClickListener, LoadProgressView, MovieDetailsView {


    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @InjectPresenter
    MovieDetailsPresenter mMovieDetailsPresenter;
    @InjectPresenter
    StorylinePresenter mStorylinePresenter;
    @InjectPresenter
    LoadProgressPresenter mLoadProgressPresenter;

    private GenresTagsAdapter mGenresTagsAdapter;
    private CastAdapter mCastAdapter;
    private TrailersAdapter mTrailersAdapter;

    private ScreenshotsAdapter mScreenshotsAdapter;
    CoordinatorLayout.Behavior behavior;
    private boolean mIsStoryLineTextViewOpen = false;

    //Bind views
    @BindView(R.id.fragment_detail_movie_backdrop_image_view)
    ImageView mBackdropImageView;
    @BindView(R.id.fragment_detail_movie_poster_image_view)
    ImageView mPosterImageView;
    @BindView(R.id.fragment_detail_movie_rating_text_view)
    TextView mRatingTextView;
    @BindView(R.id.fragment_detail_movie_title_text_view)
    TextView mTitleTextView;
    @BindView(R.id.fragment_movie_details_storyline_text_view)
    TextView mStoryLineTextView;
    @BindView(R.id.fragment_detail_movie_rating_bar)
    SimpleRatingBar mRatingBar;
    @BindView(R.id.fragment_detail_movie_genre_tags_recycler_view)
    RecyclerView mGenresRecyclerView;
    @BindView(R.id.fragment_detail_movie_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.fragment_movie_details_cast_recycler_view)
    RecyclerView mActorsRecyclerView;
    @BindView(R.id.fragment_movie_details_trailers_recycler_view)
    RecyclerView mTrailersRecyclerView;
    @BindView(R.id.fragment_movie_details_screenshots_recycler_view)
    RecyclerView mImagesRecyclerView;
    @BindView(R.id.fragment_movie_details_rating_text_view)
    TextView mRating2TextView;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movies);
    }

    @Override
    protected void bindViews() {
        //setToolbarTitle("");
        mToolbar.setTitle("");
        setToolbar(mToolbar);
        setToolbarDisplayHomeButtonEnabled(true);
        //setToolbarDisplayShowTitleEnabled(false);

        System.out.println("oo");
        final int mDefaultMovieDetailsId = 0;
        int mMovieId = getIntent().getIntExtra(Constants.Bundles.ID, mDefaultMovieDetailsId);
        mMovieDetailsPresenter.loadMovie(mMovieId);


        Context context = getApplicationContext();

        final FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(FlexDirection.ROW, FlexWrap.WRAP);
        mGenresRecyclerView.setLayoutManager(flexboxLayoutManager);
        mGenresTagsAdapter = new GenresTagsAdapter();
        mGenresRecyclerView.setAdapter(mGenresTagsAdapter);

        final LinearLayoutManager actorsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mActorsRecyclerView.setLayoutManager(actorsLayoutManager);
        mCastAdapter = new CastAdapter(context);
        mActorsRecyclerView.setAdapter(mCastAdapter);

        final LinearLayoutManager imagesLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mImagesRecyclerView.setLayoutManager(imagesLayoutManager);
        mScreenshotsAdapter = new ScreenshotsAdapter(context);
        mImagesRecyclerView.setAdapter(mScreenshotsAdapter);

        final LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mTrailersRecyclerView.setLayoutManager(trailersLayoutManager);
        mTrailersAdapter = new TrailersAdapter(context);
        mTrailersRecyclerView.setAdapter(mTrailersAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setMovie(DetailedMovie movie) {
        setToolbarTitle(movie.getTitle());
        mRatingTextView.setText(Double.toString(movie.getVoteAverage()));
        mTitleTextView.setText(movie.getTitle());
        mStoryLineTextView.setText(movie.getOverview());
        mStoryLineTextView.setOnClickListener(this);
        mRatingBar.setRating(AppUtils.roundToOneDecimal(movie.getVoteAverage(), AppUtils.ApiRatingToAppRating));

        Glide.with(getApplicationContext())
                .load(Constants.TheMovieDbApi.BaseHighResImageUrl + movie.getBackdropPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mBackdropImageView);
        Glide.with(getApplicationContext())
                .load(Constants.TheMovieDbApi.BaseLowResImageUrl + movie.getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mPosterImageView);

        mGenresTagsAdapter.update(movie.getGenres());
        mCastAdapter.update(movie.getCredits().getCast());
        mTrailersAdapter.update(movie.getMovieVideos().getResults());
        mScreenshotsAdapter.update(movie.getMovieImages().getBackdrops());
    }

    @Override
    public void setMovieRating(Rating rating) {
        mRating2TextView.setText(rating.getIMDb() + "    " +rating.getMetascore() +"     " +rating.getTomatoAudienceScore()+ "       " +rating.getTomatometer());
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_movie_details_storyline_text_view:
                mStorylinePresenter.calculateNewSize();
                break;
        }
    }

    @Override
    public void changeStorylineSize(int lines) {
        mStoryLineTextView.setLines(lines);
    }


}
