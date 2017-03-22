package com.blaizmiko.popcornapp.ui.movies.details.review;

import android.content.Context;
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
import com.blaizmiko.popcornapp.data.models.movies.Review;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;

public class ReviewsFragment extends BaseMvpFragment implements ReviewsView, RecyclerViewListeners.OnItemClickListener, StorylineView{

    public static final String TITLE = "Reviews";

    public static ReviewsFragment newInstance() {
        return new ReviewsFragment();
    }

    @InjectPresenter
    ReviewsPresenter reviewsPresenter;
    @InjectPresenter
    StorylinePresenter storylinePresenter;

    private int movieId;
    private ReviewAdapter reviewAdapter;
    private TextView clickedTextView;

    @BindView(R.id.recycler_view_movie_details_reviews)
    RecyclerView reviewsRecyclerView;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        System.out.println("reviewOncreate");
        movieId = getArguments().getInt(Constants.Extras.ID);
    }
    @Override
    protected void bindViews() {
        System.out.println("review OnCreate");
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
    public void setReviews(List<Review> reviews) {
        reviewAdapter.update(reviews);
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        clickedTextView = (TextView) view;
        storylinePresenter.calculateNewSize();
    }

    @Override
    public void changeStorylineSize(int lines) {
        clickedTextView.setLines(lines);
    }
}
