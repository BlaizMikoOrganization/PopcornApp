package com.blaizmiko.popcornapp.ui.movies.reviews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.images.ImageModel;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewActivity extends BaseMvpActivity implements ReviewView, LoadProgressView{
    @InjectPresenter
    ReviewPresenter reviewPresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;

    @BindView(R.id.image_view_review_author_avatar)
    protected CircleImageView avatarImageView;
    @BindView(R.id.text_view_review)
    protected TextView reviewTextView;
    @BindView(R.id.text_view_review_author)
    protected TextView authorTextView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;
    @BindView(R.id.progress_bar_details_load)
    protected ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }

    @Override
    protected void bindViews() {
        setToolbar(toolbar);
        setToolbarDisplayHomeButtonEnabled(true);
        setToolbarTitle(getIntent().getStringExtra(Constants.Extras.TITLE));

        reviewTextView.setText(getIntent().getStringExtra(Constants.Extras.REVIEW));
        authorTextView.setText(getIntent().getStringExtra(Constants.Extras.AUTHOR));

        reviewPresenter.loadPosters(getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_CINEMA_ID));
    }

    @Override
    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    @Override
    public void startLoad() {
        loadProgressPresenter.showProgress();
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPosters(List<ImageModel> images) {
        final int SINGLE_MATCH = 0;
        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + images.get(SINGLE_MATCH).getFilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(avatarImageView);
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
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
}
