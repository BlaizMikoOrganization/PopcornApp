package com.blaizmiko.popcornapp.ui.all.presentation.details.info.base;

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
import com.blaizmiko.popcornapp.data.models.Info;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.blaizmiko.popcornapp.ui.all.presentation.photos.PhotosAdapter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylinePresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.storyline.StorylineView;
import com.blaizmiko.popcornapp.ui.all.presentation.trailers.TrailersAdapter;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseInfoFragment extends BaseMvpFragment implements View.OnClickListener, StorylineView,
        RecyclerViewListeners.OnItemClickListener, LoadProgressView {

    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;
    @InjectPresenter
    StorylinePresenter storylinePresenter;

    TrailersAdapter trailersAdapter;
    PhotosAdapter photosAdapter;
    public TileAdapter similarAdapter;

    @BindView(R.id.text_view_info_storyline)
    TextView storyLineTextView;
    @BindView(R.id.recycler_view_info_photos)
    RecyclerView imagesRecyclerView;
    @BindView(R.id.recycler_view_info_trailers)
    RecyclerView trailersRecyclerView;
    @BindView(R.id.recycler_view_movie_details_info_similar)
    RecyclerView similarRecyclerView;

    ProgressBar progressBar;

    public void onCreateView(LayoutInflater inflater, ViewGroup container, int layoutId) {
        ButterKnife.bind(this, inflater.inflate(layoutId, container, false));
        progressBar = ButterKnife.findById(getActivity(), R.id.progress_bar_details_load);
    }

    protected void initAdapters() {
        Context context = getActivity().getApplicationContext();

        final LinearLayoutManager imagesLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        imagesRecyclerView.setLayoutManager(imagesLayoutManager);
        photosAdapter = new PhotosAdapter(context);
        imagesRecyclerView.setAdapter(photosAdapter);

        final LinearLayoutManager trailersLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        trailersRecyclerView.setLayoutManager(trailersLayoutManager);
        trailersAdapter = new TrailersAdapter(context);
        trailersRecyclerView.setAdapter(trailersAdapter);

        final LinearLayoutManager similarMoviesLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        similarRecyclerView.setLayoutManager(similarMoviesLayoutManager);
        similarRecyclerView.setHasFixedSize(true);
        similarAdapter = new TileAdapter(context, TileAdapter.TileType.VERTICAL_TILE);
        similarAdapter.setItemClickListener(this);
        similarRecyclerView.setAdapter(similarAdapter);
    }

    public void setInfo(Info cinema) {
        storyLineTextView.setText(cinema.getOverview());
        storyLineTextView.setOnClickListener(this);
        storylinePresenter.setExpandedLinesNumber(storyLineTextView.getLineCount());
        storylinePresenter.calculateNewSize();
        updateAdapters(cinema);
    }

    private void updateAdapters(Info cinema) {
        trailersAdapter.update(cinema.getVideos().getResults());
        photosAdapter.update(cinema.getImages().getBackdrops());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_info_storyline:
                storylinePresenter.calculateNewSize();
                break;
        }
    }

    @Override
    public void changeStorylineSize(int lines) {
        storyLineTextView.setLines(lines);
    }

    @Override
    public void onItemClick(View view, int position, RecyclerView.Adapter adapter) {
        final TileAdapter.Item item = ((TileAdapter) adapter).getItemByPosition(position);
        final int id = item.getId();
        final String title = item.getTitle();
        final double rating = item.getRating();
        final String backdropUrl = item.getBackdropUrl();
        final String posterUrl = item.getPosterUrl();
        //ActivityNavigator.startDetailsActivity(getActivity(), id, title, rating, backdropUrl, posterUrl);
    }

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
}

