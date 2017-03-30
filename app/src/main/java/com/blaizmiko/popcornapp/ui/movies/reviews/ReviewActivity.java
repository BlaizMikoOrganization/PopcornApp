package com.blaizmiko.popcornapp.ui.movies.reviews;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.ui.all.activities.BaseMvpActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewActivity extends BaseMvpActivity{

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
        Context context = getApplicationContext();
        Glide.with(context)
                .load(Constants.MovieDbApi.BASE_HIGH_RES_IMAGE_URL + getIntent().getStringExtra(Constants.Extras.POSTER_URL))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(avatarImageView);
        reviewTextView.setText(getIntent().getStringExtra(Constants.Extras.REVIEW));
        authorTextView.setText(getIntent().getStringExtra(Constants.Extras.AUTHOR));

        reviewPresenter.loadPosters(getIntent().getIntExtra(Constants.Extras.ID, Constants.MovieDbApi.DEFAULT_CINEMA_ID));
    }

}
