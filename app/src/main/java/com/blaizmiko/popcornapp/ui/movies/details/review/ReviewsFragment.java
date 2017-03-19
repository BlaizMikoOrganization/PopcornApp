package com.blaizmiko.popcornapp.ui.movies.details.review;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.application.Constants;
import com.blaizmiko.popcornapp.data.models.movies.Review;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;

import java.util.List;

import butterknife.BindView;

public class ReviewsFragment extends BaseMvpFragment implements ReviewsView{

    public static final String TITLE = "Reviews";

    public static ReviewsFragment newInstance() {
        return new ReviewsFragment();
    }

    @InjectPresenter
    ReviewsPresenter reviewsPresenter;

    private int movieId;
    private ReviewAdapter reviewAdapter;

    @BindView(R.id.recycler_view_movie_details_reviews)
    RecyclerView reviewsRecyclerView;

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
    public void setReviews(List<Review> reviews) {
        reviewAdapter.update(reviews);
    }
}
