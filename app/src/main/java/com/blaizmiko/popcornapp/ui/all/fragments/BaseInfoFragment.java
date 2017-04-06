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
import android.widget.VideoView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.data.models.images.ImageModel;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.genretags.GenresTagsAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.blaizmiko.popcornapp.ui.all.presentation.photos.PhotosAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.rating.RatingView;
import com.blaizmiko.popcornapp.ui.all.presentation.similarCinemas.SimilarCinemasPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.similarCinemas.SimilarCinemasView;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.blaizmiko.popcornapp.ui.all.presentation.trailers.TrailersAdapter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseInfoFragment extends BaseMvpFragment implements View.OnClickListener, StorylineView,
        RecyclerViewListeners.OnItemClickListener, LoadProgressView, RatingView, SimilarCinemasView {

    //Binding vies
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

    public static final String TITLE = "Info";
    protected String cinemaName;
    protected String cinemaReleaseDate;
    protected TrailersAdapter trailersAdapter;
    protected PhotosAdapter photosAdapter;
    protected TileAdapter similarAdapter;
    protected GenresTagsAdapter genresTagsAdapter;
    protected ProgressBar progressBar;

    //Inject presenters
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;
    @InjectPresenter
    StorylinePresenter storylinePresenter;
    @InjectPresenter
    public RatingPresenter ratingPresenter;
    @InjectPresenter
    public SimilarCinemasPresenter similarCinemasPresenter;

    //Bind views
    public void onCreateView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        ButterKnife.bind(this, inflater.inflate(layoutId, container, false));
        progressBar = ButterKnife.findById(getActivity(), R.id.progress_bar_details_load);
    }

    //Init methods
    protected void initBaseAdapters() {
        final Context context = getActivity().getApplicationContext();

        photosAdapter = new PhotosAdapter(context);
        initAdapter(context, imagesRecyclerView, photosAdapter);
        photosAdapter.setItemClickListener(this);

        trailersAdapter = new TrailersAdapter(context);
        initAdapter(context, trailersRecyclerView, trailersAdapter);
        trailersAdapter.setItemClickListener(this);

        similarAdapter = new TileAdapter(context, TileAdapter.TileType.VERTICAL_TILE);
        initAdapter(context, similarRecyclerView, similarAdapter);
        similarAdapter.setItemClickListener(this);

        genresTagsAdapter = new GenresTagsAdapter();
        final FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(FlexDirection.ROW, FlexWrap.WRAP);
        genreTagsRecyclerView.setLayoutManager(flexboxLayoutManager);
        genreTagsRecyclerView.setAdapter(genresTagsAdapter);
    }

    private void initAdapter(final Context context, final RecyclerView recyclerView, final RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    protected void setStoryLineView(final String overview) {
        storyLineTextView.setText(overview);
        storyLineTextView.setOnClickListener(this);
        storylinePresenter.setExpandedLinesNumber(storyLineTextView.getLineCount());
        storylinePresenter.calculateNewSize();
    }

    @Override
    public void showSimilarCinemas(final List<TileAdapter.Item> cinemas) {
        similarAdapter.update(cinemas);
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

    //Callbacks
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_base_info_storyline:
                storylinePresenter.calculateNewSize();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        switch(view.getId()) {
            case R.id.image_view_adapter_movie_details_photo_item_photo:
                final List<ImageModel> images = ((PhotosAdapter) adapter).getAllItems();
                final String [] imageUrls = new String[images.size()];
                for (int i = 0; i < images.size(); i++) {
                    imageUrls[i] = images.get(i).getFilePath();
                }

                final String releaseDate = cinemaReleaseDate;
                final String filmName = cinemaName;

                ActivityNavigator.startGalleryActivity(getActivity(), position, imageUrls, releaseDate, filmName);
                break;

            case R.id.image_view_info_trailer_preview_image:
                ActivityNavigator.startTrailersActivity(getActivity(),
                        trailersAdapter.getItemByPosition(position).getKey());
                break;
        }
    }
}

