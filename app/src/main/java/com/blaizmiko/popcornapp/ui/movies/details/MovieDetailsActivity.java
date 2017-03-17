package com.blaizmiko.popcornapp.ui.movies.details;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsView;
import com.blaizmiko.popcornapp.ui.movies.details.cast.CastFragment;
import com.blaizmiko.popcornapp.ui.movies.details.info.InfoFragment;
import com.blaizmiko.popcornapp.ui.movies.details.review.TabsAdapter;
import com.blaizmiko.popcornapp.ui.movies.details.review.ReviewsFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MovieDetailsActivity extends BaseMvpActivity implements GenresTagsView{

    @InjectPresenter
    GenresTagsPresenter genresTagsPresenter;

    GenresTagsAdapter genresTagsAdapter;
    //Bind views
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.details_toolbar_backdrop_image_view)
    protected ImageView backdropImageView;
    @BindView(R.id.details_toolbar_poster_image_view)
    protected ImageView posterImageView;
    @BindView(R.id.details_toolbar_rating_text_view)
    protected TextView ratingTextView;
    @BindView(R.id.details_toolbar_title_text_view)
    protected TextView titleTextView;
    @BindView(R.id.viewpager_movie_details)
    protected ViewPager viewPager;
    @BindView(R.id.progress_bar_activity_movie_details_load_progress)
    protected ProgressBar progressBar;
    @BindView(R.id.tabs)
    protected TabLayout tabLayout;
//    @BindView(R.id.details_toolbar_rating_bar)
//    SimpleRatingBar ratingBar;
    @BindView(R.id.details_toolbar_genre_tags_recycler_view)
    RecyclerView genresRecyclerView;

    private int movieId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
    }

    @Override
    protected void bindViews() {
        toolbar.setTitle("");
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);

        final int defaultMovieDetailsId = 0;
        final int defaultMovieDetailsRating = 0;

        movieId = getIntent().getIntExtra(Constants.Extras.ID, defaultMovieDetailsId);
        titleTextView.setText(getIntent().getStringExtra(Constants.Extras.TITLE));
        ratingTextView.setText(Double.toString(getIntent().getDoubleExtra(Constants.Extras.RATING, defaultMovieDetailsRating)));

        Context context = getApplication().getApplicationContext();

        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + getIntent().getStringExtra(Constants.Extras.POSTER_URL))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(posterImageView);

        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + getIntent().getStringExtra(Constants.Extras.BACKDROP_URL))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(backdropImageView);

        final FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(FlexDirection.ROW, FlexWrap.WRAP);
        genresRecyclerView.setLayoutManager(flexboxLayoutManager);
        genresTagsAdapter = new GenresTagsAdapter();
        genresRecyclerView.setAdapter(genresTagsAdapter);

        initViewPager();
        List<Integer> list = new ArrayList<>();
        list.add(14);
        list.add(28);
        list.add(80);
        genresTagsPresenter.loadGenres(list);
    }

    private void initViewPager() {
        InfoFragment infoFragment = InfoFragment.newInstance();
        ReviewsFragment reviewsFragment = ReviewsFragment.newInstance();
        CastFragment castFragment = CastFragment.newInstance();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.Extras.ID, movieId);

        infoFragment.setArguments(bundle);
        castFragment.setArguments(bundle);
        reviewsFragment.setArguments(bundle);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(infoFragment, infoFragment.TITLE);
        adapter.addFragment(castFragment, castFragment.TITLE);
        adapter.addFragment(reviewsFragment, reviewsFragment.TITLE);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                //NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void setGenreTags(List<String> genreNames) {
        for (String genre : genreNames) {
            System.out.println(genre);
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void finishLoad() {

    }
}
