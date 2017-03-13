package com.blaizmiko.popcornapp.ui.tvshows.details;

import android.content.Context;
import android.os.Bundle;
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
import com.blaizmiko.popcornapp.common.utils.AppUtil;
import com.blaizmiko.popcornapp.data.models.rating.Rating;
import com.blaizmiko.popcornapp.data.models.tvshows.detailed.DetailedTvShow;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.blaizmiko.popcornapp.ui.all.presentation.cast.CastAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.blaizmiko.popcornapp.ui.all.presentation.photos.PhotosAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingView;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.blaizmiko.popcornapp.ui.all.presentation.trailers.TrailersAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import butterknife.BindView;

public class TvShowDetailsActivity extends BaseMvpActivity implements TvShowDetailsView, RatingView, StorylineView, View.OnClickListener, LoadProgressView {
    @BindView(R.id.toolbar)
    public Toolbar toolbar;

    @InjectPresenter
    TvShowDetailsPresenter tvShowDetailsPresenter;
    @InjectPresenter
    StorylinePresenter storylinePresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;
    @InjectPresenter
    RatingPresenter ratingPresenter;

    private GenresTagsAdapter genresTagsAdapter;
    private CastAdapter castAdapter;
    private TrailersAdapter trailersAdapter;
    private RatingAdapter ratingAdapter;
    private PhotosAdapter photosAdapter;

    //Bind views
    @BindView(R.id.details_toolbar_backdrop_image_view)
    ImageView backdropImageView;
    @BindView(R.id.details_toolbar_poster_image_view)
    ImageView posterImageView;
    @BindView(R.id.details_toolbar_rating_text_view)
    TextView ratingTextView;
    @BindView(R.id.details_toolbar_title_text_view)
    TextView titleTextView;
    @BindView(R.id.text_view_tv_show_details_storyline)
    TextView storyLineTextView;
    @BindView(R.id.details_toolbar_rating_bar)
    SimpleRatingBar ratingBar;
    @BindView(R.id.details_toolbar_genre_tags_recycler_view)
    RecyclerView genresRecyclerView;
    @BindView(R.id.progress_bar_tv_show_details_load_progress)
    ProgressBar progressBar;
    @BindView(R.id.recycler_view_tv_show_details_cast)
    RecyclerView actorsRecyclerView;
    @BindView(R.id.recycler_view_tv_show_details_trailers)
    RecyclerView trailersRecyclerView;
    @BindView(R.id.recycler_view_tv_show_details_photos)
    RecyclerView imagesRecyclerView;
    @BindView(R.id.recycler_view_tv_show_details_ratings)
    RecyclerView recyclerViewRatings;

    //Life cycle
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_details);
    }

    //Init views
    @Override
    protected void bindViews() {
        toolbar.setTitle("");
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);

        final int defaultTvShowDetailsId = 0;
        int tvShowId = getIntent().getIntExtra(Constants.Bundles.ID, defaultTvShowDetailsId);
        tvShowDetailsPresenter.loadTvShow(tvShowId);

        Context context = getApplicationContext();

        final FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(FlexDirection.ROW, FlexWrap.WRAP);
        genresRecyclerView.setLayoutManager(flexboxLayoutManager);
        genresTagsAdapter = new GenresTagsAdapter();
        genresRecyclerView.setAdapter(genresTagsAdapter);

        final LinearLayoutManager actorsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        actorsRecyclerView.setLayoutManager(actorsLayoutManager);
        castAdapter = new CastAdapter(context);
        actorsRecyclerView.setAdapter(castAdapter);

        final LinearLayoutManager imagesLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        imagesRecyclerView.setLayoutManager(imagesLayoutManager);
        photosAdapter = new PhotosAdapter(context);
        imagesRecyclerView.setAdapter(photosAdapter);

        final LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        trailersRecyclerView.setLayoutManager(trailersLayoutManager);
        trailersAdapter = new TrailersAdapter(context);
        trailersRecyclerView.setAdapter(trailersAdapter);

        final LinearLayoutManager ratingsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRatings.setLayoutManager(ratingsLayoutManager);
        ratingAdapter = new RatingAdapter();
        recyclerViewRatings.setAdapter(ratingAdapter);
    }

    //TvShowDetails presenter
    @Override
    public void setTvShow(DetailedTvShow tvShow) {
        setToolbarTitle(tvShow.getName());
        ratingTextView.setText(Double.toString(tvShow.getVoteAverage()));
        titleTextView.setText(tvShow.getName());
        storyLineTextView.setText(tvShow.getOverview());
        storyLineTextView.setOnClickListener(this);
        ratingBar.setRating(AppUtil.roundToOneDecimal(tvShow.getVoteAverage(), AppUtil.ApiRatingToAppRating));
        Glide.with(getApplicationContext())
                .load(Constants.TheMovieDbApi.BaseHighResImageUrl + tvShow.getBackdropPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(backdropImageView);
        Glide.with(getApplicationContext())
                .load(Constants.TheMovieDbApi.BaseLowResImageUrl + tvShow.getPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(posterImageView);

        updateAdapters(tvShow);
        ratingPresenter.loadRating(tvShow.getExternalIds().getImdbId());
    }

    private void updateAdapters(DetailedTvShow tvShow) {
        genresTagsAdapter.update(tvShow.getGenres());
        castAdapter.update(tvShow.getCredits().getCast());
        trailersAdapter.update(tvShow.getVideos().getResults());
        photosAdapter.update(tvShow.getPictures().getBackdrops());
    }

    //LoadProgress presenter
    @Override
    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    @Override
    public void startLoad() {
        loadProgressPresenter.showProgress();
    }

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

    //FullRating presenter
    @Override
    public void setFullRating(Rating rating) {
        ratingAdapter.update(rating);
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    //Storyline presenter
    @Override
    public void changeStorylineSize(int lines) {
        storyLineTextView.setLines(lines);
    }

    //Listeners
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_activity_movie_details_storyline:
                storylinePresenter.calculateNewSize();
                break;
        }
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
}
