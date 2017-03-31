package com.blaizmiko.popcornapp.ui.tvshows;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.blaizmiko.popcornapp.R;
import com.blaizmiko.popcornapp.ui.ActivityNavigator;
import com.blaizmiko.popcornapp.ui.all.adapters.TileAdapter;
import com.blaizmiko.popcornapp.ui.all.fragments.BaseMvpFragment;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressPresenter;
import com.blaizmiko.popcornapp.ui.all.presentation.loadprogress.LoadProgressView;
import com.blaizmiko.popcornapp.ui.tvshows.nowplaying.NowPlayingTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.nowplaying.NowPlayingTvShowsView;
import com.blaizmiko.popcornapp.ui.tvshows.popular.PopularTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.popular.PopularTvShowsView;
import com.blaizmiko.popcornapp.ui.tvshows.top.TopTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.top.TopTvShowsView;
import com.blaizmiko.popcornapp.ui.tvshows.upcoming.UpcomingTvShowsPresenter;
import com.blaizmiko.popcornapp.ui.tvshows.upcoming.UpcomingTvShowsView;
import com.blaizmiko.ui.listeners.RecyclerViewListeners;
import com.blaizmiko.ui.listeners.RecyclerViewLoadMore;

import java.util.List;

import butterknife.BindView;

public class TvShowsFragment extends BaseMvpFragment implements RecyclerViewListeners.OnItemClickListener, RecyclerViewListeners.OnLoadMoreListener, LoadProgressView,
        NowPlayingTvShowsView,
        PopularTvShowsView,
        TopTvShowsView,
        UpcomingTvShowsView {

    public static TvShowsFragment newInstance() {
        return new TvShowsFragment();
    }

    @InjectPresenter
    NowPlayingTvShowsPresenter nowPlayingTvShowsPresenter;
    @InjectPresenter
    PopularTvShowsPresenter popularTvShowsPresenter;
    @InjectPresenter
    TopTvShowsPresenter topTvShowsPresenter;
    @InjectPresenter
    UpcomingTvShowsPresenter upcomingTvShowsPresenter;
    @InjectPresenter
    LoadProgressPresenter loadProgressPresenter;

    private TileAdapter nowPlayingTVShowsAdapter, popularTvShowsAdapter, topTvShowsAdapter, upcomingTvShowsAdapter;

    //Bind views
    @BindView(R.id.recycler_view_fragment_tv_shows_now_playing)
    protected RecyclerView nowTvShowsRecyclerView;
    @BindView(R.id.recycler_view_fragment_tv_shows_top)
    protected RecyclerView topTvShowsRecyclerView;
    @BindView(R.id.recycler_view_fragment_tv_shows_popular)
    protected RecyclerView popularTvShowsRecyclerView;
    @BindView(R.id.progress_bar_fragment_tv_shows_load_progress)
    protected ProgressBar progressBar;
    @BindView(R.id.recycler_view_tv_shows_fragment_upcoming)
    protected RecyclerView upcomingTvShowsRecyclerView;

    //Life cycle
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    //Init methods
    @Override
    protected void bindViews() {
        initAdapters();
        nowPlayingTvShowsPresenter.loadNowPlayingTvShowsList();
        popularTvShowsPresenter.loadPopularTvShowsList();
        topTvShowsPresenter.loadTopTvShowsList();
        upcomingTvShowsPresenter.loadUpcomingTvShowsList();
    }

    private void initAdapters() {
        final Context context = getActivity().getApplicationContext();
        nowPlayingTVShowsAdapter = initAdapter(context, nowTvShowsRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.HORIZONTAL_TILE);
        popularTvShowsAdapter = initAdapter(context, popularTvShowsRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
        topTvShowsAdapter = initAdapter(context, topTvShowsRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
        upcomingTvShowsAdapter = initAdapter(context, upcomingTvShowsRecyclerView, LinearLayoutManager.HORIZONTAL, TileAdapter.TileType.VERTICAL_TILE);
    }

    private TileAdapter initAdapter(final Context context, final RecyclerView recyclerView, final int layoutManagerType, final TileAdapter.TileType tileType) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, layoutManagerType, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerViewLoadMore(this, linearLayoutManager));

        final TileAdapter adapter = new TileAdapter(context, tileType);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(this);
        return adapter;
    }

    //Movies presenters
    @Override
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), "Sorry, an error occurred while establish server connection", Toast.LENGTH_SHORT).show();
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
    public void setUpcomingTvShowsList(List<TileAdapter.Item> upcomingTvShowsList) {
        upcomingTvShowsAdapter.add(upcomingTvShowsList);
    }

    @Override
    public void setNowPlayingTvShowsList(List<TileAdapter.Item> nowPlayingTvShowsList) {
        nowPlayingTVShowsAdapter.add(nowPlayingTvShowsList);
    }

    @Override
    public void setPopularTvShowsList(final List<TileAdapter.Item> popularTvShowsList) {
        popularTvShowsAdapter.add(popularTvShowsList);
    }

    @Override
    public void setTopTvShowsList(final List<TileAdapter.Item> topTvShowsList) {
        topTvShowsAdapter.add(topTvShowsList);
    }

    //LoadProgress presenter
    public void showProgress() {
        if (progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        if (progressBar.getVisibility() != View.GONE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    //Listeners
    @Override
    public void onLoadMore(final RecyclerView recyclerView, final int nextPage) {
        switch (recyclerView.getId()) {
            case R.id.recycler_view_fragment_movies_now_playing:
                nowPlayingTvShowsPresenter.loadNowPlayingTvShowsList();
                break;
            case R.id.recycler_view_fragment_tv_shows_popular:
                popularTvShowsPresenter.loadPopularTvShowsList();
                break;
            case R.id.recycler_view_fragment_movies_top_movies:
                topTvShowsPresenter.loadTopTvShowsList();
                break;
            case R.id.recycler_view_fragment_movies_upcoming_movies:
                upcomingTvShowsPresenter.loadUpcomingTvShowsList();
                break;
        }
    }

    @Override
    public void onItemClick(final View view, final int position, final RecyclerView.Adapter adapter) {
        final TileAdapter.Item tvShow = ((TileAdapter) adapter).getItemByPosition(position);

        final int id = tvShow.getId();
        final String title = tvShow.getTitle();
        final String backdropUrl = tvShow.getBackdropUrl();
        final String posterUrl = tvShow.getPosterUrl();
        final double rating = tvShow.getRating();
        ActivityNavigator.startDetailsTvShowActivity(getActivity(), id, title, backdropUrl, posterUrl, rating);
    }
}
