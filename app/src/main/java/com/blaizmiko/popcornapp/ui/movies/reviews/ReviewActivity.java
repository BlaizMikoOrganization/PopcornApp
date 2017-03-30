package com.blaizmiko.popcornapp.ui.movies.reviews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.images.ImageModel;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewActivity extends BaseMvpActivity implements ReviewView{

    @BindView(R.id.image_view_review_author_avatar)
    protected CircleImageView avatarImageView;
    @BindView(R.id.text_view_review)
    protected TextView reviewTextView;
    @BindView(R.id.text_view_review_author)
    protected TextView authorTextView;
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @InjectPresenter
    ReviewPresenter reviewPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
    }

    @Override
    protected void bindViews() {
        setToolbar(toolbar);
        setToolbarTitle(getIntent().getStringExtra(Constants.Extras.TITLE));
        reviewTextView.setText(getIntent().getStringExtra(Constants.Extras.REVIEW));
        authorTextView.setText(getIntent().getStringExtra(Constants.Extras.AUTHOR));

        reviewPresenter.loadPosters(getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_CINEMA_ID));
    }

    @Override
    public void finishLoad() {

    }

    @Override
    public void startLoad() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void setPosters(List<ImageModel> images) {
        Glide.with(getApplicationContext())
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + images.get(0).getFilePath())
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(avatarImageView);
    }
}
