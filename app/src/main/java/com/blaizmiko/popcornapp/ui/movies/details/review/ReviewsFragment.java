package com.blaizmiko.popcornapp.ui.movies.details.review;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.movies.ReviewMovieModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import java.util.List;

import butterknife.BindView;

public class ReviewsFragment extends BaseMvpFragment implements ReviewsView, RecyclerViewListeners.OnItemClickListener {

    public static final String TITLE = "Reviews";

    public static ReviewsFragment newInstance() {
        return new ReviewsFragment();
    }

    @InjectPresenter
    ReviewsPresenter reviewsPresenter;

    private int movieId;
    private ReviewAdapter reviewAdapter;

    @BindView(R.id.recycler_view_review_reviews)
    RecyclerView reviewsRecyclerView;
    @BindView(R.id.text_view_reviews_nothing_to_show)
    TextView nothingToShowTextView;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        movieId = getArguments().getInt(Constants.Extras.ID);
    }
    @Override
    protected void bindViews() {
        Context context = getActivity().getApplicationContext();
        LinearLayoutManager reviewsLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        reviewsRecyclerView.setLayoutManager(reviewsLayoutManager);
        reviewAdapter = new ReviewAdapter();
        reviewsRecyclerView.setAdapter(reviewAdapter);
        reviewAdapter.setItemClickListener(this);
        reviewsPresenter.loadReviews(movieId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }


    @Override
    public void showError() {
    }

    @Override
    public void finishLoad() {
    }

    @Override
    public void startLoad() {
    }

    @Override
    public void showNoReviewsView() {
        nothingToShowTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setReviews(List<ReviewMovieModel> reviews) {
        reviewAdapter.update(reviews);
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.text_view_info_movie_details_review:
                ReviewMovieModel review = ((ReviewAdapter)adapter).getItemByPosition(position);

                ActivityNavigator.startReviewActivity(getActivity(),
                        review.getAuthor(),
                        getArguments().getString(Constants.Extras.TITLE),
                        review.getContent(),
                        getArguments().getInt(Constants.Extras.ID));
        }
    }
}
