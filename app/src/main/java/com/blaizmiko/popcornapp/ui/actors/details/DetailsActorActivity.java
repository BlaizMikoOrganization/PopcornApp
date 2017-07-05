package com.blaizmiko.popcornapp.ui.actors.details;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.actors.detailed.TaggedImageModel;
import com.blaizmiko.popcornapp.ui.actors.details.biography.BiographyActorFragment;
import com.blaizmiko.popcornapp.ui.actors.details.cinemas.movies.MoviesActorFragment;
import com.blaizmiko.popcornapp.ui.actors.details.cinemas.tvshows.TvShowsActorFragment;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.blaizmiko.popcornapp.ui.all.adapters.TabsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;

public class DetailsActorActivity extends BaseMvpActivity implements DetailsActorView, LoadProgressView {

    @InjectPresenter
    DetailsActorPresenter detailsActorPresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;

    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;
    @BindView(R.id.image_view_actor_details_avatar)
    protected ImageView avatarImageView;
    @BindView(R.id.image_view_actor_details_background)
    protected ImageView backgroundImageView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager_details)
    ViewPager viewPager;

    //Lifecycle methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);
    }

    //Init methods
    @Override
    protected void bindViews() {
        final long actorId = getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_ID);
        final String actorName = getIntent().getStringExtra(Constants.Extras.NAME);
        final String actorAvatarPath = getIntent().getStringExtra(Constants.Extras.AVATAR_URL);

        bindToolbar(actorId, actorName, actorAvatarPath);
        initViewPager(actorId);
    }

    private void initViewPager(final long actorId) {
        final BiographyActorFragment biographyActorFragment = BiographyActorFragment.newInstance(loadProgressPresenter);
        final MoviesActorFragment moviesActorFragment = MoviesActorFragment.newInstance(loadProgressPresenter);
        final TvShowsActorFragment tvShowsActorFragment = TvShowsActorFragment.newInstance(loadProgressPresenter);

        final Bundle actorBundle = new Bundle();
        actorBundle.putLong(Constants.Extras.ID, actorId);

        biographyActorFragment.setArguments(actorBundle);
        moviesActorFragment.setArguments(actorBundle);
        tvShowsActorFragment.setArguments(actorBundle);

        final TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(biographyActorFragment, BiographyActorFragment.TITLE);
        adapter.addFragment(moviesActorFragment, MoviesActorFragment.TITLE);
        adapter.addFragment(tvShowsActorFragment, TvShowsActorFragment.TITLE);

        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }

    private void bindToolbar(final long actorId, final String actorName, final String actorAvatarPath) {
        toolbar.setTitle(actorName);
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);

        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + actorAvatarPath)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(avatarImageView);
        detailsActorPresenter.loadTaggedImages(actorId);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showBackdrop(final List<TaggedImageModel> backdropsUrl) {
        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + backdropsUrl.get(0).getFilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(backgroundImageView);
    }

    //Detailed actor presenters
    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    @Override
    public void startLoad() {
        loadProgressPresenter.showProgress();
    }

    //LoadProgress presenter
    public void showProgress() {
        if (progressBar == null) return;
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if (progressBar == null) return;
        progressBar.setVisibility(View.GONE);
    }
}
