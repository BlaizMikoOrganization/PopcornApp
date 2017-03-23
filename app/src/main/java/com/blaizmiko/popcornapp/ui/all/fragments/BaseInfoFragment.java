package com.blaizmiko.popcornapp.ui.all.fragments;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.blaizmiko.popcornapp.ui.all.presentation.photos.PhotosAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingView;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.blaizmiko.popcornapp.ui.all.presentation.trailers.TrailersAdapter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseInfoFragment extends BaseMvpFragment implements View.OnClickListener, StorylineView,
        RecyclerViewListeners.OnItemClickListener, LoadProgressView, RatingView {

    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;
    @InjectPresenter
    StorylinePresenter storylinePresenter;
    @InjectPresenter
    public RatingPresenter ratingPresenter;

    public static final String TITLE = "Info";
    protected RatingAdapter ratingAdapter;
    protected TrailersAdapter trailersAdapter;
    protected PhotosAdapter photosAdapter;
    protected TileAdapter similarAdapter;
    protected GenresTagsAdapter genresTagsAdapter;

    protected ProgressBar progressBar;
    @BindView(R.id.recycler_view_base_info_genre_tags)
    protected RecyclerView genreTagsRecyclerView;
    @BindView(R.id.text_view_base_info_storyline)
    protected TextView storyLineTextView;
    @BindView(R.id.recycler_view_base_info_photos)
    protected RecyclerView imagesRecyclerView;
    @BindView(R.id.recycler_view_base_info_trailers)
    protected RecyclerView trailersRecyclerView;
    @BindView(R.id.recycler_view_base_info_similar)
    protected RecyclerView similarRecyclerView;
    @BindView(R.id.recycler_view_base_info_ratings)
    protected RecyclerView ratingRecyclerView;

    //Bind views
    public void onCreateView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        ButterKnife.bind(this, inflater.inflate(layoutId, container, false));
        progressBar = ButterKnife.findById(getActivity(), R.id.progress_bar_details_load);
    }

    //Init methods
    protected void initBaseAdapters() {
        Context context = getActivity().getApplicationContext();

        photosAdapter = new PhotosAdapter(context);
        initAdapter(context, imagesRecyclerView, photosAdapter);

        trailersAdapter = new TrailersAdapter(context);
        initAdapter(context, trailersRecyclerView, trailersAdapter);

        similarAdapter = new TileAdapter(context, TileAdapter.TileType.VERTICAL_TILE);
        initAdapter(context, similarRecyclerView, similarAdapter);
        similarAdapter.setItemClickListener(this);

        ratingAdapter = new RatingAdapter();
        initAdapter(context, ratingRecyclerView, ratingAdapter);

        genresTagsAdapter = new GenresTagsAdapter();
        final FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(FlexDirection.ROW, FlexWrap.WRAP);
        genreTagsRecyclerView.setLayoutManager(flexboxLayoutManager);
        genreTagsRecyclerView.setAdapter(genresTagsAdapter);
    }

    private void initAdapter(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    protected void setStoryLineView(String overview) {
        storyLineTextView.setText(overview);
        storyLineTextView.setOnClickListener(this);
        storylinePresenter.setExpandedLinesNumber(storyLineTextView.getLineCount());
        storylinePresenter.calculateNewSize();
    }

    //Storyline Presenter
    @Override
    public void changeStorylineSize(int lines) {
        storyLineTextView.setLines(lines);
    }


    //Load Progress Presenter
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
    }

    public void finishLoad() {
        loadProgressPresenter.hideProgress();
    }

    public void startLoad() {
        loadProgressPresenter.showProgress();
    }

    @Override
    public void showProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_base_info_storyline:
                storylinePresenter.calculateNewSize();
                break;
        }
    }
}

