package com.blaizmiko.popcornapp.ui.movies.details.review;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.db.models.movies.ReviewDBModel;
import com.blaizmiko.popcornapp.data.models.movies.ReviewMovieModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsFragment extends BaseMvpFragment implements ReviewsView, RecyclerViewListeners.OnItemClickListener {

    public static final String TITLE = "Reviews";

    public static ReviewsFragment newInstance(final LoadProgressPresenter progressPresenter) {
        loadProgressPresenter = progressPresenter;
        return new ReviewsFragment();
    }
    private static LoadProgressPresenter loadProgressPresenter;
    @InjectPresenter
    ReviewsPresenter reviewsPresenter;

    private long movieId;
    private ReviewAdapter reviewAdapter;

    @BindView(R.id.recycler_view_review_reviews)
    protected RecyclerView reviewsRecyclerView;
    @BindView(R.id.text_view_reviews_nothing_to_show)
    protected TextView nothingToShowTextView;
    protected ProgressBar progressBar;

    @Override
    public void onCreate(final Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        movieId = getArguments().getLong(Constants.Extras.ID);
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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        progressBar = ButterKnife.findById(getActivity(), R.id.progress_bar_details_load);
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }


    @Override
    public void showError() {
    }

    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    public void startLoad() {
        loadProgressPresenter.showProgress();
    }

    @Override
    public void showNoReviewsView() {
        nothingToShowTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showReviews(final List<ReviewDBModel> reviews) {
        reviewAdapter.update(reviews);
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.text_view_info_movie_details_review:
                final ReviewDBModel review = ((ReviewAdapter)adapter).getItemByPosition(position);
                ActivityNavigator.startReviewActivity(getActivity(),
                    review.getAuthor(),
                    getArguments().getString(Constants.Extras.TITLE),
                    review.getContent(),
                    getArguments().getLong(Constants.Extras.ID));
        }
    }
}
