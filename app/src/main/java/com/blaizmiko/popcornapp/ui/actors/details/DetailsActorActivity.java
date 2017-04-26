package com.blaizmiko.popcornapp.ui.actors.details;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.actors.detailed.TaggedImageModel;
import com.blaizmiko.popcornapp.ui.actors.details.biography.BiographyActorFragment;
import com.blaizmiko.popcornapp.ui.actors.details.movies.MoviesActorFragment;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.blaizmiko.popcornapp.ui.all.adapters.TabsAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;

public class DetailsActorActivity extends BaseMvpActivity implements DetailsActorView {

    @InjectPresenter
    DetailsActorPresenter detailsActorPresenter;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);
    }

    @Override
    protected void bindViews() {
        final int actorId = getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_ID);
        final String actorName = getIntent().getStringExtra(Constants.Extras.NAME);
        final String actorAvatarPath = getIntent().getStringExtra(Constants.Extras.AVATAR_URL);

        bindToolbar(actorId, actorName, actorAvatarPath);
        initViewPager(actorId);
    }

    private void initViewPager(final int actorId) {
        BiographyActorFragment biographyActorFragment = BiographyActorFragment.newInstance();
        MoviesActorFragment moviesActorFragment = MoviesActorFragment.newInstance();

        Bundle biographyBundle = new Bundle();
        biographyBundle.putInt(Constants.Extras.ID, actorId);

        biographyActorFragment.setArguments(biographyBundle);
        moviesActorFragment.setArguments(biographyBundle);

        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());
        adapter.addFragment(biographyActorFragment, BiographyActorFragment.TITLE);
        adapter.addFragment(moviesActorFragment, MoviesActorFragment.TITLE);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setAdapter(adapter);
    }

    private void bindToolbar(final int actorId, final String actorName, final String actorAvatarPath) {
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
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    public void finishLoad() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void startLoad() {

    }
}
