package com.blaizmiko.popcornapp.ui.actors.details;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.actors.DetailedActorModel;
import com.blaizmiko.popcornapp.data.models.actors.TaggedImageModel;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;

public class DetailsActorActivity extends BaseMvpActivity implements DetailsActorView, View.OnClickListener, StorylineView {

    @InjectPresenter
    DetailsActorPresenter detailsActorPresenter;
    @InjectPresenter
    StorylinePresenter storylinePresenter;

    @BindView(R.id.image_view_actor_details_avatar)
    protected ImageView avatarImageView;
    @BindView(R.id.image_view_actor_details_background)
    protected ImageView backgroundImageView;
    @BindView(R.id.text_view_actor_details_age)
    protected TextView ageTextView;
    @BindView(R.id.text_view_actor_details_gender)
    protected TextView genderTextView;
    @BindView(R.id.text_view_actor_details_birth_date)
    protected TextView birthDateTextView;
    @BindView(R.id.text_view_actor_details_death_date)
    protected TextView deathDateTextView;
    @BindView(R.id.text_view_actor_details_birth_place)
    protected TextView birthPlaceTextView;
    @BindView(R.id.text_view_actor_details_biography)
    protected TextView biographyTextView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_details);
    }

    @Override
    protected void bindViews() {
        toolbar.setTitle("");
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);
        detailsActorPresenter.loadTaggedImages(getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_ID));
        detailsActorPresenter.loadActorInfo(getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_ID));
    }

    //DetailsActorPresenter
    @Override
    public void finishLoad() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void startLoad() {

    }

    @Override
    public void showActor(final DetailedActorModel actor) {
        toolbar.setTitle(actor.getName());

        birthPlaceTextView.setText(actor.getPlaceOfBirth());
        biographyTextView.setText(actor.getBiography());
        biographyTextView.setOnClickListener(this);
        storylinePresenter.setExpandedLinesNumber(biographyTextView.getLineCount());
        storylinePresenter.calculateNewSize();

        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + actor.getProfileImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(avatarImageView);
    }

    @Override
    public void showAge(final int age) {
        ageTextView.setText(String.valueOf(age));
    }

    @Override
    public void showBackdrop(final List<TaggedImageModel> images) {
        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + images.get(0).getFilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(backgroundImageView);
    }

    @Override
    public void showGender(final String gender) {
        genderTextView.setText(gender);
    }

    @Override
    public void showBirthDate(final String birthDate) {
        birthDateTextView.setText(birthDate);
    }

    @Override
    public void showDeathDate(final String deathDate) {
        deathDateTextView.setText(deathDate);
    }

    //Storyline presenter
    @Override
    public void changeStorylineSize(final int lines) {
        biographyTextView.setLines(lines);
    }

    //Listeners
    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.text_view_actor_details_biography:
                storylinePresenter.calculateNewSize();
                break;
        }
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
}
